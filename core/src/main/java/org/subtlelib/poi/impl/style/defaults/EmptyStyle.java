package org.subtlelib.poi.impl.style.defaults;

import org.subtlelib.poi.api.style.Style;
import org.subtlelib.poi.api.workbook.WorkbookContext;

/**
 * An empty style that does nothing.
 * Created on 10/04/13
 * @author d.serdiuk
 */
public class EmptyStyle implements Style {
    @Override
    public void enrich(WorkbookContext workbookContext, org.apache.poi.ss.usermodel.CellStyle style) {}
    public static final Style instance = new EmptyStyle();
}
