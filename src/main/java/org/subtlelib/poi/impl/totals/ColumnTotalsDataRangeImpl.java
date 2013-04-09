package org.subtlelib.poi.impl.totals;

import static com.google.common.base.Preconditions.checkState;

import org.subtlelib.poi.api.totals.ColumnTotalsDataRange;
import org.subtlelib.poi.impl.sheet.SheetContextImpl;

/**
 * Created on 28/03/13
 *
 * @author d.serdiuk
 */
public class ColumnTotalsDataRangeImpl implements ColumnTotalsDataRange {
    private final static int NOT_SET = -1;

    private final SheetContextImpl sheetContext;
    private final int startLineNo;
    private int endLineNo = NOT_SET;

    public ColumnTotalsDataRangeImpl(SheetContextImpl sheetContext) {
        this.startLineNo = sheetContext.getCurrentLineNo() + 2; // +1 since it's next row, +1 since line numbers are
                                                                // 0-based in SheetContextImpl
        this.sheetContext = sheetContext;
    }

    @Override
    public void end() {
        checkState(endLineNo == NOT_SET, "Don't call end() twice. End line was already marked: %s", endLineNo);
        endLineNo = sheetContext.getCurrentLineNo();
        checkState(endLineNo != startLineNo, "No data for totals. " +
                "Start line of data range equals to end line: %s", endLineNo);
    }

    @Override
    public int getStartLineNo() {
        return startLineNo;
    }

    @Override
    public int getEndLineNo() {
        if (endLineNo == NOT_SET) { // if not yet marked the end of data range
            end();
        }
        return endLineNo;
    }
}
