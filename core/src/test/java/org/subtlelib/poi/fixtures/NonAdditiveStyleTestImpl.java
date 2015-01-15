package org.subtlelib.poi.fixtures;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.subtlelib.poi.api.style.Style;

import com.google.common.base.Objects;

public class NonAdditiveStyleTestImpl implements Style {
    private final String id;

    public NonAdditiveStyleTestImpl(String id) {
        this.id = id;
    }

    @Override
    public void enrich(Workbook workbook, CellStyle style) {
        //do nothing
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NonAdditiveStyleTestImpl
                && Objects.equal(id, NonAdditiveStyleTestImpl.class.cast(obj).id);
    }
}
