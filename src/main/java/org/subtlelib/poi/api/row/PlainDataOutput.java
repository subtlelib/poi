package org.subtlelib.poi.api.row;

import java.util.Date;

import org.subtlelib.poi.api.style.Style;
import org.subtlelib.poi.api.style.StyleConfiguration;

/**
 * Basic data output functionality
 * 
 * @author i.voshkulat
 *
 */
public interface PlainDataOutput {

	/**
	 * Write mandatory text to the current cell.
	 * Shift current cell pointer to the next position.
	 * 
	 * Style as per {@link StyleConfiguration#getTextStyle()} on the current level is applied.
	 * For details of style configuration please see {@link StyleConfiguration} implementation used.
	 * 
	 * @param text non-null String
	 */
    public RowContext text(String text);

	/**
	 * Write mandatory text to the current cell.
	 * Shift current cell pointer to the next position.
	 * 
	 * Style which is a product of merging/overwriting {@link StyleConfiguration#getTextStyle()} with a provided style is applied.
	 * For details of style configuration please see {@link StyleConfiguration} implementation used.
	 * For details of style merging see actual {@link Style} in use.
	 * 
	 * @param text non-null String
	 */
    public RowContext text(String text, Style style);
    
    /**
     * Write optional text to the current cell.
     * 
     * Same as {@link #text(String)} but the text is optional.
     *  
     * @param text null is treated as an empty value
     */
    public RowContext optionalText(String text);

    /**
     * Write optional text to the current cell and apply style provided.
     * 
     * Same as {@link #text(String, Style)} but the text is optional.
     *  
     * @param text null is treated as an empty value
     */
    public RowContext optionalText(String text, Style style);
	
    public RowContext number(Number number);
    public RowContext number(Number number, Style style);
    public RowContext optionalNumber(Number number);
    public RowContext optionalNumber(Number number, Style style);
    
    public RowContext date(Date date);
    public RowContext date(Date date, Style style);

}
