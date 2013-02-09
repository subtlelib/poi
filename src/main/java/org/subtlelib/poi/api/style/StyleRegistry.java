package org.subtlelib.poi.api.style;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;

public interface StyleRegistry {

	public HSSFCellStyle registerStyle(Style style);
	
}
