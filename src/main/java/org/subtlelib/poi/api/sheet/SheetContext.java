package org.subtlelib.poi.api.sheet;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.subtlelib.poi.api.condition.BlockCondition;
import org.subtlelib.poi.api.navigation.RowNavigation;
import org.subtlelib.poi.api.row.RowContext;
import org.subtlelib.poi.api.style.StyleConfigurable;
import org.subtlelib.poi.api.style.StyleConfiguration;

/**
 * Sheet context.
 * 
 * @author i.voshkulat
 *
 */
public interface SheetContext extends RowNavigation<SheetContext, RowContext>, BlockCondition<SheetContext>, 
		SheetConfiguration<SheetContext>, StyleConfiguration, StyleConfigurable<SheetContext> {

    /**
     * Retrieve POI sheet referred to by current {@link SheetContext}
     * 
     * @return native POI {@link HSSFSheet}
     */
	public HSSFSheet getNativeSheet();
  
}