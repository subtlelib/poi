package com.github.subtlelib.poi.impl.style;

import com.github.subtlelib.poi.fixtures.AdditiveStyleTestImpl;
import com.github.subtlelib.poi.fixtures.StyleType;
import org.junit.Test;
import com.github.subtlelib.poi.api.style.AdditiveStyle;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class CompositeStyleTest {
    private final AdditiveStyle style1type1 = new AdditiveStyleTestImpl("style1", StyleType.type1);

    @Test
    public void testEqualsContract() {
        // given
        CompositeStyle composite1 = new CompositeStyle(singletonList(style1type1));
        CompositeStyle composite2 = new CompositeStyle(singletonList(style1type1));

        // verify
        assertEquals(composite1, composite2);
    }
}
