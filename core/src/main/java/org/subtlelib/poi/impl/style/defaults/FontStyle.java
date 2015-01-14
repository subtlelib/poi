package org.subtlelib.poi.impl.style.defaults;

import static org.apache.poi.ss.usermodel.CellStyle.ALIGN_LEFT;
import static org.apache.poi.ss.usermodel.CellStyle.ALIGN_RIGHT;
import static org.apache.poi.ss.usermodel.CellStyle.VERTICAL_BOTTOM;
import static org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD;
import static org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_NORMAL;
import static org.apache.poi.ss.usermodel.Font.U_NONE;
import static org.apache.poi.ss.usermodel.Font.U_SINGLE;

import org.apache.poi.ss.usermodel.Font;
import org.subtlelib.poi.api.style.AdditiveStyle;
import org.subtlelib.poi.api.workbook.WorkbookContext;

public enum FontStyle implements AdditiveStyle {
    /** All these styles use default font (Calibri for xlsx files, Arial for xls files) */
	NORMAL(10, BOLDWEIGHT_NORMAL, U_NONE, ALIGN_LEFT, VERTICAL_BOTTOM),
    ITALIC(10, BOLDWEIGHT_NORMAL, U_NONE, ALIGN_LEFT, VERTICAL_BOTTOM, true),
	NORMAL_RIGHT(10, BOLDWEIGHT_NORMAL, U_NONE, ALIGN_RIGHT, VERTICAL_BOTTOM),
	BOLD(10, BOLDWEIGHT_BOLD, U_NONE, ALIGN_LEFT, VERTICAL_BOTTOM),
	BOLD_RIGHT(10, BOLDWEIGHT_BOLD, U_NONE, ALIGN_RIGHT, VERTICAL_BOTTOM),

	COLUMN_HEADER(10, BOLDWEIGHT_BOLD, U_NONE, ALIGN_LEFT, VERTICAL_BOTTOM),
	COLUMN_HEADER_RIGHT(10, BOLDWEIGHT_BOLD, U_NONE, ALIGN_RIGHT, VERTICAL_BOTTOM),
	SECTION_HEADER(10, BOLDWEIGHT_BOLD, U_SINGLE, ALIGN_LEFT, VERTICAL_BOTTOM),
	SECTION_HEADER_RIGHT(10, BOLDWEIGHT_BOLD, U_SINGLE, ALIGN_RIGHT, VERTICAL_BOTTOM),
    PRIMARY_HEADER(12, BOLDWEIGHT_BOLD, U_SINGLE, ALIGN_LEFT, VERTICAL_BOTTOM),
    SECONDARY_HEADER(14, BOLDWEIGHT_BOLD, U_NONE, ALIGN_LEFT, VERTICAL_BOTTOM);

    private final short height;
    private final short boldWeight;
    private final byte underline;
    private final short horizontalAlignment;
    private final short verticalAlignment;
    private final boolean italic;

	private FontStyle(Integer height, short boldWeight, byte underline, short horizontalAlignment,
                      short verticalAlignment) {
        this(height, boldWeight, underline, horizontalAlignment, verticalAlignment, false);
	}

    private FontStyle(Integer height, short boldWeight, byte underline, short horizontalAlignment,
                      short verticalAlignment, boolean italic) {
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
        font.setBoldweight(boldWeight);
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
