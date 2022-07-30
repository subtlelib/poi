package com.github.subtlelib.poi.impl.style.defaults;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import com.github.subtlelib.poi.api.style.AdditiveStyle;
import com.github.subtlelib.poi.api.workbook.WorkbookContext;

import static org.apache.poi.ss.usermodel.Font.U_NONE;
import static org.apache.poi.ss.usermodel.Font.U_SINGLE;
import static org.apache.poi.ss.usermodel.HorizontalAlignment.LEFT;
import static org.apache.poi.ss.usermodel.HorizontalAlignment.RIGHT;
import static org.apache.poi.ss.usermodel.VerticalAlignment.BOTTOM;

public enum FontStyle implements AdditiveStyle {
    /** All these styles use default font (Calibri for xlsx files, Arial for xls files) */
    NORMAL(10, false, U_NONE, LEFT, BOTTOM),
    ITALIC(10, false, U_NONE, LEFT, BOTTOM, true),
    NORMAL_RIGHT(10, false, U_NONE, RIGHT, BOTTOM),
    BOLD(10, true, U_NONE, LEFT, BOTTOM),
    BOLD_RIGHT(10, true, U_NONE, RIGHT, BOTTOM),

    COLUMN_HEADER(10, true, U_NONE, LEFT, BOTTOM),
    COLUMN_HEADER_RIGHT(10, true, U_NONE, RIGHT, BOTTOM),
    SECTION_HEADER(10, true, U_SINGLE, LEFT, BOTTOM),
    SECTION_HEADER_RIGHT(10, true, U_SINGLE, RIGHT, BOTTOM),
    PRIMARY_HEADER(12, true, U_SINGLE, LEFT, BOTTOM),
    SECONDARY_HEADER(14, true, U_NONE, LEFT, BOTTOM);

    private final short               height;
    private final boolean             boldWeight;
    private final byte                underline;
    private final HorizontalAlignment horizontalAlignment;
    private final VerticalAlignment   verticalAlignment;
    private final boolean             italic;

    FontStyle(Integer height, boolean boldWeight, byte underline, HorizontalAlignment horizontalAlignment,
              VerticalAlignment verticalAlignment) {
        this(height, boldWeight, underline, horizontalAlignment, verticalAlignment, false);
    }

    FontStyle(Integer height, boolean boldWeight, byte underline, HorizontalAlignment horizontalAlignment,
              VerticalAlignment verticalAlignment, boolean italic) {
        this.height = height.shortValue();
        this.boldWeight = boldWeight;
        this.underline = underline;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
        this.italic = italic;
    }

    @Override
    public void enrich(WorkbookContext workbookContext, org.apache.poi.ss.usermodel.CellStyle style) {
        Font font = workbookContext.toNativeWorkbook().createFont();
        font.setFontName(workbookContext.getDefaultFontName());
        font.setFontHeightInPoints(height);
        font.setBold(boldWeight);
        font.setUnderline(underline);
        font.setItalic(italic);
        style.setFont(font);

        style.setAlignment(horizontalAlignment);
        style.setVerticalAlignment(verticalAlignment);
    }

    @Override
    public Enum<StyleType> getType() {
        return StyleType.FONT;
    }
}
