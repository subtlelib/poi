package examples.simple;

import java.io.File;
import java.io.IOException;

import org.subtlelib.poi.api.workbook.WorkbookContext;
import org.subtlelib.poi.impl.workbook.WorkbookContextFactory;

import com.google.common.io.Files;

/**
 * Created on 15/05/13
 * @author d.serdiuk
 */
public class SimpleReportController {

    public static void main(String[] args) throws IOException {
        SimpleReportView view = new SimpleReportView(WorkbookContextFactory.useXlsx());
        WorkbookContext workbook = view.render(new SimpleReportModel().getPayments());
        Files.write(workbook.toNativeBytes(), new File("simple_example.xlsx"));
    }
}
