package io.github.subtlelib.poi.api.style;


public interface StyleConfigurable<T> {

	T setStyleConfiguration(StyleConfiguration styleConfiguration);
	StyleConfiguration getStyleConfiguration();

	T setTextStyle(Style style);
	T setNumberStyle(Style style);
	T setDateStyle(Style style);
	
	T setTotalStyle(Style style);
	T setHeaderStyle(Style style);
	T setPercentageStyle(Style style);
	
}
