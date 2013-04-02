package org.subtlelib.poi.api.row;

import org.subtlelib.poi.api.style.StyleConfiguration;

/**
 * Custom data output functionality
 * 
 * @author i.voshkulat
 *
 */
public interface FormattedDataOutput {

    public RowContext header(String text);
    public RowContext total(String text);

	/**
	 * Write mandatory percentage to the current cell.
	 * Shift current cell pointer to the next position.
	 * 
	 * Value is an integer or decimal percentage, not the fraction of 1.
	 * 
	 * Style as per {@link StyleConfiguration#getPercentageStyle()} on the current level is applied.
	 * For details of style configuration please see {@link StyleConfiguration} implementation used.
	 * 
	 * @param number non-null percentage
	 */
    public RowContext percentage(Number number);

}
