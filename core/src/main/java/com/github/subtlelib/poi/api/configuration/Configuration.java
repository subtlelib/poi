package com.github.subtlelib.poi.api.configuration;

/**
 * Workbook wide configuration.
 * 
 * @author i.voshkulat
 *
 */
public interface Configuration {

	/**
	 * @return multiplier used to convert cell width provided on cell construction into
     * actual width of excel cell in units of 1/256th of a character width.
	 */
	public double getColumnWidthBaseValue();

    /**
     * @return multiplier used to convert values passed to height-setting methods into those that Excel understands
     * ("twips" or 1/20th of a point). Default multiplier corresponds to the one in Row Height dialog in Excel (20).
     * Therefore you would set height in typographical points.
     */
    public double getRowHeightBaseValue();
}
