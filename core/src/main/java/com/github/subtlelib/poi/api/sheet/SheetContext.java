package com.github.subtlelib.poi.api.sheet;

import com.github.subtlelib.poi.api.condition.BlockCondition;
import com.github.subtlelib.poi.api.configuration.ConfigurationProvider;
import com.github.subtlelib.poi.api.navigation.RowNavigation;
import com.github.subtlelib.poi.api.style.StyleConfigurable;
import com.github.subtlelib.poi.api.style.StyleConfiguration;
import com.github.subtlelib.poi.api.totals.ColumnTotalsDataRangeSource;
import org.apache.poi.ss.usermodel.Sheet;
import com.github.subtlelib.poi.api.row.RowContext;

/**
 * Sheet context.
 * 
 * @author i.voshkulat
 *
 */
public interface SheetContext extends RowNavigation<SheetContext, RowContext>, BlockCondition<SheetContext>,
		SheetConfiguration<SheetContext>, ConfigurationProvider, StyleConfiguration, StyleConfigurable<SheetContext>, ColumnTotalsDataRangeSource {

	/**
	 * Merge cells of the current row starting from column number {@code startColumn} and ending with a column {@code endColumn}.
	 * 
	 * In most cases using {@link RowContext#mergeCells(int)} is supposed to be more convenient.
	 * 
	 * @param startColumn index of the first column participating in a merged cell
	 * @param endColumn index of the last column participating in a merged cell
     * @return this
	 */
	@SuppressWarnings("UnusedReturnValue") // for consistency with other similar methods
	public SheetContext mergeCells(int startColumn, int endColumn);
	
    /**
     * Retrieve POI sheet referred to by current {@link SheetContext}
     * Please refrain from using the exposed {@link Sheet} directly unless you need functionality of POI not provided by {@link SheetContext}.
     * 
     * @return native POI {@link Sheet}
     */
	public Sheet getNativeSheet();
  
}