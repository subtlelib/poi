package org.subtlelib.poi.api.style;


public interface StyleConfigurable<T> {

	public T setStyleConfiguration(StyleConfiguration styleConfiguration);
	public StyleConfiguration getStyleConfiguration();

	public T setTextStyle(Style style);
	public T setNumberStyle(Style style);
	public T setDateStyle(Style style);
	
	public T setTotalStyle(Style style);
	public T setHeaderStyle(Style style);
	public T setPercentageStyle(Style style);
	
}
