package org.subtlelib.poi.impl.row;

import org.subtlelib.poi.api.row.RowContext;
import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.impl.style.InheritableStyleConfiguration;

public abstract class AbstractDelegatingRowContext extends InheritableStyleConfiguration<RowContext> implements RowContext {

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
    public RowContext currentRow() {
    	throw new IllegalStateException("Operation makes no sense on RowContext instance. Nothing will change if you just remove this line from your code");
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
    public SheetContext skipRows(int offset) {
        return sheet.skipRows(offset);
    }

    @Override
    public SheetContext stepOneRowBack() {
        return sheet.stepOneRowBack();
    }
}
