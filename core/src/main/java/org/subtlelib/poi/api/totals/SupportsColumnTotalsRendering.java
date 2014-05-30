package org.subtlelib.poi.api.totals;

import org.subtlelib.poi.api.style.Style;

/**
 * Created on 28/03/13
 * @author d.serdiuk
 */
public interface SupportsColumnTotalsRendering<R> {
    /**
     * The data range will be used to render totals.
     * <b>If the end of data range has not yet been marked, {@link ColumnTotalsDataRange#endOnPreviousRow()}
     * method will be called</b>
     *
     * @param data data range to be used for totals calculation
     * @return this
     */
    R setTotalsDataRange(ColumnTotalsDataRange data);
    R total(Formula formula);
    R total(Formula formula, Style style);
    R totals(Formula formula, int times);
    R totals(Formula formula, int times, Style style);
}
