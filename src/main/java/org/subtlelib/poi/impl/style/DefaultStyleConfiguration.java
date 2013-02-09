package org.subtlelib.poi.impl.style;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.subtlelib.poi.api.style.Style;
import org.subtlelib.poi.api.style.StyleConfiguration;

/**
 * A default implementation sitting on the top of the hierarchy.
 * Not expected to be used as a root for the style hierarchy of your own.
 * Please consider enum-based implementation of {@link StyleConfiguration} and reuse it across the application.
 * 
 * @author i.voshkulat
 *
 */
public class DefaultStyleConfiguration implements StyleConfiguration {

	private static final Style COMMON_STYLE = new Style() {
		@Override
		public HSSFCellStyle toNativeStyle(HSSFWorkbook workbook) {
			return workbook.createCellStyle();
		}
	};
	
	@Override
	public Style getTextStyle() {
		return commonStyle();
	}

	@Override
	public Style getNumberStyle() {
		return commonStyle();
	}

	@Override
	public Style getTotalStyle() {
		return commonStyle();
	}

	@Override
	public Style getHeaderStyle() {
		return commonStyle();
	}

	@Override
	public Style getPercentageStyle() {
		return commonStyle();
	}	

	private Style commonStyle() {
		return COMMON_STYLE;
	}
	
}