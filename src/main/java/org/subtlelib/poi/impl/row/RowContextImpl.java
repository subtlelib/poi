package org.subtlelib.poi.impl.row;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.util.Collection;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.joda.time.LocalDate;
import org.subtlelib.poi.api.row.RowContext;
import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.api.style.CompositeStyle;
import org.subtlelib.poi.api.style.Style;
import org.subtlelib.poi.api.style.StyleRegistry;
import org.subtlelib.poi.api.totals.ColumnTotalsDataRange;
import org.subtlelib.poi.api.totals.Formula;
import org.subtlelib.poi.impl.column.Columns;
import org.subtlelib.poi.impl.style.system.SystemCellWrapTextStyle;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;


public class RowContextImpl extends AbstractDelegatingRowContext {

	private static final Joiner multilineTextJoiner = Joiner.on("\n");
	
    private final StyleRegistry styleRegistry;
    
    private final Row row;
    private ColumnTotalsDataRange totalsData;
    
    private int index;
    private int indent;
    
    public RowContextImpl(Row row, SheetContext sheet, StyleRegistry styleRegistry, int indent) {
        super(sheet);
        this.row = row;
        this.styleRegistry = styleRegistry;
        this.index = indent;
        this.indent = indent;
    }

    @Override
    public RowContext text(String text) {
    	return writeText(text, getTextStyle());
    }

    @Override
    public RowContext text(String text, Style style) {
    	return writeText(text, getTextStyle(), style);
    }

	@Override
	public RowContext optionalText(String text) {
		return text == null ? skipCell() : text(text);
	}

	@Override
	public RowContext optionalText(String text, Style style) {
		return text == null ? skipCell() : text(text, style);
	}


	@Override
	public RowContext multilineText(Collection<String> lines) {
		return writeMultilineText(lines, getTextStyle());
	}

	@Override
	public RowContext multilineText(Collection<String> lines, Style style) {
		return writeMultilineText(lines, getTextStyle(), style);
	}

    @Override
    public RowContext number(Number number) {
        return writeNumber(number, getNumberStyle());
    }
    
    @Override
    public RowContext number(Number number, Style style) {
    	return writeNumber(number, getNumberStyle(), style);
    }

    @Override
    public RowContext optionalNumber(Number number) {
    	return number == null ? skipCell() : number(number);
    }

    @Override
    public RowContext optionalNumber(Number number, Style style) {
    	return number == null ? skipCell() : number(number, style);
    }

	@Override
	public RowContext date(Date date) {
		return writeDate(date, getDateStyle());
	}

    @Override
	public RowContext date(Date date, Style style) {
    	return writeDate(date, getDateStyle(), style);
	}

    @Override
    public RowContext date(LocalDate date) {
        checkLocalDateIsNotNull(date);
        return date(date.toDate());
    }

    @Override
    public RowContext date(LocalDate date, Style style) {
        checkLocalDateIsNotNull(date);
        return date(date.toDate(), style);
    }

    private void checkLocalDateIsNotNull(LocalDate date) {
        checkArgument(date != null, "trying to set null LocalDate in column %s", index);
    }

    public RowContext total(String text) {
        return writeText(text, getTotalStyle());
    }

    @Override
    public RowContext header(String text) {
        return writeText(text, getHeaderStyle());
    }
    
    @Override
    public RowContext percentage(Number number) {
    	return writeNumber(number.doubleValue() / 100, getPercentageStyle());
    }

    @Override
    public RowContext skipCell() {
        return skipCells(1);
    }

    @Override
    public RowContext skipCells(int offset) {
        index += offset;
        return this;
    }

    @Override
    public RowContext cellAt(int newIndex) {
        index = newIndex + indent;
        return this;
    }
    
    @Override
    public RowContext conditionalCell(boolean condition) {
    	return condition ? this : new RowContextNoImpl(sheet, this);
    }

	@Override
	public RowContext setColumnWidth(int width) {
		sheet.setColumnWidth(index - 1, width);
		return this;
	}

    @Override
    public RowContext setHeight(int height) {
        row.setHeight((short) (sheet.getConfiguration().getRowHeightBaseValue() * height));
        return this;
    }

    @Override
	public RowContext mergeCells(int number) {
    	sheet.mergeCells(index, index + number - 1);
		return this;
	}

	@Override
    public Row getNativeRow() {
        return row;
    }
    
    private RowContext writeText(String text, Style... styles) {
    	checkArgument(text != null, "Text is null for column %s", index);
    	
    	createCell(1, combineStyles(styles)).setCellValue(new HSSFRichTextString(text));
    	return this;
    }

    private RowContext writeMultilineText(Collection<String> lines, Style... styles) {
    	checkArgument(lines != null, "Lines is null for column %s", index);
    	
    	String text = multilineTextJoiner.join(lines);
		CompositeStyle style = combineStyles(styles).setStyle(SystemCellWrapTextStyle.WRAP_TEXT);
		
		createCell(lines.size(), style).setCellValue(new HSSFRichTextString(text));
    	return this;
    }
    
	private RowContext writeNumber(Number number, Style... styles) {
		checkArgument(number != null, "Number is null for column %s", index);
		
		createCell(1, combineStyles(styles)).setCellValue(number.doubleValue());
        return this;
	}
    
	private RowContext writeDate(Date date, Style... styles) {
		checkArgument(date != null, "Date is null for column %s", index);
		
		createCell(1, combineStyles(styles)).setCellValue(date);
        return this;
	}

    private RowContext writeFormula(String formula, Style style) {
    	checkArgument(formula != null, "Formula is null for column %s", index);
    	
        createCell(1, style).setCellFormula(formula);
        return this;
    }
    
	@VisibleForTesting
	Cell createCell(int rowHeightMultiplier, Style style) {
		row.setHeightInPoints(row.getHeightInPoints() * rowHeightMultiplier);	
		
        Cell cell = row.createCell(index++);
        cell.setCellStyle(styleRegistry.registerStyle(style));

        return cell;
	}

    @Override
    public RowContext setTotalsDataBlock(ColumnTotalsDataRange data) {
        this.totalsData = data;
        return this;
    }

    @Override
    public RowContext total(Formula formula) {
        return total(formula, getTextStyle());
    }

    @Override
    public RowContext total(Formula formula, Style style) {
        checkState(totalsData != null, "Please set totals data before rendering totals formula (setTotalsDataBlock(...)");

        String columnIndex = Columns.columnIndexAsLetters(index + 1);
        String totalString = formula.toString() + '(' + columnIndex + totalsData.getStartLineNo()
                + ":"
                + columnIndex + totalsData.getEndLineNo() + ')';
        writeFormula(totalString, style);
        return this;
    }

    @Override
    public RowContext totals(Formula formula, int times) {
        return totals(formula, times, getTextStyle());
    }

    @Override
    public RowContext totals(Formula formula, int times, Style style) {
        for (int i = 0; i < times; i++) {
            total(formula, style);
        }
        return this;
    }
}