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
	 * Merge cells of the current row starting from column number {@code startColumn} and ending with a column {@code endColumn}.
	 * 
	 * In most cases using {@link RowContext#mergeCells(int)} is supposed to be more convenient.
	 * 
	 * @param startColumn index of the first column participating in a merged cell
	 * @param endColumn index of the last column participating in a merged cell
	 */
	public SheetContext mergeCells(int startColumn, int endColumn);
	
    /**
     * Retrieve POI sheet referred to by current {@link SheetContext}
     * Please refrain from using the exposed {@link HSSFSheet} directly unless you need functionality of POI not provided by {@link SheetContext}.
     * 
     * @return native POI {@link HSSFSheet}
     */
	public HSSFSheet getNativeSheet();
  
}