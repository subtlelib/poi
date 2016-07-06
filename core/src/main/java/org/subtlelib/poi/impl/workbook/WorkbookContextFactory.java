package org.subtlelib.poi.impl.workbook;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.subtlelib.poi.api.configuration.Configuration;
import org.subtlelib.poi.api.style.StyleConfiguration;
import org.subtlelib.poi.api.workbook.WorkbookContext;
import org.subtlelib.poi.impl.configuration.DefaultConfiguration;
import org.subtlelib.poi.impl.style.defaults.DefaultStyleConfiguration;

public class WorkbookContextFactory {
	
	// if HSSF (.xls) or XSSF (.xlsx) format is used for new workbooks ?
	private final boolean useHSSF;

    private WorkbookContextFactory(boolean useHSSF) {
        this.useHSSF = useHSSF;
    }

    private static final WorkbookContextFactory xlsFactory = new WorkbookContextFactory(true);
    private static final WorkbookContextFactory xlsxFactory = new WorkbookContextFactory(false);

    /**
     * <p>
     * If you use Dependency Injection, inject WorkbookContextFactory
     * and in your config wire/bind one of these 2 static factory methods as implementation.
     * </p>
     * <p>
     * Tip: Spring has &lt;bean factory-method="..." /&gt; attribute. Guice has @Provides methods.
     * </p>
     * @return xls workbook factory
     */
    public static WorkbookContextFactory useXls() {
        return xlsFactory;
    }

    public static WorkbookContextFactory useXlsx() {
        return xlsxFactory;
    }

    public WorkbookContext createWorkbook() {
        return useWorkbook(newWorkBook());
    }
	
    public WorkbookContext createWorkbook(Configuration configuration) {
        return useWorkbook(newWorkBook(), configuration);
    }

    public WorkbookContext createWorkbook(StyleConfiguration styleConfiguration) {
        return useWorkbook(newWorkBook(), styleConfiguration);
    }

    private Workbook newWorkBook() {
		return useHSSF ? new HSSFWorkbook() : new XSSFWorkbook();
	}

    private static String getDefaultFontName(Workbook workbook) {
        if (workbook instanceof HSSFWorkbook) {
            return HSSFFont.FONT_ARIAL;
        } else if (workbook instanceof XSSFWorkbook) {
            return XSSFFont.DEFAULT_FONT_NAME;
        } else {
            throw new IllegalArgumentException("Workbook is expected to be an instance of HSSFWorkbook or XSSFWorkbook: "
                    + workbook);
        }
    }
    
    /**
     * Use an existing WorkBook
     * @param workbook could be of type xls (HSSFWorkbook) or xlsx (XSSFWorkbook)
     * @return new context for existing workbook
     */
    @SuppressWarnings("WeakerAccess") // part of API
    public static WorkbookContext useWorkbook(Workbook workbook) {
        return new WorkbookContextImpl(workbook, new DefaultStyleConfiguration(), new DefaultConfiguration(),
                getDefaultFontName(workbook));
    }
    
    @SuppressWarnings("WeakerAccess") // part of API
    public static WorkbookContext useWorkbook(Workbook workbook, Configuration configuration) {
        return new WorkbookContextImpl(workbook, new DefaultStyleConfiguration(), configuration,
                getDefaultFontName(workbook));
    }

    @SuppressWarnings("WeakerAccess") // part of API
    public static WorkbookContext useWorkbook(Workbook workbook, StyleConfiguration styleConfiguration) {
        return new WorkbookContextImpl(workbook, styleConfiguration, new DefaultConfiguration(),
                getDefaultFontName(workbook));
    }
}
