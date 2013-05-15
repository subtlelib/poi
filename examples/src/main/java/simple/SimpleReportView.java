package simple;

import java.util.Collection;

import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.api.workbook.WorkbookContext;
import org.subtlelib.poi.impl.workbook.WorkbookContextFactory;

/**
 * This example shows the basics of subtlelib. First headers are rendered, then the data rows
 * (each row corresponds to 1 domain object).
 * <p/>
 * Created on 15/05/13
 * @author d.serdiuk
 */
public class SimpleReportView {
    public WorkbookContext render(Collection<Payment> payments) {
        WorkbookContext workbookCtx = WorkbookContextFactory.createWorkbook();
        SheetContext sheetCtx = workbookCtx.createSheet("Payments");

        // heading
        sheetCtx
            .nextRow()
                .text("Amount")
                .text("Currency")
                .text("Beneficiary").setColumnWidth(25)
                .text("Payee bank").setColumnWidth(35)
            .skipRow();

        // data
        for (Payment payment : payments) {
            sheetCtx
                .nextRow()
                    .number(payment.getAmount())
                    .text(payment.getCurrency())
                    .text(payment.getBeneficiary())
                    .text(payment.getPayeeBank());
        }
        return workbookCtx;
    }
}
