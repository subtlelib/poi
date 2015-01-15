package org.subtlelib.poi.impl.workbook;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.subtlelib.poi.api.configuration.Configuration;
import org.subtlelib.poi.api.style.StyleConfiguration;
import org.subtlelib.poi.api.workbook.WorkbookContext;
import org.subtlelib.poi.impl.configuration.DefaultConfiguration;
import org.subtlelib.poi.impl.style.defaults.DefaultStyleConfiguration;

public class WorkbookContextFactory {
	
	// if HSSF (.xls) or XSSF (.xlsx) format is used for new workbooks ?
	private static boolean useHSSF = true;	
	
	public static boolean isUseHSSF() {
		return useHSSF;
	}
	public static void setUseHSSF(boolean useHSSF) {
		WorkbookContextFactory.useHSSF = useHSSF;
	}
	
    private static Workbook newWorkBook() {
		return useHSSF ? new HSSFWorkbook() : new XSSFWorkbook();
	}

    public static WorkbookContext createWorkbook() {
        return useWorkbook(newWorkBook());
    }
	
    public static WorkbookContext createWorkbook(Configuration configuration) {
        return useWorkbook(newWorkBook(), configuration);
    }

    public static WorkbookContext createWorkbook(StyleConfiguration styleConfiguration) {
        return useWorkbook(newWorkBook(), styleConfiguration);
    }
    
    /**
     * Use an existing WorkBook
     * @param workbook
     * @return
     */
    public static WorkbookContext useWorkbook(Workbook workbook) {
        return new WorkbookContextImpl(workbook, new DefaultStyleConfiguration(), new DefaultConfiguration());
    }
    
    public static WorkbookContext useWorkbook(Workbook workbook, Configuration configuration) {
        return new WorkbookContextImpl(workbook, new DefaultStyleConfiguration(), configuration);
    }

    public static WorkbookContext useWorkbook(Workbook workbook, StyleConfiguration styleConfiguration) {
        return new WorkbookContextImpl(workbook, styleConfiguration, new DefaultConfiguration());
    }
    
}
