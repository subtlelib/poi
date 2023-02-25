package io.github.subtlelib.poi.api.sheet;

import io.github.subtlelib.poi.api.configuration.Configuration;
import io.github.subtlelib.poi.api.navigation.CellNavigation;
import io.github.subtlelib.poi.api.row.RowContext;

/**
 * Sheet configuration routines.
 * 
 * @author i.voshkulat
 *
 * @param <T> implementing builder type
 */
public interface SheetConfiguration<T> {

	/**
	 * Set column width.
	 * 
	 * @param columnNumber index of the column to set width for
	 * @param width width in units, subject to multiplication - see {@link Configuration#getColumnWidthBaseValue()}
     * @return this
	 */
	@SuppressWarnings("UnusedReturnValue") // for consistency with other similar methods
	T setColumnWidth(int columnNumber, int width);
	
	/**
	 * Set widths for subsequent columns starting from the first one.
	 * 
	 * @param widths each width in units, subject to multiplication - see {@link Configuration#getColumnWidthBaseValue()}
     * @return this
	 */
	T setColumnWidths(int... widths);
	
	/**
	 * Hide grid lines for current sheet.
     * @return this
	 */
	T hideGrid();
	
	/**
	 * Set sheet-wide indentation.
	 * 
	 * Defines initial position of current cell pointer on a newly created {@link RowContext} which in due order affects all the cell navigation and output operations. 
	 * Shifts position set by {@link CellNavigation#cellAt(int)}.
	 *  
	 * Default: 0.
	 * 
	 * @param indent indentation in number of cells
     * @return this
	 */
	T setDefaultRowIndent(int indent);

    T fitOnPagesByWidth(int pages);
    T fitOnPagesByHeight(int pages);

}
