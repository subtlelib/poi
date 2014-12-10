package org.subtlelib.poi.api.filter;

/**
 * Created by beni on 10.12.14.
 */
public interface SupportsFilterRendering<T> {
	T setFilterDataRange(FilterDataRange data);
	T filter();
}
