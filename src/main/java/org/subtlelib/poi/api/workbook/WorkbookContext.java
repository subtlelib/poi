package org.subtlelib.poi.api.workbook;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.subtlelib.poi.api.configuration.ConfigurationProvider;
import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.api.style.StyleConfigurable;
import org.subtlelib.poi.api.style.StyleConfiguration;
import org.subtlelib.poi.api.style.StyleRegistry;

public interface WorkbookContext extends ConfigurationProvider, StyleRegistry, StyleConfiguration, StyleConfigurable<WorkbookContext> {

	public SheetContext createSheet(String sheetName);
	
	public HSSFWorkbook toNativeWorkbook();
	public byte[] toNativeBytes() throws IOException;
	
}
