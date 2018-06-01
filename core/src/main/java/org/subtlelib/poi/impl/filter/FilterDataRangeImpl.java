package org.subtlelib.poi.impl.filter;

import org.subtlelib.poi.api.filter.FilterDataRange;
import static com.google.common.base.Preconditions.checkState;

import org.subtlelib.poi.impl.sheet.SheetContextImpl;

import com.google.common.base.Objects;

/**
 * Created by beni on 10.12.14.
 */
public class FilterDataRangeImpl implements FilterDataRange {
	
	private final static int NOT_SET = -1;

	private final SheetContextImpl sheetContext;
	private final int startRowNo;
	private final int startColNo;
	private int endRowNo = NOT_SET;
	private int endColNo = NOT_SET;

	public FilterDataRangeImpl(SheetContextImpl sheetContext) {
		this.startRowNo = sheetContext.getCurrentRowNo();
		this.startColNo = sheetContext.currentRow().getCurrentColNo();
		this.sheetContext = sheetContext;
	}

	@Override
	public void endOnCurrentRow() {
		endOnRow(0);
	}

	@Override
	public void endOnCurrentCol() {
		endOnCol(0);
	}

	@Override
	public void endOnPreviousRow() {
		endOnRow(-1);
	}

	@Override
	public void endOnPreviousCol() {
		endOnCol(-1);
	}

	@Override
	public void endOnRow(int rowOffset) {
		endRow(sheetContext.getCurrentRowNo() + rowOffset);
	}

	@Override
	public void endOnCol(int colOffset) {
		endCol(sheetContext.currentRow().getCurrentColNo() + colOffset);
	}

	private void endRow(int onLine) {
		checkState(endRowNo == NOT_SET, "Don't mark range end twice. End line was already marked.", this);
		endRowNo = onLine + 1; // +1 since line numbers are 0-based in SheetContextImpl
		checkState(endRowNo >= startRowNo, "No data for this filter.", this);
	}
	
	private void endCol(int onColumn) {
		checkState(endColNo == NOT_SET, "Don't mark range end twice. End column was already marked.", this);
		endColNo = onColumn + 1; // +1 since column numbers are 0-based in SheetContextImpl
		checkState(endColNo >= startColNo, "No data for this filter.", this);
	}

	@Override
	public int getStartRowNo() {
		return startRowNo;
	}

	@Override
	public int getEndRowNo() {
		checkState(isEndRowMarked(), "End line of data range must be marked before trying to retrieve it.", this);
		return endRowNo;
	}

	@Override
	public int getStartColNo() {
		return startColNo;
	}

	@Override
	public int getEndColNo() {
		checkState(isEndColMarked(), "End column of data range must be marked before trying to retrieve it.", this);
		return endColNo;
	}

	@Override
	public boolean isEndRowMarked() {
		return endRowNo != NOT_SET;
	}

	@Override
	public boolean isEndColMarked() {
		return endColNo != NOT_SET;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("startRow", startRowNo)
				.add("startCol", startColNo)
				.add("endRow", endRowNo)
				.add("endCol", endColNo)
				.add("isEndRowMarked", isEndRowMarked())
				.add("isEndColMarked", isEndColMarked())
				.toString();
	}
}
