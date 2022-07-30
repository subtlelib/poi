package examples.simple;

import com.github.subtlelib.poi.api.workbook.WorkbookContext;
import com.github.subtlelib.poi.impl.workbook.WorkbookContextFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created on 15/05/13
 * @author d.serdiuk
 */
public class SimpleReportController {

    public static void main(String[] args) throws IOException {
        SimpleReportView view = new SimpleReportView(WorkbookContextFactory.useXlsx());
        WorkbookContext workbook = view.render(new SimpleReportModel().getPayments());
        Files.write(Paths.get("simple_example.xlsx"), workbook.toNativeBytes());
    }
}
