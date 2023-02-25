package io.github.subtlelib.poi.impl.style;

import java.util.*;

import org.apache.poi.ss.usermodel.CellStyle;
import io.github.subtlelib.poi.api.style.AdditiveStyle;
import io.github.subtlelib.poi.api.style.Style;
import io.github.subtlelib.poi.api.style.Styles;
import io.github.subtlelib.poi.api.workbook.WorkbookContext;

/**
 * Don't instantiate this class by yourself,
 * use {@link Styles#combine(AdditiveStyle...)} if needed
 */
public final class CompositeStyle implements AdditiveStyle {
	private final Map<Enum<?>, AdditiveStyle> styles;
	
    public CompositeStyle(List<AdditiveStyle> partialStyles) {
        Map<Enum<?>, AdditiveStyle> combined = new LinkedHashMap<>();
        for (AdditiveStyle partialStyle : partialStyles) {
            combined.put(partialStyle.getType(), partialStyle);
        }
        styles = combined;
    }

	@Override
	public void enrich(WorkbookContext workbookContext, CellStyle nativeStyle) {
		for (Style style : styles.values()) {
			style.enrich(workbookContext, nativeStyle);
		}
	}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositeStyle that = (CompositeStyle) o;

        return Objects.equals(styles, that.styles);
    }

    @Override
    public int hashCode() {
        return styles != null ? styles.hashCode() : 0;
    }

    public Collection<AdditiveStyle> getStyles() {
        return styles.values();
    }

    // CompositeStyle implements AdditiveStyle because it's additive, however the routine for
    // adding such classes is captured in Styles class and getType() is never called.
    // Separating AdditiveStyle and TypedAdditiveStyle would imply
    // making org.subtlelib.poi.api.style.Styles#combine narrowing the type from TypedAdditiveStyle
    // to just AdditiveStyle (because Composite would not implement TypedAdditiveStyle).
    // in this case user will not be able to combine styles by himself more than once.
    @Override
    public Enum<?> getType() {
        throw new UnsupportedOperationException("CompositeStyle does not have type. " +
                "Instead, get it's parts - they have their types.");
    }
}
