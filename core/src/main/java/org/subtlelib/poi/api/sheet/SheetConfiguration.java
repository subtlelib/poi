package org.subtlelib.poi.api.sheet;

import org.subtlelib.poi.api.configuration.Configuration;
import org.subtlelib.poi.api.navigation.CellNavigation;
import org.subtlelib.poi.api.row.RowContext;

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
	public T setColumnWidth(int columnNumber, int width);
	
	/**
	 * Set widths for subsequent columns starting from the first one.
	 * 
	 * @param widths each width in units, subject to multiplication - see {@link Configuration#getColumnWidthBaseValue()}
     * @return this
	 */
	public T setColumnWidths(int... widths);
	
	/**
	 * Hide grid lines for current sheet.
     * @return this
	 */
	public T hideGrid();
	
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
	public T setDefaultRowIndent(int indent);

    public T fitOnPagesByWidth(int pages);
    public T fitOnPagesByHeight(int pages);

}
