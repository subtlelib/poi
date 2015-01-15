package examples.conditional;

import java.io.File;
import java.io.IOException;

import org.subtlelib.poi.api.workbook.WorkbookContext;
import org.subtlelib.poi.impl.workbook.WorkbookContextFactory;

import com.google.common.io.Files;

/**
 * Created on 17/05/13
 *
 * @author d.serdiuk
 */
public class ConditionalReportController {

    public static void main(String[] args) throws IOException {
        ConditionalReportView view = new ConditionalReportView(WorkbookContextFactory.useXlsx());
        WorkbookContext workbook = view.render(ConditionalReportModel.getExample());
        Files.write(workbook.toNativeBytes(), new File("conditional_example_books.xlsx"));
    }
}

