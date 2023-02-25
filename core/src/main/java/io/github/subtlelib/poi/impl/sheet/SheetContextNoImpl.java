package io.github.subtlelib.poi.impl.sheet;

import io.github.subtlelib.poi.impl.row.RowContextNoImpl;
import io.github.subtlelib.poi.impl.style.InheritableStyleConfiguration;
import org.apache.poi.ss.usermodel.Sheet;
import io.github.subtlelib.poi.api.configuration.Configuration;
import io.github.subtlelib.poi.api.row.RowContext;
import io.github.subtlelib.poi.api.sheet.SheetContext;
import io.github.subtlelib.poi.api.totals.ColumnTotalsDataRange;
import io.github.subtlelib.poi.api.workbook.WorkbookContext;

public class SheetContextNoImpl extends InheritableStyleConfiguration<SheetContext> implements SheetContext {

	private final SheetContext parent;
	
	private final RowContext noImplRowContext = new RowContextNoImpl(this);
	
	public SheetContextNoImpl(SheetContext parent, WorkbookContext workbook) {
    	super(workbook);		
		this.parent = parent;
	}

	@Override
	public RowContext nextRow() {
		return noImplRowContext;
	}

	@Override
	public RowContext nextConditionalRow(boolean condition) {
		return noImplRowContext;
	}

	@Override
	public RowContext currentRow() {
		return noImplRowContext;
	}
	
	@Override
	public SheetContext skipRow() {
		return this;
	}

	@Override
	public SheetContext skipRows(int offset) {
		return this;
	}

    @Override
    public SheetContext stepOneRowBack() {
        return this;
    }

    @Override
	public SheetContext startConditionalBlock(boolean condition) {
		return this;
	}

	@Override
	public SheetContext endConditionalBlock() {
		return parent;
	}
	
	@Override
	public Sheet getNativeSheet() {
		throw new UnsupportedOperationException("Native sheet is not supported by SheetContextNoImpl");
	}

    @Override
    public SheetContext setColumnWidth(int columnNumber, int width) {
    	return this;
    }
	
	@Override
	public SheetContext setColumnWidths(int... multipliers) {
		return this;
	}
	
	@Override
	public SheetContext mergeCells(int startColumn, int endColumn) {
		return this;
	}

	@Override
	public SheetContext hideGrid() {
		return this;
	}
	
	@Override
	public SheetContext setDefaultRowIndent(int indent) {	
		return this;
	}

    @Override
    public SheetContext fitOnPagesByWidth(int pages) {
        return this;
    }

    @Override
    public SheetContext fitOnPagesByHeight(int pages) {
        return this;
    }

    @Override
    public Configuration getConfiguration() {
        throw new UnsupportedOperationException("Configuration is not supported by SheetContextNoImpl");
    }

    @Override
    public ColumnTotalsDataRange startColumnTotalsDataRangeFromNextRow() {
        throw new UnsupportedOperationException("Totals unsupported in conditional blocks");
    }
}