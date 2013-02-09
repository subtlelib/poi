package org.subtlelib.poi.api.style;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface Style {

	public HSSFCellStyle toNativeStyle(HSSFWorkbook workbook);
	
}
