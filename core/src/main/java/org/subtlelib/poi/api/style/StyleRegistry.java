package org.subtlelib.poi.api.style;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Workbook style registry.
 * Converts library's {@link Style} into POI style and registers it with the current workbook.
 * 
 * @author i.voshkulat
 *
 */
public interface StyleRegistry {

	/**
	 * Convert provided {@link Style} into POI style and register it with current workbook.
	 * Resulting style can be assigned directly to a POI cell.
	 * 
	 * @param style style to be converted and registered
	 * @return POI representation of the provided style
	 */
	public CellStyle registerStyle(Style style);
	
}
