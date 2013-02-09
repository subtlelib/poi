package org.subtlelib.poi.impl.style;

import org.subtlelib.poi.api.style.Style;
import org.subtlelib.poi.api.style.StyleConfiguration;
import org.subtlelib.poi.api.style.StyleConfigurable;

@SuppressWarnings("unchecked")
public abstract class HierarchicalStyleConfiguration<T> implements StyleConfiguration, StyleConfigurable<T> {

	private final StyleConfiguration parentConfig;

	private Style textStyle;
	private Style numberStyle;

	private Style totalStyle;
	private Style headerStyle;
	private Style percentageStyle;

	public HierarchicalStyleConfiguration(StyleConfiguration parentConfig) {
		this.parentConfig = parentConfig;
	}
	
	@Override
	public Style getTotalStyle() {
		return totalStyle != null ? totalStyle : parentConfig.getTotalStyle();
	}

	@Override
	public T setTotalStyle(Style style) {
		this.totalStyle = style;
		return (T) this;
	}

	@Override
	public Style getHeaderStyle() {
		return headerStyle != null ? headerStyle : parentConfig.getHeaderStyle();
	}

	@Override
	public T setHeaderStyle(Style style) {
		this.headerStyle = style;
		return (T) this;
	}

	@Override
	public Style getPercentageStyle() {
		return percentageStyle != null ? percentageStyle : parentConfig.getPercentageStyle();
	}

	@Override
	public T setPercentageStyle(Style style) {
		this.percentageStyle = style;
		return (T) this;
	}
	
	@Override
	public Style getTextStyle() {
		return textStyle != null ? textStyle : parentConfig.getTextStyle();
	}

	@Override
	public T setTextStyle(Style style) {
		this.textStyle = style;
		return (T) this;
	}

	@Override
	public Style getNumberStyle() {
		return numberStyle != null ? numberStyle : parentConfig.getNumberStyle();
	}

	@Override
	public T setNumberStyle(Style style) {
		this.numberStyle = style;
		return (T) this;
	}

	@Override
	public T setStyleConfiguration(StyleConfiguration styleConfiguration) {
		this.headerStyle = styleConfiguration.getHeaderStyle();
		this.totalStyle = styleConfiguration.getTotalStyle();
		this.textStyle = styleConfiguration.getTextStyle();
		this.numberStyle = styleConfiguration.getNumberStyle();
		this.percentageStyle = styleConfiguration.getPercentageStyle();
		return (T) this;
	}

	@Override
	public StyleConfiguration getStyleConfiguration() {
		return this;
	}

}