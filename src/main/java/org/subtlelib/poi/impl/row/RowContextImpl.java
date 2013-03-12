package org.subtlelib.poi.impl.row;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.subtlelib.poi.api.row.RowContext;
import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.api.style.Style;
import org.subtlelib.poi.api.style.StyleRegistry;


public class RowContextImpl extends AbstractDelegatingRowContext {
    
    private final StyleRegistry styleRegistry;
    
    private final HSSFRow row;
    
    private int index;
    private int indent;
    
    public RowContextImpl(HSSFRow row, SheetContext sheet, StyleRegistry styleRegistry, int indent) {
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
    	return writeText(text, combineStyles(getTextStyle(), style));
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
    public RowContext number(Number number) {
        return writeNumber(number, getNumberStyle());
    }
    
    @Override
    public RowContext number(Number number, Style style) {
    	return writeNumber(number, combineStyles(getNumberStyle(), style));
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
    	return writeDate(date, combineStyles(getDateStyle(), style));
	}

    @Override
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
    public RowContext skipCells(int number) {
        index += number;
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
    public HSSFRow getNativeRow() {
        return row;
    }
    
    private RowContext writeText(String text, Style style) {
    	createCell(text, style).setCellValue(new HSSFRichTextString(text));
    	return this;
    }
    
	private RowContext writeNumber(Number number, Style style) {
		createCell(number, style).setCellValue(number.doubleValue());
        return this;
	}
    
	private RowContext writeDate(Date date, Style style) {
		createCell(date, style).setCellValue(date);
        return this;
	}
    
	private HSSFCell createCell(Object value, Style style) {
		checkArgument(value != null, "Value is null for column %s", index);
    	
        HSSFCell cell = row.createCell(index++);
        cell.setCellStyle(styleRegistry.registerStyle(style));

        return cell;
	}
    
}