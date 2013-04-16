package org.subtlelib.poi.api.style;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Designates the class that holds some formatting settings.<br/>
 * Implement this class directly only if you need some basic non-additive style.
 * Otherwise, consider implementing more specific interface: {@link AdditiveStyle}<br/>
 * Classes that implement Style should be immutable, because they are being
 * used as keys in a map in StyleRegistry.
 */
public interface Style {

    public void enrich(HSSFWorkbook workbook, HSSFCellStyle style);
}