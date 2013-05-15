poi
===

A fancy way to boilerplate-less POI

## Examples
### Simple
The easiest use of subtlelib: we display a collection of domain objects in an excel worksheet.

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
    
    public class SimpleReportController {
        public static void main(String[] args) throws IOException {
            SimpleReportView view = new SimpleReportView();
            WorkbookContext workbook = view.render(new SimpleReportModel().getPayments());
            Files.write(workbook.toNativeBytes(), new File("simple_example.xls"));
        }
    }
