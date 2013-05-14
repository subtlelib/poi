package org.subtlelib.poi.api.row;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Row;
import org.subtlelib.poi.api.condition.CellCondition;
import org.subtlelib.poi.api.configuration.Configuration;
import org.subtlelib.poi.api.navigation.CellNavigation;
import org.subtlelib.poi.api.navigation.RowNavigation;
import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.api.style.StyleConfigurable;
import org.subtlelib.poi.api.style.StyleConfiguration;
import org.subtlelib.poi.api.totals.SupportsColumnTotalsRendering;

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
	 * <pre>
	 * {@code
	 *   .nextRow()
	 *     .text("Some column header").setColumnWidth(25)
	 * }
	 * </pre>
	 * </p>
	 * 
	 * @param width width in units, subject to multiplication - see {@link Configuration#getColumnWidthBaseValue()}
	 */
    public RowContext setColumnWidth(int width);


    /**
     * Set height of the current row. Subject to multiplication - see {@link Configuration#getRowHeightBaseValue()}
     *
     * @param height by default, in points (as in Excel row height dialog).
     */
    public RowContext setRowHeight(int height);
    
    /**
     * Merge cells of the current row starting from the current cell.
     * 
     * @param number total number of cells to merge (including the current one)
     */
    public RowContext mergeCells(int number);
    
    /**
     * Retrieve POI row referred to by current {@link RowContext}.
     * Please refrain from using the exposed {@link HSSFRow} directly unless you need functionality of POI not provided by {@link RowContext}.
     * 
     * @return native POI {@link HSSFRow}
     */
	public Row getNativeRow();
    
}