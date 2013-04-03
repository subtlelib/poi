package org.subtlelib.poi.api.totals;

import org.subtlelib.poi.api.style.Style;

/**
 * Created on 28/03/13
 * @author d.serdiuk
 */
public interface SupportsColumnTotalsRendering<R> {
    R setTotalsDataBlock(ColumnTotalsDataRange data);
    R total(Formula formula);
    R total(Formula formula, Style style);
    R totals(Formula formula, int times);
    R totals(Formula formula, int times, Style style);
}
