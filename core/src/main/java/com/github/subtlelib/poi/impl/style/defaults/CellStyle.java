package com.github.subtlelib.poi.impl.style.defaults;

import org.apache.poi.ss.usermodel.BorderStyle;
import com.github.subtlelib.poi.api.style.AdditiveStyle;
import com.github.subtlelib.poi.api.workbook.WorkbookContext;

import static org.apache.poi.ss.usermodel.BorderStyle.DOUBLE;
import static org.apache.poi.ss.usermodel.BorderStyle.NONE;
import static org.apache.poi.ss.usermodel.BorderStyle.THIN;

public enum CellStyle implements AdditiveStyle {

    BORDERS_THIN_ALL(THIN, THIN, THIN, THIN),
    BORDERS_BOTTOM_THIN(NONE, NONE, THIN, NONE),
    BORDERS_TOP_BOTTOM_THIN(THIN, NONE, THIN, NONE),
    BORDERS_BOTTOM_DOUBLE(NONE, NONE, DOUBLE, NONE);

    private final BorderStyle borderTop;
    private final BorderStyle borderRight;
    private final BorderStyle borderBottom;
    private final BorderStyle borderLeft;

    CellStyle(BorderStyle borderTop, BorderStyle borderRight, BorderStyle borderBottom, BorderStyle borderLeft) {
        this.borderTop = borderTop;
        this.borderRight = borderRight;
        this.borderBottom = borderBottom;
        this.borderLeft = borderLeft;
    }

    @Override
    public void enrich(WorkbookContext workbookContext, org.apache.poi.ss.usermodel.CellStyle style) {
        style.setBorderTop(borderTop);
        style.setBorderRight(borderRight);
        style.setBorderBottom(borderBottom);
        style.setBorderLeft(borderLeft);
    }

    @Override
    public Enum<StyleType> getType() {
        return StyleType.CELL;
    }

}
