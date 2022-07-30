package examples.conditional;

import com.github.subtlelib.poi.api.workbook.WorkbookContext;
import com.github.subtlelib.poi.impl.workbook.WorkbookContextFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created on 17/05/13
 *
 * @author d.serdiuk
 */
public class ConditionalReportController {

    public static void main(String[] args) throws IOException {
        ConditionalReportView view = new ConditionalReportView(WorkbookContextFactory.useXlsx());
        WorkbookContext workbook = view.render(ConditionalReportModel.getExample());
        Files.write(Paths.get("conditional_example_books.xlsx"), workbook.toNativeBytes());
    }
}

