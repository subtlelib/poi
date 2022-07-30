package com.github.subtlelib.poi.api.workbook;

import com.github.subtlelib.poi.api.configuration.ConfigurationProvider;
import org.apache.poi.ss.usermodel.Workbook;
import com.github.subtlelib.poi.api.sheet.SheetContext;
import com.github.subtlelib.poi.api.style.StyleConfigurable;
import com.github.subtlelib.poi.api.style.StyleConfiguration;
import com.github.subtlelib.poi.api.style.StyleRegistry;

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
     * @return created {@link SheetContext}
	 */
	public SheetContext createSheet(String sheetName);
	
	/**
	 * Use an existing sheet within the current workbook
	 *
	 * @param sheetName sheet to use
	 * @return existing {@link SheetContext}
	 * @throws java.lang.IllegalArgumentException if a sheet with the given name doesn't exist
	 */
	public SheetContext useSheet(String sheetName); 
		
    /**
     * Retrieve POI workbook referred to by current {@link WorkbookContext}.
     * Please refrain from using the exposed {@link Workbook} directly unless you need functionality of POI not provided by {@link WorkbookContext}.
     * To retrieve a workbook for saving to a file look at {@link #toNativeBytes()} instead.
     * 
     * @return native POI {@link Workbook}
     */	
	public Workbook toNativeWorkbook();
	
	/**
	 * Create .xls file binary representation for current {@link WorkbookContext}
	 * 
	 * @return excel file binary representation
	 */
	public byte[] toNativeBytes();

	/** 
	 * xls and xlsx files have different default fonts 
	 *
	 * @return font name
	 */
	public String getDefaultFontName();
	
}
