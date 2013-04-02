package org.subtlelib.poi.api.style;

import java.util.Collection;

/**
 * Holds a single {@link Style} or a collection of {@link SimpleStyle} instances.
 * In case of {@link SimpleStyle} a single instance of a distinct type as defined by {@link SimpleStyle#getType()} is stored.
 * 
 * @author i.voshkulat
 *
 */
public interface CompositeStyle extends Style {

	/**
	 * Get all the styles comprising current composite style.
	 * 
	 * @return non-null collections of styles
	 */
	public Collection<Style> getStyles();
	
	/**
	 * Set/add the underlying style.
	 * 
	 * @param style style to set/add to the list
	 */
	public CompositeStyle setStyle(Style style);
	
}
