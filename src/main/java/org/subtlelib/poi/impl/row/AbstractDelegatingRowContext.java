package org.subtlelib.poi.impl.row;

import org.subtlelib.poi.api.row.RowContext;
import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.impl.style.HierarchicalStyleConfiguration;

public abstract class AbstractDelegatingRowContext extends HierarchicalStyleConfiguration<RowContext> implements RowContext {

    protected final SheetContext sheet;

    public AbstractDelegatingRowContext(SheetContext sheet) {
    	super(sheet);
        this.sheet = sheet;
    }
    
    @Override
    public RowContext nextRow() {
        return sheet.nextRow();
    }
   
    @Override
    public RowContext nextConditionalRow(boolean condition) {
        return sheet.nextConditionalRow(condition);
    }

    @Override
    public SheetContext skipRow() {
        return sheet.skipRow();
    }

    @Override
    public SheetContext skipRows(int linesNumber) {
        return sheet.skipRows(linesNumber);
    }
    
}
