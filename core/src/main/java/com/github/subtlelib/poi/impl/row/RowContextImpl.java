package com.github.subtlelib.poi.impl.row;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import com.github.subtlelib.poi.impl.column.Columns;
import com.github.subtlelib.poi.impl.style.StylesInternal;
import com.github.subtlelib.poi.impl.style.system.SystemCellWrapTextStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import com.github.subtlelib.poi.api.row.RowContext;
import com.github.subtlelib.poi.api.sheet.SheetContext;
import com.github.subtlelib.poi.api.style.Style;
import com.github.subtlelib.poi.api.style.StyleRegistry;
import com.github.subtlelib.poi.api.totals.ColumnTotalsDataRange;
import com.github.subtlelib.poi.api.totals.Formula;

import static java.util.Objects.requireNonNull;


public class RowContextImpl extends AbstractDelegatingRowContext {

    private static final int ROW_HEIGHT_AUTOMATIC = -1;

    private final StyleRegistry styleRegistry;

    private final Row row;
    private ColumnTotalsDataRange totalsData;

    private int index;
    private final int indent;
    private short rowHeight;
    private int step;

    public RowContextImpl(Row row, SheetContext sheet, StyleRegistry styleRegistry, int indent) {
        super(sheet);
        this.row = row;
        this.styleRegistry = styleRegistry;
        this.index = indent;
        this.indent = indent;
        this.step = 1;
        this.rowHeight = ROW_HEIGHT_AUTOMATIC;
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
	public RowContext date(LocalDate date) {
		return writeDate(date, getDateStyle());
	}

    @Override
	public RowContext date(LocalDate date, Style style) {
    	return writeDate(date, StylesInternal.combineOrOverride(getDateStyle(), style));
	}

    @Override
    public RowContext date(Optional<LocalDate> date) {
        return date.isPresent() ? date(date.get()) : skipCell();
    }

    @Override
    public RowContext date(Optional<LocalDate> date, Style style) {
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
    public RowContext percentage(Optional<? extends Number> number) {
        return number.isPresent() ? percentage(number.get()) : skipCell();
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
        rowHeight = (short) (sheet.getConfiguration().getRowHeightBaseValue() * height);
        return this;
    }

    @Override
	public RowContext mergeCells(int number) {
    	sheet.mergeCells(index, index + number - 1);
        step = number;
		return this;
	}

	@Override
    public Row getNativeRow() {
        return row;
    }

    private RowContext writeText(String text, Style style) {
    	requireNonNull(text, () -> "Text is null for column " + index);

    	createCell(1, style).setCellValue(text);
    	return this;
    }

    private RowContext writeMultilineText(Collection<String> lines, Style style) {
    	requireNonNull(lines, () -> "Lines is null for column" + index);

    	String text = String.join("\n", lines);
		Style styleWithWrapped = StylesInternal.combineOrOverride(style, SystemCellWrapTextStyle.WRAP_TEXT);

		createCell(lines.size(), styleWithWrapped).setCellValue(text);
    	return this;
    }

	private RowContext writeNumber(Number number, Style style) {
		requireNonNull(number, () -> "Number is null for column " + index);

		createCell(1, style).setCellValue(number.doubleValue());
        return this;
	}

	private RowContext writeDate(LocalDate date, Style style) {
        requireNonNull(date, () -> "Date is null for column " + index);

		createCell(1, style).setCellValue(date);
        return this;
	}

    @SuppressWarnings("UnusedReturnValue") // for consistency with the other methods
    private RowContext writeFormula(Formula formula, Style style) {
    	requireNonNull(formula, () -> "Formula is null for column " + index);
        if (totalsData == null) {
            throw new IllegalStateException("Please set totals data before rendering totals formula " +
                    "(setTotalsDataBlock(...)");
        }

        String columnIndex = Columns.columnIndexAsLetters(index + 1);
        String totalString = formula.toString() + '(' + columnIndex + totalsData.getStartRowNo()
                + ":"
                + columnIndex + totalsData.getEndRowNo() + ')';

        Cell cell = createCell(1, style);
        cell.setCellFormula(totalString);
        cacheEvaluatedFormula(cell);

        return this;
    }

    private void cacheEvaluatedFormula(Cell cell) {
        Workbook workbook = sheet.getNativeSheet().getWorkbook();
        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        formulaEvaluator.evaluateInCell(cell);
    }

	private Cell createCell(int rowHeightMultiplier, Style style) {
        assignRowHeight(rowHeightMultiplier);

        Cell cell = row.createCell(index);
        cell.setCellStyle(styleRegistry.registerStyle(style));

        index += step;
        step = 1;

        return cell;
	}

    /**
     * Define row height as follows:
     * - row height set explicitly by user: value defined by user;
     * - multiline output and no row height defined: default height * number of lines;
     * - otherwise: auto row height (by setting height to a magic value of #ROW_HEIGHT_AUTOMATIC);
     */
    private void assignRowHeight(int rowHeightMultiplier) {
        if (rowHeightMultiplier > 1 && rowHeight == ROW_HEIGHT_AUTOMATIC) {
            row.setHeightInPoints(row.getHeightInPoints() * rowHeightMultiplier);
        } else {
            row.setHeight(rowHeight);
        }
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
        writeFormula(formula, getTotalStyle());
        return this;
    }

    @Override
    public RowContext total(Formula formula, Style style) {
        writeFormula(formula, StylesInternal.combineOrOverride(getTotalStyle(), style));
        return this;
    }

    @Override
    public RowContext totals(Formula formula, int times) {
        for (int i = 0; i < times; i++) {
            writeFormula(formula, getTotalStyle());
        }
        return this;
    }

    @Override
    public RowContext totals(Formula formula, int times, Style style) {
        Style combinedStyle = StylesInternal.combineOrOverride(getTotalStyle(), style);
        for (int i = 0; i < times; i++) {
            writeFormula(formula, combinedStyle);
        }
        return this;
    }
}