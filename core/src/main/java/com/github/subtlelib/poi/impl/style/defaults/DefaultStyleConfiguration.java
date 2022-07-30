package com.github.subtlelib.poi.impl.style.defaults;

import com.github.subtlelib.poi.api.style.Style;
import com.github.subtlelib.poi.api.style.StyleConfiguration;

/**
 * A default implementation sitting on the top of the hierarchy.
 * Not expected to be used as a root for the style hierarchy of your own.
 * Please consider enum-based implementation of {@link StyleConfiguration} and reuse it across the application.
 * 
 * @author i.voshkulat
 */
public class DefaultStyleConfiguration implements StyleConfiguration {

	@Override
	public Style getTextStyle() {
        return EmptyStyle.instance;
    }

	@Override
	public Style getNumberStyle() {
        return EmptyStyle.instance;
    }

	@Override
	public Style getDateStyle() {
        return DataStyle.DATE;
    }

	@Override
	public Style getTotalStyle() {
        return EmptyStyle.instance;
    }

	@Override
	public Style getHeaderStyle() {
        return FontStyle.COLUMN_HEADER;
    }

	@Override
	public Style getPercentageStyle() {
        return EmptyStyle.instance;
    }

}