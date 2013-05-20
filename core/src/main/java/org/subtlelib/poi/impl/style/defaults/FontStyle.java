package org.subtlelib.poi.impl.style.defaults;

import static org.apache.poi.hssf.usermodel.HSSFFont.FONT_ARIAL;
import static org.apache.poi.ss.usermodel.CellStyle.ALIGN_LEFT;
import static org.apache.poi.ss.usermodel.CellStyle.ALIGN_RIGHT;
import static org.apache.poi.ss.usermodel.CellStyle.VERTICAL_BOTTOM;
import static org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD;
import static org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_NORMAL;
import static org.apache.poi.ss.usermodel.Font.U_NONE;
import static org.apache.poi.ss.usermodel.Font.U_SINGLE;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.subtlelib.poi.api.style.AdditiveStyle;

public enum FontStyle implements AdditiveStyle {
	NORMAL(FONT_ARIAL, 10, BOLDWEIGHT_NORMAL, U_NONE, ALIGN_LEFT, VERTICAL_BOTTOM),
    ITALIC(FONT_ARIAL, 10, BOLDWEIGHT_NORMAL, U_NONE, ALIGN_LEFT, VERTICAL_BOTTOM, true),
	NORMAL_RIGHT(FONT_ARIAL, 10, BOLDWEIGHT_NORMAL, U_NONE, ALIGN_RIGHT, VERTICAL_BOTTOM),
	BOLD(FONT_ARIAL, 10, BOLDWEIGHT_BOLD, U_NONE, ALIGN_LEFT, VERTICAL_BOTTOM),
	BOLD_RIGHT(FONT_ARIAL, 10, BOLDWEIGHT_BOLD, U_NONE, ALIGN_RIGHT, VERTICAL_BOTTOM),

	COLUMN_HEADER(FONT_ARIAL, 10, BOLDWEIGHT_BOLD, U_NONE, ALIGN_LEFT, VERTICAL_BOTTOM),
	COLUMN_HEADER_RIGHT(FONT_ARIAL, 10, BOLDWEIGHT_BOLD, U_NONE, ALIGN_RIGHT, VERTICAL_BOTTOM),
	SECTION_HEADER(FONT_ARIAL, 10, BOLDWEIGHT_BOLD, U_SINGLE, ALIGN_LEFT, VERTICAL_BOTTOM),
	SECTION_HEADER_RIGHT(FONT_ARIAL, 10, BOLDWEIGHT_BOLD, U_SINGLE, ALIGN_RIGHT, VERTICAL_BOTTOM),
    PRIMARY_HEADER(FONT_ARIAL, 12, BOLDWEIGHT_BOLD, U_SINGLE, ALIGN_LEFT, VERTICAL_BOTTOM),
    SECONDARY_HEADER(FONT_ARIAL, 14, BOLDWEIGHT_BOLD, U_NONE, ALIGN_LEFT, VERTICAL_BOTTOM);

    private final String name;
    private final short height;
    private final short boldWeight;
    private final byte underline;
    private final short horizontalAlignment;
    private final short verticalAlignment;
    private final boolean italic;

	private FontStyle(String name, Integer height, short boldWeight, byte underline, short horizontalAlignment,
                      short verticalAlignment) {
        this(name, height, boldWeight, underline, horizontalAlignment, verticalAlignment, false);
	}

    private FontStyle(String name, Integer height, short boldWeight, byte underline, short horizontalAlignment,
                      short verticalAlignment, boolean italic) {
        this.name = name;
        this.height = height.shortValue();
        this.boldWeight = boldWeight;
        this.underline = underline;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
        this.italic = italic;
    }

	@Override
	public void enrich(HSSFWorkbook workbook, HSSFCellStyle style) {
        HSSFFont font = workbook.createFont();
        font.setFontName(name);
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
