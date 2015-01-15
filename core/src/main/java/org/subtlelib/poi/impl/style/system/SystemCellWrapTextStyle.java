package org.subtlelib.poi.impl.style.system;

import org.apache.poi.ss.usermodel.CellStyle;
import org.subtlelib.poi.api.style.AdditiveStyle;
import org.subtlelib.poi.api.workbook.WorkbookContext;

public enum SystemCellWrapTextStyle implements AdditiveStyle {
	
	WRAP_TEXT;
	
	@Override
	public void enrich(WorkbookContext workbookContext, CellStyle style) {
		style.setWrapText(true);
	}

	@Override
	public Enum<SystemStyleType> getType() {
		return SystemStyleType.CELL_WRAP_TEXT;
	}

}