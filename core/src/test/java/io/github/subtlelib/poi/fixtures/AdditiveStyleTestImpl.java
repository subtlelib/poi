package io.github.subtlelib.poi.fixtures;

import org.apache.poi.ss.usermodel.CellStyle;
import io.github.subtlelib.poi.api.style.AdditiveStyle;
import io.github.subtlelib.poi.api.workbook.WorkbookContext;

import java.util.Objects;

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
    public void enrich(WorkbookContext workbookContext, CellStyle style) {
        //do nothing
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdditiveStyleTestImpl that = (AdditiveStyleTestImpl) o;

        if (!Objects.equals(id, that.id)) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return id;
    }

}
