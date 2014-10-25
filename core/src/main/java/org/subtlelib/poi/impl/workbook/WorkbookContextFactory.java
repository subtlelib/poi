package org.subtlelib.poi.impl.workbook;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.subtlelib.poi.api.configuration.Configuration;
import org.subtlelib.poi.api.style.StyleConfiguration;
import org.subtlelib.poi.api.workbook.WorkbookContext;
import org.subtlelib.poi.impl.configuration.DefaultConfiguration;
import org.subtlelib.poi.impl.style.defaults.DefaultStyleConfiguration;

public class WorkbookContextFactory {

    public static WorkbookContext createWorkbook() {
        return useWorkbook(new HSSFWorkbook());
    }

    public static WorkbookContext createWorkbook(Configuration configuration) {
        return useWorkbook(new HSSFWorkbook(), configuration);
    }

    public static WorkbookContext createWorkbook(StyleConfiguration styleConfiguration) {
        return useWorkbook(new HSSFWorkbook(), styleConfiguration);
    }
    
    /**
     * Use an existing WorkBook
     * @param workbook
     * @return
     */
    public static WorkbookContext useWorkbook(HSSFWorkbook workbook) {
        return new WorkbookContextImpl(workbook, new DefaultStyleConfiguration(), new DefaultConfiguration());
    }
    
    public static WorkbookContext useWorkbook(HSSFWorkbook workbook, Configuration configuration) {
        return new WorkbookContextImpl(workbook, new DefaultStyleConfiguration(), configuration);
    }

    public static WorkbookContext useWorkbook(HSSFWorkbook workbook, StyleConfiguration styleConfiguration) {
        return new WorkbookContextImpl(workbook, styleConfiguration, new DefaultConfiguration());
    }
    
}
