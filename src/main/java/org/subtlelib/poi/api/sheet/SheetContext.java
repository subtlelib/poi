package org.subtlelib.poi.api.sheet;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Sheet;
import org.subtlelib.poi.api.condition.BlockCondition;
import org.subtlelib.poi.api.navigation.RowNavigation;
import org.subtlelib.poi.api.row.RowContext;
import org.subtlelib.poi.api.style.StyleConfigurable;
import org.subtlelib.poi.api.style.StyleConfiguration;
import org.subtlelib.poi.api.totals.ColumnTotalsDataRangeSource;

/**
 * Sheet context.
 * 
 * @author i.voshkulat
 *
 */
public interface SheetContext extends RowNavigation<SheetContext, RowContext>, BlockCondition<SheetContext>, 
		SheetConfiguration<SheetContext>, StyleConfiguration, StyleConfigurable<SheetContext>, ColumnTotalsDataRangeSource {

    /**
     * Retrieve POI sheet referred to by current {@link SheetContext}
     * Please refrain from using the exposed {@link HSSFSheet} directly unless you need functionality of POI not provided by {@link SheetContext}.
     * 
     * @return native POI {@link HSSFSheet}
     */
	public Sheet getNativeSheet();
  
}