poi
===

A fancy way to boilerplate-less POI

## Examples
### Simple
The easiest use of subtlelib: we display a collection of domain objects in an excel worksheet.

#### Source code
    public class SimpleReportView {
        public WorkbookContext render(Collection<Payment> payments) {
            WorkbookContext workbookCtx = WorkbookContextFactory.createWorkbook();
            SheetContext sheetCtx = workbookCtx.createSheet("Payments");
    
            // heading
            sheetCtx
                .nextRow()
                    .skipCell()
                    .header("Amount")
                    .header("Currency")
                    .header("Beneficiary").setColumnWidth(25)
                    .header("Payee bank").setColumnWidth(35);
    
            ColumnTotalsDataRange totalsData = sheetCtx.startColumnTotalsDataRangeFromNextRow();
    
            // data
            for (Payment payment : payments) {
                sheetCtx
                    .nextRow()
                        .skipCell()
                        .number(payment.getAmount())
                        .text(payment.getCurrency())
                        .text(payment.getBeneficiary())
                        .text(payment.getPayeeBank());
            }
    
            sheetCtx
                .nextRow().setTotalsDataRange(totalsData)
                    .header("Total:")
                    .total(Formula.SUM);
    
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
#### Resulting file    
![Alt text](/examples/images/example1.png)
