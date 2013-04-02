package org.subtlelib.poi.api.style;

/**
 * Provides style configuration per designated data type.
 * 
 * @author i.voshkulat
 *
 */
public interface StyleConfiguration {
	
	public Style getTotalStyle();
	public Style getHeaderStyle();
	public Style getPercentageStyle();
	
	public Style getTextStyle();
	public Style getNumberStyle();
	public Style getDateStyle();
	
}
