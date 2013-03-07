package org.subtlelib.poi.api.sheet;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.subtlelib.poi.api.condition.BlockCondition;
import org.subtlelib.poi.api.condition.RowCondition;
import org.subtlelib.poi.api.navigation.RowNavigation;
import org.subtlelib.poi.api.row.RowContext;
import org.subtlelib.poi.api.style.StyleConfigurable;
import org.subtlelib.poi.api.style.StyleConfiguration;

public interface SheetContext extends RowNavigation<SheetContext, RowContext>, RowCondition<RowContext>, BlockCondition<SheetContext>, 
		StyleConfiguration, StyleConfigurable<SheetContext> {

	public RowContext currentRow();
	
	public SheetContext setColumnWidth(int columnNumber, int width);
	public SheetContext setColumnWidths(int... widths);
	
	public SheetContext hideGrid();
	public SheetContext setDefaultRowIndent(int indent);
	
	public HSSFSheet getNativeSheet();
  
}