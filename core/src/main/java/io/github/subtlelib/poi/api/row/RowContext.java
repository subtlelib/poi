package io.github.subtlelib.poi.api.row;

import io.github.subtlelib.poi.api.condition.CellCondition;
import io.github.subtlelib.poi.api.configuration.Configuration;
import io.github.subtlelib.poi.api.navigation.CellNavigation;
import io.github.subtlelib.poi.api.navigation.RowNavigation;
import io.github.subtlelib.poi.api.sheet.SheetContext;
import io.github.subtlelib.poi.api.style.StyleConfigurable;
import io.github.subtlelib.poi.api.style.StyleConfiguration;
import io.github.subtlelib.poi.api.totals.SupportsColumnTotalsRendering;
import org.apache.poi.ss.usermodel.Row;

/**
 * Row context.
 * 
 * @author i.voshkulat
 *
 */
public interface RowContext extends PlainDataOutput, FormattedDataOutput, CellNavigation<RowContext>, CellCondition<RowContext>,
		RowNavigation<SheetContext, RowContext>, StyleConfiguration, StyleConfigurable<RowContext>,
		SupportsColumnTotalsRendering<RowContext> {

	/**
	 * Set width of the last output cell.
	 * 
	 * <p>
	 * Sample:
	 * {@code
	 *   .nextRow()
	 *     .text("Some column header").setColumnWidth(25)
	 * }
	 * </p>
	 *
	 * @param width width in units, subject to multiplication - see {@link Configuration#getColumnWidthBaseValue()}
     * @return this
	 */
	RowContext setColumnWidth(int width);


    /**
     * Set height of the current row. Subject to multiplication - see {@link Configuration#getRowHeightBaseValue()}
     *
     * @param height by default, in points (as in Excel row height dialog).
     * @return this
     */
	RowContext setRowHeight(int height);
    
    /**
     * Merge cells of the current row starting from the current cell.
     * 
     * @param number total number of cells to merge (including the current one)
     * @return this
     */
	RowContext mergeCells(int number);
    
    /**
     * Retrieve POI row referred to by current {@link RowContext}.
     * Please refrain from using the exposed {@link Row} directly unless you need functionality of POI not provided by {@link RowContext}.
     * 
     * @return native POI {@link Row}
     */
	Row getNativeRow();
    
}