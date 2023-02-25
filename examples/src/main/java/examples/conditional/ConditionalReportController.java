package examples.conditional;

import io.github.subtlelib.poi.api.workbook.WorkbookContext;
import io.github.subtlelib.poi.impl.workbook.WorkbookContextFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        Path dir = Paths.get("target");
        if (!Files.exists(dir)) {
            Files.createDirectory(dir);
        }
        Files.write(dir.resolve("conditional_example_books.xlsx"), workbook.toNativeBytes());
    }
}

