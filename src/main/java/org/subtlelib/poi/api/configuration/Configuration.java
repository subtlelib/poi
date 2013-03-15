package org.subtlelib.poi.api.configuration;

/**
 * Workbook wide configuration.
 * 
 * @author i.voshkulat
 *
 */
public interface Configuration {

	/**
	 * Multiplier used to convert cell width provided on cell construction into actual width of excel cell in units of 1/256th of a character width.
	 * 
	 * @return multiplier
	 */
	public double getColumnWidthBaseValue();
	
}
