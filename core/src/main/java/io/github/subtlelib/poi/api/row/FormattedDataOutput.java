package io.github.subtlelib.poi.api.row;

import io.github.subtlelib.poi.api.style.StyleConfiguration;

import java.util.Optional;

/**
 * Custom data output functionality
 * 
 * @author i.voshkulat
 *
 */
public interface FormattedDataOutput {

    RowContext header(String text);
    RowContext total(String text);

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
     * @return this
	 */
	RowContext percentage(Number number);

    RowContext percentage(Optional<? extends Number> number);

}
