package examples.conditional;

import com.github.subtlelib.poi.api.sheet.SheetContext;
import com.github.subtlelib.poi.api.workbook.WorkbookContext;
import com.github.subtlelib.poi.impl.workbook.WorkbookContextFactory;

/**
 * This example shows how to use subtlelib to render a report with grouped data.
 * Additional features covered:
 * <ul>
 *     <li><b>conditional cell</b> (see 'Related E-book' below): it is rendered
 *         depending on the report model</li>
 *     <li><b>optional data</b> (see 'ISBN'): when the field is optional, nothing is written in the cell.
 *         If you have nullable fields instead, use <code>.text(Optional.fromNullable(myData.getSomeField()))</code></li>
 * </ul>
 *
 * Created on 16/05/13
 * @author d.serdiuk
 */
public class ConditionalReportView {
    private final WorkbookContextFactory ctxFactory;

    public ConditionalReportView(WorkbookContextFactory ctxFactory) {
        this.ctxFactory = ctxFactory;
    }

    public WorkbookContext render(ConditionalReportModel model) {
        WorkbookContext workbookCtx = ctxFactory.createWorkbook();
        SheetContext sheetCtx = workbookCtx.createSheet("Books")
                .hideGrid();

        // report heading
        sheetCtx
            .nextRow()
                .mergeCells(2).text("Authors report #")
                .number(model.getReportNumber())
            .nextRow().cellAt(5)
                .text("LocalDate:").setColumnWidth(11)
                .date(model.getReportCreationDate()).setColumnWidth(11)
            .nextRow().cellAt(5)
                .text("Place:")
                .text(model.getReportCreationPlace());

        // columns heading
        sheetCtx
            .nextRow()
                .header("Name")
                .header("Surname").setColumnWidth(35)
                .header("ContactNumber").setColumnWidth(15)
                .header("Last Activity").setColumnWidth(16)
                .header("Rating");

        // data
        for (Author author: model.getBooksByAuthor().keySet()) {
            sheetCtx
                .nextRow()
                    .text(author.getName())
                    .text(author.getSurname())
                    .text(author.getContactNumber()) // contact number is Optional. If value is Absent, cell will be skipped
                    .date(author.getLastUpdate())
                    .text(author.getRating())
                .nextRow().skipCell()
                    .header("Title")
                    .header("# pages")
                    .conditionalCell(model.isEbooksIncluded()).header("Related E-book")
                    .header("Rating")
                    .header("Left in stock")
                    .header("Publisher")
                    .header("ISBN");

            for (Book book : model.getBooksByAuthor().get(author)) {
                sheetCtx
                    .nextRow().skipCell()
                        .text(book.getTitle())
                        .number(book.getPages())
                        .conditionalCell(model.isEbooksIncluded()).text(book.getEbookNumber())
                        .number(book.getRating())
                        .number(book.getLeftInWarehouse())
                        .text(book.getPublisher())
                        .text(book.getIsbn()); // ISBN is Optional. If value is Absent - cell will be skipped
            }
            sheetCtx
                .skipRow();
        }
        return workbookCtx;
    }
}
