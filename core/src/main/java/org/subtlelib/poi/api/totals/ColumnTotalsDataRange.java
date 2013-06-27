package org.subtlelib.poi.api.totals;

/**
 * An instance of this interface represents the contiguous range of rows that can be used as a source
 * for totals formula.<br/>
 * Data range is started when the instance is created, and ended when {@link #endOnCurrentRow()}
 * or {@link #endOnPreviousRow()} called explicitly or when the object is used to render totals.
 *
 * <p/>
 * Created on 28/03/13
 * @author d.serdiuk
 */
public interface ColumnTotalsDataRange {
    /**
     * <p>Mark the current row as the end of data range</p>
     * <p>You can omit calling this method directly if the formula is generated on the line following the last line
     * of the data range. </p>
     * If you want to leave some lines between the data range and the formula that uses it,
     * call this method when you finished writing your data.
     * @see SupportsColumnTotalsRendering#setTotalsDataRange(ColumnTotalsDataRange)
     */
    void endOnCurrentRow();
    /** 1-based line number that marks the start of the data range (line number corresponds to how it's shown in Excel */
    int getStartRowNo();
    /** 1-based line number that marks the end of the data range (line number corresponds to how it's shown in Excel */
    int getEndRowNo();

    /**
     * @return true if either {@link #endOnCurrentRow()} or {@link #endOnPreviousRow()} was called and therefore,
     * calling either of these methods will produce an error (cannot mark the end twice)
     */
    boolean isEndMarked();

    /** Mark the row rendered before current one as the end of data range */
    void endOnPreviousRow();
}
