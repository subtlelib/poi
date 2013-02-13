package org.subtlelib.poi.impl.sheet;

import static com.google.common.base.Preconditions.checkState;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.subtlelib.poi.api.configuration.Configuration;
import org.subtlelib.poi.api.row.RowContext;
import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.api.workbook.WorkbookContext;
import org.subtlelib.poi.impl.row.RowContextImpl;
import org.subtlelib.poi.impl.row.RowContextNoImpl;
import org.subtlelib.poi.impl.style.HierarchicalStyleConfiguration;

public class SheetContextImpl extends HierarchicalStyleConfiguration<SheetContext> implements SheetContext {

	private final WorkbookContext workbook;
    
    private final HSSFSheet sheet;
    
    protected RowContext currentRow;
    protected int lineNo = -1;

    private final SheetContext noImplSheetContext;
    private final RowContext noImplRowContext;
    
    public SheetContextImpl(HSSFSheet sheet, WorkbookContext workbook) {
    	super(workbook);
    	
        this.sheet = sheet;
        this.workbook = workbook;
        
        this.noImplSheetContext = new SheetContextNoImpl(this, workbook);
        this.noImplRowContext = new RowContextNoImpl(this);
    }
    
    @Override
	public SheetContext skipRow() {
        lineNo++;
        return this;
    }

    @Override
	public SheetContext skipRows(int n) {
        this.lineNo += n;
        return this;
    }

    @Override
	public RowContext nextRow() {
        currentRow = new RowContextImpl(sheet.createRow(++lineNo), this, workbook);
        return currentRow;
    }

    @Override
	public RowContext nextConditionalRow(boolean condition) {
        return condition ? nextRow() : nextRowNoImpl();
    }
    
    private RowContext nextRowNoImpl() {
        currentRow = noImplRowContext;
        return currentRow;
    }

    @Override
	public RowContext currentRow() {
        checkState(currentRow != null, "Current row doesn't exist. Use nextRow() to create a new row");
        
        return currentRow;
    }

    @Override
	public HSSFSheet getNativeSheet() {
        return sheet;
    }

    @Override
    public SheetContext setColumnWidth(int columnNumber, int width) {
        //TODO max col width in excel is 255 characters; check aforementioned condition with a test (whether our 1.1 doesn't reduce this upper boundary), add precondition check
    	sheet.setColumnWidth(columnNumber, (int) (getConfiguration().getColumnWidthBaseValue() * width));
    	return this;
    }
    
    @Override
	public SheetContext setColumnWidths(int... multipliers) {
    	for (int i = 0; i < multipliers.length; i++) {
            setColumnWidth(i, multipliers[i]);
        }
        return this;
    }
    
	@Override
	public SheetContext hideGrid() {
		sheet.setDisplayGridlines(false);
		return this;
	}

	@Override
	public SheetContext startConditionalBlock(boolean condition) {
		return condition ? this : noImplSheetContext;
	}

	@Override
	public SheetContext endConditionalBlock() {
		return this;
	}

	private Configuration getConfiguration() {
		return workbook.getConfiguration();
	}

}
