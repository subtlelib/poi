package io.github.subtlelib.poi.fixtures;

import org.apache.poi.ss.usermodel.CellStyle;
import io.github.subtlelib.poi.api.style.Style;
import io.github.subtlelib.poi.api.workbook.WorkbookContext;

import java.util.Objects;

public class NonAdditiveStyleTestImpl implements Style {
    private final String id;

    public NonAdditiveStyleTestImpl(String id) {
        this.id = id;
    }

    @Override
    public void enrich(WorkbookContext workbookContext, CellStyle style) {
        //do nothing
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NonAdditiveStyleTestImpl that = (NonAdditiveStyleTestImpl) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
