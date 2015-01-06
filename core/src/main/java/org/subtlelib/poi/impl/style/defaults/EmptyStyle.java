package org.subtlelib.poi.impl.style.defaults;

import org.apache.poi.ss.usermodel.Workbook;
import org.subtlelib.poi.api.style.Style;

/**
 * An empty style that does nothing.
 * Created on 10/04/13
 * @author d.serdiuk
 */
public class EmptyStyle implements Style {
    @Override
    public void enrich(Workbook workbook, org.apache.poi.ss.usermodel.CellStyle style) {}
    public static final Style instance = new EmptyStyle();
}
