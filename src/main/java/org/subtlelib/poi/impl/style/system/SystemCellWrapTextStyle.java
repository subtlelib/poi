package org.subtlelib.poi.impl.style.system;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.subtlelib.poi.api.style.SimpleStyle;

public enum SystemCellWrapTextStyle implements SimpleStyle {
	
	WRAP_TEXT;
	
	@Override
	public void enrich(HSSFWorkbook workbook, HSSFCellStyle style) {
		style.setWrapText(true);
	}

	@Override
	public Enum<SystemStyleType> getType() {
		return SystemStyleType.CELL_WRAP_TEXT;
	}

}