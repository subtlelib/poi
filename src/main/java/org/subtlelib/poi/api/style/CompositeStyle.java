package org.subtlelib.poi.api.style;

import java.util.Collection;

public interface CompositeStyle extends Style {

	public Collection<Style> getStyles();
	public CompositeStyle setStyle(Style style);
	
}
