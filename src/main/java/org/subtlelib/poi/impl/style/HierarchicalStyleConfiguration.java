package org.subtlelib.poi.impl.style;

import org.subtlelib.poi.api.style.Style;
import org.subtlelib.poi.api.style.StyleConfigurable;
import org.subtlelib.poi.api.style.StyleConfiguration;
import org.subtlelib.poi.api.style.ext.CompositeStyle;

@SuppressWarnings("unchecked")
public abstract class HierarchicalStyleConfiguration<T> implements StyleConfiguration, StyleConfigurable<T> {

	private final StyleConfiguration parentConfig;

	private Style textStyle;
	private Style numberStyle;
	private Style dateStyle;

	private Style totalStyle;
	private Style headerStyle;
	private Style percentageStyle;

	public HierarchicalStyleConfiguration(StyleConfiguration parentConfig) {
		this.parentConfig = parentConfig;
	}
	
	@Override
	public Style getTotalStyle() {
		return merge(totalStyle, parentConfig.getTotalStyle());
	}

	@Override
	public T setTotalStyle(Style style) {
		this.totalStyle = style;
		return (T) this;
	}

	@Override
	public Style getHeaderStyle() {
		return merge(headerStyle, parentConfig.getHeaderStyle());
	}

	@Override
	public T setHeaderStyle(Style style) {
		this.headerStyle = style;
		return (T) this;
	}

	@Override
	public Style getPercentageStyle() {
		return merge(percentageStyle, parentConfig.getPercentageStyle());
	}

	@Override
	public T setPercentageStyle(Style style) {
		this.percentageStyle = style;
		return (T) this;
	}
	
	@Override
	public Style getTextStyle() {
		return merge(textStyle, parentConfig.getTextStyle());
	}

	@Override
	public T setTextStyle(Style style) {
		this.textStyle = style;
		return (T) this;
	}

	@Override
	public Style getNumberStyle() {
		return merge(numberStyle, parentConfig.getNumberStyle());
	}

	@Override
	public T setNumberStyle(Style style) {
		this.numberStyle = style;
		return (T) this;
	}

	@Override
	public Style getDateStyle() {
		return merge(dateStyle, parentConfig.getDateStyle());
	}

	@Override
	public T setDateStyle(Style style) {
		this.dateStyle = style;
		return (T) this;
	}
	
	@Override
	public T setStyleConfiguration(StyleConfiguration styleConfiguration) {
		this.headerStyle = styleConfiguration.getHeaderStyle();
		this.totalStyle = styleConfiguration.getTotalStyle();
		this.textStyle = styleConfiguration.getTextStyle();
		this.numberStyle = styleConfiguration.getNumberStyle();
		this.percentageStyle = styleConfiguration.getPercentageStyle();
		this.dateStyle = styleConfiguration.getDateStyle();
		return (T) this;
	}

	@Override
	public StyleConfiguration getStyleConfiguration() {
		return this;
	}

	private Style merge(Style style, Style parentStyle) {
		if (style == null) {
			return parentStyle;
		}
		
		if (style instanceof CompositeStyle) {
			return CompositeStyle.class.cast(style).merge(parentStyle);
		}
		
		return style;
	}
	
}