package org.subtlelib.poi.impl.style;

import org.junit.Test;
import org.subtlelib.poi.api.style.AdditiveStyle;
import org.subtlelib.poi.fixtures.AdditiveStyleTestImpl;
import org.subtlelib.poi.fixtures.StyleType;

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
