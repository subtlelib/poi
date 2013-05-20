package org.subtlelib.poi.impl.workbook;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.subtlelib.poi.api.configuration.Configuration;
import org.subtlelib.poi.api.workbook.WorkbookContext;
import org.subtlelib.poi.impl.configuration.DefaultConfiguration;
import org.subtlelib.poi.impl.style.defaults.DefaultStyleConfiguration;

public class WorkbookContextFactory {

    public static WorkbookContext createWorkbook() {
        return new WorkbookContextImpl(new HSSFWorkbook(), new DefaultStyleConfiguration(), new DefaultConfiguration());
    }

    public static WorkbookContext createWorkbook(Configuration configuration) {
        return new WorkbookContextImpl(new HSSFWorkbook(), new DefaultStyleConfiguration(), configuration);
    }

}
