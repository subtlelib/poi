package org.subtlelib.poi.fixtures;

import java.util.Objects;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.subtlelib.poi.api.style.Style;

public class NonAdditiveStyleTestImpl implements Style {
    private final String id;

    public NonAdditiveStyleTestImpl(String id) {
        this.id = id;
    }

    @Override
    public void enrich(HSSFWorkbook workbook, HSSFCellStyle style) {
        //do nothing
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NonAdditiveStyleTestImpl
                && Objects.equals(id, NonAdditiveStyleTestImpl.class.cast(obj).id);
    }
}
