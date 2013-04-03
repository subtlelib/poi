package org.subtlelib.poi.impl.style;

import static com.google.common.base.Preconditions.checkArgument;

import org.subtlelib.poi.api.style.CompositeStyle;
import org.subtlelib.poi.api.style.Style;
import org.subtlelib.poi.api.style.StyleConfigurable;
import org.subtlelib.poi.api.style.StyleConfiguration;

@SuppressWarnings("unchecked")
public abstract class InheritableStyleConfiguration<T> implements StyleConfiguration, StyleConfigurable<T> {

	private CompositeStyle textStyle;
	private CompositeStyle numberStyle;
	private CompositeStyle dateStyle;

	private CompositeStyle totalStyle;
	private CompositeStyle headerStyle;
	private CompositeStyle percentageStyle;

	public InheritableStyleConfiguration(StyleConfiguration parentConfig) {
		overwriteStyles(parentConfig);
	}

	private void overwriteStyles(StyleConfiguration parentConfig) {
		textStyle = new CompositeStyleImpl(parentConfig.getTextStyle());
		numberStyle = new CompositeStyleImpl(parentConfig.getNumberStyle());
		dateStyle = new CompositeStyleImpl(parentConfig.getDateStyle());
		totalStyle = new CompositeStyleImpl(parentConfig.getTotalStyle());
		headerStyle = new CompositeStyleImpl(parentConfig.getHeaderStyle());
		percentageStyle = new CompositeStyleImpl(parentConfig.getPercentageStyle());
	}
	
	@Override
	public Style getTotalStyle() {
		return totalStyle;
	}

	@Override
	public T setTotalStyle(Style style) {
		totalStyle.setStyle(style);
		return (T) this;
	}

	@Override
	public Style getHeaderStyle() {
		return headerStyle;
	}

	@Override
	public T setHeaderStyle(Style style) {
		this.headerStyle.setStyle(style);
		return (T) this;
	}

	@Override
	public Style getPercentageStyle() {
		return percentageStyle;
	}

	@Override
	public T setPercentageStyle(Style style) {
		this.percentageStyle.setStyle(style);
		return (T) this;
	}
	
	@Override
	public Style getTextStyle() {
		return textStyle;
	}

	@Override
	public T setTextStyle(Style style) {
		this.textStyle.setStyle(style);
		return (T) this;
	}

	@Override
	public Style getNumberStyle() {
		return numberStyle;
	}

	@Override
	public T setNumberStyle(Style style) {
		this.numberStyle.setStyle(style);
		return (T) this;
	}

	@Override
	public Style getDateStyle() {
		return dateStyle;
	}

	@Override
	public T setDateStyle(Style style) {
		this.dateStyle.setStyle(style);
		return (T) this;
	}
	
	@Override
	public T setStyleConfiguration(StyleConfiguration styleConfiguration) {
		overwriteStyles(styleConfiguration);
		return (T) this;
	}

	@Override
	public StyleConfiguration getStyleConfiguration() {
		return this;
	}
	
	protected CompositeStyle combineStyles(Style... styles) {
		checkArgument(styles != null, "List of styles to combine is null");
		checkArgument(styles.length > 0, "List of styles to combine is empty");
		
		CompositeStyle mergedStyle = new CompositeStyleImpl(styles[0]);
		for (int i = 1; i < styles.length; i++) {
			mergedStyle.setStyle(styles[i]);
		}

		return mergedStyle;
	}
	
}