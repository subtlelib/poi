package org.subtlelib.poi.api.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.subtlelib.poi.api.workbook.WorkbookContext;

/**
 * <p>
 * Designates the class that holds some formatting settings.
 * Implement this class directly only if you need some basic non-additive style.
 * Otherwise, consider implementing more specific interface: {@link AdditiveStyle}
 * </p>
 * <p>
 * Classes that implement Style should be immutable, because they are being
 * used as keys in a map in StyleRegistry.
 * </p>
 */
public interface Style {

    public void enrich(WorkbookContext workbookContext, CellStyle style);
}