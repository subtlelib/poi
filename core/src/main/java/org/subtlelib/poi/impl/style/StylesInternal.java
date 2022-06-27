package org.subtlelib.poi.impl.style;

import org.subtlelib.poi.api.style.AdditiveStyle;
import org.subtlelib.poi.api.style.Style;
import org.subtlelib.poi.api.style.Styles;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

/**
 * Created on 10/04/13
 * @author d.serdiuk
 */
public class StylesInternal {
    /**
     * @param style1 not-null
     * @param style2 not-null
     * @return if at least one style given is non-additive, second passed style is returned (it 'overrides' the 1st).
     * Otherwise, styles are combined and a composite style is returned.
     */
    public static Style combineOrOverride(Style style1, Style style2) {
        requireNonNull(style1, "first style to combine cannot be null");
        requireNonNull(style2, "second style to combine cannot be null");

        if (!(style1 instanceof AdditiveStyle) || !(style2 instanceof AdditiveStyle)) {
            return style2;
        }
        return Styles.combine(Arrays.asList((AdditiveStyle) style1, (AdditiveStyle) style2));
    }
}
