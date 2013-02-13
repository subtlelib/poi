package org.subtlelib.poi.api.style.ext;

import org.subtlelib.poi.api.style.Style;

public interface CompositeStyle extends Style, TextFormatStyle, DataFormatStyle {

	/**
	 * Merge style with another style.
	 * This has a priority over the other.
	 */
	public CompositeStyle merge(Style other);

}
