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
        return text(text, getTextStyle());
    }

    @Override
    public RowContext text(String text, Style style) {
    	checkArgument(text != null, "Text is null");
    	
        HSSFCell cell = row.createCell(index);
        cell.setCellValue(new HSSFRichTextString(text));
        cell.setCellStyle(styleRegistry.registerStyle(style));

        index++;
        return this;
    }

	@Override
	public RowContext optionalText(String text) {
		return optionalText(text, getTextStyle());
	}

	@Override
	public RowContext optionalText(String text, Style style) {
		return text == null ? skipCell() : text(text, style);
	}

    @Override
    public RowContext number(Number number) {
        return number(number, getNumberStyle());
    }
    
    @Override
    public RowContext number(Number number, Style style) {
    	checkArgument(number != null, "Number is null");
    	
        HSSFCell cell = row.createCell(index);
        cell.setCellValue(number.doubleValue());
        cell.setCellStyle(styleRegistry.registerStyle(style));
        
        index++;
        return this;
    }

    @Override
    public RowContext optionalNumber(Number number) {
    	return optionalNumber(number, getNumberStyle());
    }

    @Override
    public RowContext optionalNumber(Number number, Style style) {
    	return number == null ? skipCell() : number(number, style);
    }

	@Override
	public RowContext date(Date date) {
		return date(date, getDateStyle());
	}

    @Override
	public RowContext date(Date date, Style style) {
    	checkArgument(date != null, "Date is null");
    	
        HSSFCell cell = row.createCell(index);
        cell.setCellValue(date);
        cell.setCellStyle(styleRegistry.registerStyle(style));
        
        index++;
        return this;
	}
    
    @Override
    public RowContext total(String text) {
        return text(text, getTotalStyle());
    }

    @Override
    public RowContext header(String caption) {
        return text(caption, getHeaderStyle());
    }
    
    @Override
    public RowContext percentage(Number number) {
    	return number(number.doubleValue() / 100, getPercentageStyle());
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

}
