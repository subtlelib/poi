package io.github.subtlelib.poi.api.style;

/**
 * Provides style configuration per designated data type.
 * 
 * @author i.voshkulat
 *
 */
public interface StyleConfiguration {
	
	Style getTotalStyle();
	Style getHeaderStyle();
	Style getPercentageStyle();
	
	Style getTextStyle();
	Style getNumberStyle();
	Style getDateStyle();
	
}
