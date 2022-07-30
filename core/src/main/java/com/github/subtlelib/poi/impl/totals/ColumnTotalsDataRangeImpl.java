package com.github.subtlelib.poi.impl.totals;

import com.github.subtlelib.poi.api.totals.ColumnTotalsDataRange;
import com.github.subtlelib.poi.impl.sheet.SheetContextImpl;

/**
 * Created on 28/03/13
 *
 * @author d.serdiuk
 */
public class ColumnTotalsDataRangeImpl implements ColumnTotalsDataRange {

    private final static int NOT_SET = -1;

    private final SheetContextImpl sheetContext;
    private final int startRowNo;
    private int endRowNo = NOT_SET;

    public ColumnTotalsDataRangeImpl(SheetContextImpl sheetContext) {
        this.startRowNo = sheetContext.getCurrentRowNo() + 2; // +1 since it's next row, +1 since line numbers are
                                                              // 0-based in SheetContextImpl
        this.sheetContext = sheetContext;
    }

    @Override
    public void endOnCurrentRow() {
        endOn(0);
    }

    @Override
    public void endOnPreviousRow() {
        endOn(-1);
    }

    @Override
    public void endOn(int rowOffset) {
        end(sheetContext.getCurrentRowNo() + rowOffset);
    }

    private void end(int onLine) {
        if (endRowNo != NOT_SET) {
            throw new IllegalStateException("Don't mark range end twice. End line was already marked: " + this);
        }
        endRowNo = onLine + 1; // +1 since line numbers are 0-based in SheetContextImpl
        if (endRowNo < startRowNo) {
            throw new IllegalStateException("No data for totals: " + this);
        }
    }

    @Override
    public int getStartRowNo() {
        return startRowNo;
    }

    @Override
    public int getEndRowNo() {
        if (!isEndMarked()) {
            throw new IllegalStateException("End line of data range must be marked " +
                    "before trying to retrieve it: " + this);
        }
        return endRowNo;
    }

    @Override
    public boolean isEndMarked() {
        return endRowNo != NOT_SET;
    }

    @Override
    public String toString() {
        return "ColumnTotalsDataRangeImpl{" +
                "startRowNo=" + startRowNo +
                ", endRowNo=" + endRowNo +
                ", isEndMarked=" + isEndMarked() +
                '}';
    }
}
