package org.subtlelib.poi.api.totals;

/**
 * An instance of this interface represents the contiguous range of rows that can be used as a source
 * for totals formula.<br/>
 * Data range is started when the instance is created, and ended when {@link #end()} called explicitly or when
 * the object is used to render totals.
 *
 * <p/>
 * Created on 28/03/13
 * @author d.serdiuk
 */
public interface ColumnTotalsDataRange {
    /**
     * You can omit calling this method directly if the formula is generated on the line following the last line
     * of the data range. If you want to leave some lines between the data range and the formula that uses it,
     * call this method when you finished writing your data.
     */
    void end();
    int getStartLineNo();
    int getEndLineNo();
}
