package org.subtlelib.poi.impl.style;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.subtlelib.poi.api.style.AdditiveStyle;
import org.subtlelib.poi.api.style.Style;
import org.subtlelib.poi.api.style.Styles;
import org.subtlelib.poi.api.workbook.WorkbookContext;

import com.google.common.collect.ImmutableMap;

/**
 * Don't instantiate this class by yourself,
 * use {@link Styles#combine(AdditiveStyle...)} if needed
 */
public final class CompositeStyle implements AdditiveStyle {
	private final ImmutableMap<Enum<?>, AdditiveStyle> styles;
	
    public CompositeStyle(List<AdditiveStyle> partialStyles) {
        Map<Enum<?>, AdditiveStyle> combined = new LinkedHashMap<Enum<?>, AdditiveStyle>();
        for (AdditiveStyle partialStyle : partialStyles) {
            combined.put(partialStyle.getType(), partialStyle);
        }
        styles = ImmutableMap.copyOf(combined);
    }

	@Override
	public void enrich(WorkbookContext workbookContext, CellStyle nativeStyle) {
		for (Style style : styles.values()) {
			style.enrich(workbookContext, nativeStyle);
		}
	}


	@Override
	public int hashCode() {
        // May add caching here if needed
        // (ImmutableMap.hashCode() does no caching and TypeAdditiveStyle instances must be immutable)
        return styles.hashCode();
    }

    @Override
	public boolean equals(Object obj) {
		if (obj instanceof CompositeStyle) {
			CompositeStyle other = CompositeStyle.class.cast(obj);
			return styles.equals(other.styles);
		}
		return false;
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
