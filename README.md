Java Excel report Builder library
===
using [POI](poi.apache.org).

An easy way to generate Excel reports.
- Supports generation of **xls** and **xlsx** files
- Fluent syntax
- Refactoring friendly
- Easy to read
- Supports cascading styles (define default styles for your project, customize for a single report)
- Used in production for > 10 years
- Supports simple formulas for totals
- Doesn't tolerate nulls, uses Optionals instead
## Maven/Gradle
```xml
<dependency>
    <groupId>io.github.subtlelib</groupId>
    <artifactId>poi-builder-core</artifactId>
    <version>1.1.1</version>
</dependency>
```
```groovy
implementation 'io.github.subtlelib:poi-builder-core:1.1.1'
```


## Examples
### Simple
The easiest use of subtlelib: we display a collection of domain objects in an excel worksheet.

#### Source code

```java
public class SimpleReportView {
    private final WorkbookContextFactory ctxFactory;

    public SimpleReportView(WorkbookContextFactory ctxFactory) {
        this.ctxFactory = ctxFactory;
    }

    public WorkbookContext render(Collection<Payment> payments) {
        WorkbookContext workbookCtx = ctxFactory.createWorkbook();
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
    public static void main(String[] args) {
        SimpleReportView view = new SimpleReportView(WorkbookContextFactory.useXlsx());
        WorkbookContext workbook = view.render(new SimpleReportModel().getPayments());
        Files.write(Paths.get("simple_example.xls"), workbook.toNativeBytes());
    }
}
```
#### Resulting file    
![Simple example](https://github.com/subtlelib/poi/blob/master/examples/images/example1.png)

### An example featuring optional and conditional elements
#### Source code
```java
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
                .text("Date:").setColumnWidth(11)
                .date(model.getReportCreationDate().toDate()).setColumnWidth(11)
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
                    .date(author.getLastUpdate().toDate())
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
```
#### Resulting file

![Conditional example](https://github.com/subtlelib/poi/blob/master/examples/images/example2.PNG)
