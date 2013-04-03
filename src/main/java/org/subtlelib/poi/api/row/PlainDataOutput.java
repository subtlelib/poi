package org.subtlelib.poi.api.row;

import java.util.Collection;
import java.util.Date;

import org.joda.time.LocalDate;
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
	 * @param text non-null string
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
	 * @param text non-null string
	 * @param style style to be applied to the current cell on top of the predefined style
	 */
    public RowContext text(String text, Style style);
    
    /**
     * Write optional text to the current cell.
     * 
     * Same as {@link #text(String)} but the value is optional.
     *  
     * @param text string or null, for null no value is written to the cell
     */
    public RowContext optionalText(String text);

    /**
     * Write optional text to the current cell and apply style provided.
     * 
     * Same as {@link #text(String, Style)} but the value is optional.
     *  
     * @param text string or null, for null no value is written to the cell
	 * @param style style to be applied to the current cell on top of the predefined style 
     */
    public RowContext optionalText(String text, Style style);

	/**
	 * Write mandatory multiline text to the current cell.
	 * Shift current cell pointer to the next position.
	 * 
	 * Style as per {@link StyleConfiguration#getTextStyle()} on the current level is applied.
	 * For details of style configuration please see {@link StyleConfiguration} implementation used.
	 * 
	 * @param lines non-null collection of strings
	 */
    public RowContext multilineText(Collection<String> lines);
    
	/**
	 * Write mandatory multiline text to the current cell.
	 * Shift current cell pointer to the next position.
	 * 
	 * Style which is a product of merging/overwriting {@link StyleConfiguration#getTextStyle()} with a provided style is applied.
	 * For details of style configuration please see {@link StyleConfiguration} implementation used.
	 * For details of style merging see actual {@link Style} in use.
	 * 
	 * @param lines non-null collection of strings
	 * @param style style to be applied to the current cell on top of the predefined style
	 */
    public RowContext multilineText(Collection<String> lines, Style style);
    
	/**
	 * Write mandatory number to the current cell.
	 * Shift current cell pointer to the next position.
	 * 
	 * Style as per {@link StyleConfiguration#getNumberStyle()} on the current level is applied.
	 * For details of style configuration please see {@link StyleConfiguration} implementation used.
	 * 
	 * @param number non-null number
	 */
    public RowContext number(Number number);
    
	/**
	 * Write mandatory number to the current cell.
	 * Shift current cell pointer to the next position.
	 * 
	 * Style which is a product of merging/overwriting {@link StyleConfiguration#getNumberStyle()} with a provided style is applied.
	 * For details of style configuration please see {@link StyleConfiguration} implementation used.
	 * For details of style merging see actual {@link Style} in use.
	 * 
	 * @param number non-null number
	 * @param style style to be applied to the current cell on top of the predefined style 
	 */    
    public RowContext number(Number number, Style style);
    
    /**
     * Write optional number to the current cell.
     * 
     * Same as {@link #number(Number)} but the value is optional.
     *  
     * @param number number or null, for null no value is written to the cell
     */    
    public RowContext optionalNumber(Number number);

    /**
     * Write optional number to the current cell and apply style provided.
     * 
     * Same as {@link #number(Number, Style)} but the value is optional.
     *  
     * @param number number or null, for null no value is written to the cell
	 * @param style style to be applied to the current cell on top of the predefined style 
     */    
    public RowContext optionalNumber(Number number, Style style);
    
	/**
	 * Write mandatory date to the current cell.
	 * Shift current cell pointer to the next position.
	 * 
	 * Style as per {@link StyleConfiguration#getDateStyle()} on the current level is applied.
	 * For details of style configuration please see {@link StyleConfiguration} implementation used.
	 * For details of style merging see actual {@link Style} in use.
	 * 
	 * @param date non-null date
	 */        
    public RowContext date(Date date);
    
	/**
	 * Write mandatory date to the current cell.
	 * Shift current cell pointer to the next position.
	 * 
	 * Style which is a product of merging/overwriting {@link StyleConfiguration#getDateStyle()} with a provided style is applied.
	 * For details of style configuration please see {@link StyleConfiguration} implementation used.
	 * For details of style merging see actual {@link Style} in use.
	 * 
	 * @param date non-null date
	 * @param style style to be applied to the current cell on top of the predefined style 
	 */        
    public RowContext date(Date date, Style style);
    public RowContext date(LocalDate date);
    public RowContext date(LocalDate date, Style style);

}
