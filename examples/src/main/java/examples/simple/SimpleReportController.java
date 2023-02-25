package examples.simple;

import io.github.subtlelib.poi.api.workbook.WorkbookContext;
import io.github.subtlelib.poi.impl.workbook.WorkbookContextFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFileAttributes;

/**
 * Created on 15/05/13
 *
 * @author d.serdiuk
 */
public class SimpleReportController {

    public static void main(String[] args) throws IOException {
        SimpleReportView view = new SimpleReportView(WorkbookContextFactory.useXlsx());
        WorkbookContext workbook = view.render(new SimpleReportModel().getPayments());
        Path dir = Paths.get("target");
        if (!Files.exists(dir)) {
            Files.createDirectory(dir);
        }
        Files.write(dir.resolve("simple_example.xlsx"), workbook.toNativeBytes());
    }
}
