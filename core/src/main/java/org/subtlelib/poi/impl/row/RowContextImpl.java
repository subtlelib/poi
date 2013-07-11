package org.subtlelib.poi.impl.row;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.util.Collection;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.subtlelib.poi.api.row.RowContext;
import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.api.style.Style;
import org.subtlelib.poi.api.style.StyleRegistry;
import org.subtlelib.poi.api.totals.ColumnTotalsDataRange;
import org.subtlelib.poi.api.totals.Formula;
import org.subtlelib.poi.impl.column.Columns;
import org.subtlelib.poi.impl.style.StylesInternal;
import org.subtlelib.poi.impl.style.system.SystemCellWrapTextStyle;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;


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
    	return writeText(text, StylesInternal.combineOrOverride(getTextStyle(), style));
    }

    @Override
    public RowContext text(Optional<String> text) {
        return text.isPresent()? text(text.get()) : skipCell();
    }

    @Override
    public RowContext text(Optional<String> text, Style style) {
        return text.isPresent() ? text(text.get(), style) : skipCell();
    }


    @Override
	public RowContext multilineText(Collection<String> lines) {
		return writeMultilineText(lines, getTextStyle());
	}

	@Override
	public RowContext multilineText(Collection<String> lines, Style style) {
		return writeMultilineText(lines, StylesInternal.combineOrOverride(getTextStyle(), style));
	}

    @Override
    public RowContext number(Number number) {
        return writeNumber(number, getNumberStyle());
    }
    
    @Override
    public RowContext number(Number number, Style style) {
    	return writeNumber(number, StylesInternal.combineOrOverride(getNumberStyle(), style));
    }

    @Override
    public RowContext number(Optional<? extends Number> number) {
        return number.isPresent() ? number(number.get()) : skipCell();
    }

    @Override
    public RowContext number(Optional<? extends Number> number, Style style) {
        return number.isPresent() ? number(number.get(), style) : skipCell();
    }

    @Override
	public RowContext date(Date date) {
		return writeDate(date, getDateStyle());
	}

    @Override
	public RowContext date(Date date, Style style) {
    	return writeDate(date, StylesInternal.combineOrOverride(getDateStyle(), style));
	}

    @Override
    public RowContext date(Optional<Date> date) {
        return date.isPresent() ? date(date.get()) : skipCell();
    }

    @Override
    public RowContext date(Optional<Date> date, Style style) {
        return date.isPresent() ? date(date.get(), style) : skipCell();
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
    public RowContext setRowHeight(int height) {
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
    
    private RowContext writeText(String text, Style style) {
    	checkArgument(text != null, "Text is null for column %s", index);
    	
    	createCell(1, style).setCellValue(new HSSFRichTextString(text));
    	return this;
    }

    private RowContext writeMultilineText(Collection<String> lines, Style style) {
    	checkArgument(lines != null, "Lines is null for column %s", index);
    	
    	String text = multilineTextJoiner.join(lines);
		Style styleWithWrapped = StylesInternal.combineOrOverride(style, SystemCellWrapTextStyle.WRAP_TEXT);
		
		createCell(lines.size(), styleWithWrapped).setCellValue(new HSSFRichTextString(text));
    	return this;
    }
    
	private RowContext writeNumber(Number number, Style style) {
		checkArgument(number != null, "Number is null for column %s", index);
		
		createCell(1, style).setCellValue(number.doubleValue());
        return this;
	}
    
	private RowContext writeDate(Date date, Style style) {
		checkArgument(date != null, "Date is null for column %s", index);
		
		createCell(1, style).setCellValue(date);
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
    public RowContext setTotalsDataRange(ColumnTotalsDataRange data) {
        if (!data.isEndMarked()) {
            data.endOnPreviousRow();
        }
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
        String totalString = formula.toString() + '(' + columnIndex + totalsData.getStartRowNo()
                + ":"
                + columnIndex + totalsData.getEndRowNo() + ')';
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