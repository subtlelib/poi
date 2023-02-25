package io.github.subtlelib.poi.api.navigation;

import io.github.subtlelib.poi.api.sheet.SheetConfiguration;

/**
 * Cell navigation within the row routines.
 * 
 * @author i.voshkulat
 *
 * @param <T> implementing builder type
 */
public interface CellNavigation<T> {

	/**
	 * Move current cell pointer to the next position.
     * @return this
	 */
    T skipCell();

    /**
     * Move current cell pointer by {@code offset} cells within the row.
     * 
     * @param offset number of cells to move pointer by
     * @return this
     */
    T skipCells(int offset);

    /**
     * Place current cell pointer in the {@code newIndex}-th position in the row.
     * Subject to an additional shift as set by {@link SheetConfiguration#setDefaultRowIndent(int)}.
     * Therefore: absolute position = newIndex + page wide indentation 
     * 
     * @param newIndex new pointer absolute position
     * @return this
     */
    T cellAt(int newIndex);

}
