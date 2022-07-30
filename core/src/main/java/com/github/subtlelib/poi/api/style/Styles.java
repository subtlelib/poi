package com.github.subtlelib.poi.api.style;

import com.github.subtlelib.poi.impl.style.CompositeStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Created on 10/04/13
 * @author d.serdiuk
 */
public class Styles {
    /**
     * Combine a list of additive styles into one. Styles with greater indexes may partially override
     * styles with lesser indexes.
     * @param styles not-null list that cannot contain null styles
     * @return a style that has the features of passed styles
     */
    public static AdditiveStyle combine(List<AdditiveStyle> styles) {
        requireNonNull(styles, "styles to combine cannot be null");
        if (styles.isEmpty()) {
            throw new IllegalArgumentException("cannot combine an empty list of styles");
        }

        if (styles.size() == 1) {
            return styles.get(0);
        }

        List<AdditiveStyle> parts = new ArrayList<>();
        for (AdditiveStyle style : styles) {
            // can't use polymorphism in this case since we want to keep AdditiveStyle an interface so
            // that it can be implemented by user enums
            if (style instanceof CompositeStyle) {
                parts.addAll(((CompositeStyle) style).getStyles());
            } else {
                parts.add(style);
            }
        }
        return new CompositeStyle(parts);
    }

    /**
     * Combine an array of additive styles into one. Styles with greater indexes may partially override
     * styles with lesser indexes.
     * @param styles not-null list that cannot contain null styles
     * @return a style that has the features of passed styles
     */
    public static AdditiveStyle combine(AdditiveStyle... styles) {
        return combine(Arrays.asList(styles));
    }
}
