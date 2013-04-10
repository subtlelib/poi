package org.subtlelib.poi.fixtures;

import java.util.Objects;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.subtlelib.poi.api.style.AdditiveStyle;

public class AdditiveStyleTestImpl implements AdditiveStyle {

    private final String id;
    private final StyleType type;

    public AdditiveStyleTestImpl(String id, StyleType type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public Enum<?> getType() {
        return type;
    }

    @Override
    public void enrich(HSSFWorkbook workbook, HSSFCellStyle style) {
        //do nothing
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AdditiveStyleTestImpl) {
            AdditiveStyleTestImpl that = (AdditiveStyleTestImpl) obj;
            return Objects.equals(this.id, that.id) && Objects.equals(this.type, that.type);
        }
        return false;
    }
}
