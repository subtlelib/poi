package examples.conditional;

import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.api.workbook.WorkbookContext;
import org.subtlelib.poi.impl.workbook.WorkbookContextFactory;

/**
 * TODO: describe class
 * <p/>
 * Created on 16/05/13
 *
 * @author d.serdiuk
 */
public class ConditionalReportView {
    public WorkbookContext render(ConditionalReportModel model) {
        WorkbookContext workbookCtx = WorkbookContextFactory.createWorkbook();
        SheetContext sheetCtx = workbookCtx.createSheet("Books");

        // report heading
        sheetCtx
            .nextRow()
                .text("Authors report")
            .nextRow().cellAt(5)
                .text("Date:")
                .date(model.getReportCreationDate())
            .nextRow().cellAt(5)
                .text("Place:")
                .text(model.getReportCreationPlace());

        // columns heading
        sheetCtx
            .nextRow()
                .text("Name")
                .text("Surname")
                .text("ContactNumber")
                .text("Last Activity")
                .text("Rating")
            .skipRow();

        // data
        for (Author author: model.getBooksByAuthor().keySet()) {
            sheetCtx
                .nextRow()
                    .text(author.getName())
                    .text(author.getSurname())
                    .text(author.getContactNumber())
                    .date(author.getLastUpdate())
                    .text(author.getRating());

            // TODO show books heading and data
        }
        return workbookCtx;
    }
}
