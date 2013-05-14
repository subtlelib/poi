package org.subtlelib.poi.api.navigation;

import org.subtlelib.poi.api.condition.RowCondition;
import org.subtlelib.poi.api.row.RowContext;

/**
 * Row navigation within the sheet routines.
 * 
 * @author i.voshkulat
 *
 * @param <S> sheet context type
 * @param <R> row context type
 */
public interface RowNavigation<S, R> extends RowCondition<R> {

	/**
	 * Create subsequent row and place current row pointer on it.
	 * 
	 * @return created row
	 */
    public R nextRow();
    
	/**
	 * Retrieve current row, i.e. last row <b>created</b> (see {@link #nextRow()} and {@link #nextConditionalRow(boolean)}) within current sheet.
	 * Is not affected by {@link #skipRow()} or {@link #skipRows(int)}.
	 * 
	 * @return current row {@link RowContext}
	 */
	public R currentRow();
	
    /**
     * Move current row pointer to the next position within the sheet.
     * Doesn't involve actual row creation but just the position in which the next row will be created with {@link #nextRow()} or {@link #nextConditionalRow(boolean).
     */
    public S skipRow();
    
    /**
     * Move current row pointer by {@code offset} rows within the sheet.
     * Doesn't involve actual row creation but just the position in which the next row will be created with {@link #nextRow()} or {@link #nextConditionalRow(boolean).
     * 
     * @param offset number of rows to move pointer by
     */
    public S skipRows(int offset);

    public S stepOneRowBack();
}
