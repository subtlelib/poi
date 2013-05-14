package org.subtlelib.poi.impl.workbook;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.subtlelib.poi.api.configuration.Configuration;
import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.api.style.Style;
import org.subtlelib.poi.api.style.StyleConfiguration;
import org.subtlelib.poi.api.workbook.WorkbookContext;
import org.subtlelib.poi.impl.sheet.SheetContextImpl;
import org.subtlelib.poi.impl.style.InheritableStyleConfiguration;

public class WorkbookContextImpl extends InheritableStyleConfiguration<WorkbookContext> implements WorkbookContext {

    private final HSSFWorkbook workbook;
    
    private final Map<Style, HSSFCellStyle> registeredStyles = new HashMap<>();
    
    private final Configuration configuration;
    
    protected WorkbookContextImpl(HSSFWorkbook workbook, StyleConfiguration styleConfiguration, Configuration configuration) {
    	super(styleConfiguration);
        this.workbook = workbook;
        this.configuration = configuration;
    }
    
    @Override
    public SheetContext createSheet(String sheetName) {
        return new SheetContextImpl(workbook.createSheet(sheetName), this);
    }
    
	@Override
	public HSSFCellStyle registerStyle(Style style) {
		checkArgument(style != null, "Style is null");

		HSSFCellStyle registeredStyle = registeredStyles.get(style);
		
		if (registeredStyle == null) {
			registeredStyle = workbook.createCellStyle();
			style.enrich(workbook, registeredStyle);
			registeredStyles.put(style, registeredStyle);
		}
		
		return registeredStyle;
	}
    
    @Override
    public byte[] toNativeBytes() {
    	try {
    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
    		workbook.write(baos);
    		return baos.toByteArray();
    	} catch (IOException e) {
    		throw new RuntimeException("Quite unlikely case as we are working with an in-memory data. Wrap to avoid handling checked exception", e);
		}
    }

	@Override
	public HSSFWorkbook toNativeWorkbook() {
		return workbook;
	}

	@Override
	public Configuration getConfiguration() {
		return configuration;
	}
}