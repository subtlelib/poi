package io.github.subtlelib.poi.impl.style.defaults;

import java.util.Locale;

import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.util.DateFormatConverter;
import io.github.subtlelib.poi.api.style.AdditiveStyle;
import io.github.subtlelib.poi.api.workbook.WorkbookContext;

public enum DataStyle implements AdditiveStyle {

	INTEGER("0"),
    AMOUNT("#,##0.00"),
	PERCENTAGE("0.00000%"),

	DATE(DateFormatConverter.convert(Locale.US, "dd-mmm-yyyy")),
	DATE_NUMERIC(DateFormatConverter.convert(Locale.US, "dd-mm-yyyy"));

    private final String format;

    DataStyle(String format) {
        this.format = format;
    }

	@Override
	public void enrich(WorkbookContext workbookContext, org.apache.poi.ss.usermodel.CellStyle style) {
        DataFormat dataFormat = workbookContext.toNativeWorkbook().createDataFormat();
        style.setDataFormat(dataFormat.getFormat(format));
	}

	@Override
	public Enum<StyleType> getType() {
		return StyleType.DATA;
	}
}