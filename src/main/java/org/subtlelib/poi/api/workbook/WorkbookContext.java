package org.subtlelib.poi.api.workbook;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.subtlelib.poi.api.configuration.ConfigurationProvider;
import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.api.style.StyleConfigurable;
import org.subtlelib.poi.api.style.StyleConfiguration;
import org.subtlelib.poi.api.style.StyleRegistry;

/**
 * Workbook context.
 * 
 * @author i.voshkulat
 *
 */
public interface WorkbookContext extends ConfigurationProvider, StyleRegistry, StyleConfiguration, StyleConfigurable<WorkbookContext> {

	/**
	 * Create new sheet within the current workbook.
	 * 
	 * @param sheetName sheet name
	 */
	public SheetContext createSheet(String sheetName);
	
    /**
     * Retrieve POI workbook referred to by current {@link WorkbookContext}.
     * Please refrain from using the exposed {@link HSSFWorkbook} directly unless you need functionality of POI not provided by {@link WorkbookContext}.
     * To retrieve a workbook for saving to a file look at {@link #toNativeBytes()} instead.
     * 
     * @return native POI {@link HSSFWorkbook}
     */	
	public HSSFWorkbook toNativeWorkbook();
	
	/**
	 * Create .xls file binary representation for current {@link WorkbookContext}
	 * 
	 * @return excel file binary representation
	 */
	public byte[] toNativeBytes();
	
}
