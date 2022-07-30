package com.github.subtlelib.poi.api.row;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import com.github.subtlelib.poi.api.style.Style;
import com.github.subtlelib.poi.api.style.StyleConfiguration;

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
     * @return this
	 */
	RowContext text(String text);

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
     * @return this
	 */
	RowContext text(String text, Style style);

    /**
     * Write optional text to the current cell.
     *
     * Same as {@link #text(String)} but the value is optional.
     *
     * @param text for Present string - value is written, on Absent value the cell is skipped
     * @return this
     */
	RowContext text(Optional<String> text);

    /**
     * Write optional text to the current cell and apply style provided.
     *
     * Same as {@link #text(String, Style)} but the value is optional.
     *
     * @param text for Present string - value is written, on Absent value the cell is skipped
	 * @param style style to be applied to the current cell on top of the predefined style
     * @return this
     */
	RowContext text(Optional<String> text, Style style);
    
	/**
	 * Write mandatory multiline text to the current cell.
	 * Shift current cell pointer to the next position.
	 * 
	 * Style as per {@link StyleConfiguration#getTextStyle()} on the current level is applied.
	 * For details of style configuration please see {@link StyleConfiguration} implementation used.
	 * 
	 * @param lines non-null collection of strings
     * @return this
	 */
	RowContext multilineText(Collection<String> lines);
    
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
     * @return this
	 */
	RowContext multilineText(Collection<String> lines, Style style);
    
	/**
	 * Write mandatory number to the current cell.
	 * Shift current cell pointer to the next position.
	 * 
	 * Style as per {@link StyleConfiguration#getNumberStyle()} on the current level is applied.
	 * For details of style configuration please see {@link StyleConfiguration} implementation used.
	 * 
	 * @param number non-null number
     * @return this
	 */
	RowContext number(Number number);
    
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
     * @return this
	 */
	RowContext number(Number number, Style style);
    
    /**
     * Write optional number to the current cell.
     *
     * Same as {@link #number(Number)} but the value is optional.
     *
     * @param number Present value is written, on Absent value the cell is skipped
     * @return this
     */
	RowContext number(Optional<? extends Number> number);

    /**
     * Write optional number to the current cell and apply style provided.
     *
     * Same as {@link #number(Number, Style)} but the value is optional.
     *
     * @param number Present value is written, on Absent value the cell is skipped
	 * @param style style to be applied to the current cell on top of the predefined style
     * @return this
     */
	RowContext number(Optional<? extends Number> number, Style style);

	/**
	 * Write mandatory date to the current cell.
	 * Shift current cell pointer to the next position.
	 * 
	 * Style as per {@link StyleConfiguration#getDateStyle()} on the current level is applied.
	 * For details of style configuration please see {@link StyleConfiguration} implementation used.
	 * For details of style merging see actual {@link Style} in use.
	 * 
	 * @param date non-null date
     * @return this
	 */
	RowContext date(LocalDate date);
    
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
     * @return this
	 */
	RowContext date(LocalDate date, Style style);

    RowContext date(Optional<LocalDate> date);
    RowContext date(Optional<LocalDate> date, Style style);
}
