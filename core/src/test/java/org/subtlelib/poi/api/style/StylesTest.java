package org.subtlelib.poi.api.style;

import org.junit.Test;
import org.subtlelib.poi.fixtures.AdditiveStyleTestImpl;
import org.subtlelib.poi.fixtures.StyleType;
import org.subtlelib.poi.impl.style.CompositeStyle;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created on 10/04/13
 * @author d.serdiuk
 */
public class StylesTest {

    private final AdditiveStyle style1type1 = new AdditiveStyleTestImpl("style1", StyleType.type1);
    private final AdditiveStyle style2type1 = new AdditiveStyleTestImpl("style2", StyleType.type1);
    private final AdditiveStyle style3type2 = new AdditiveStyleTestImpl("style3", StyleType.type2);

    @Test(expected = NullPointerException.class)
    public void testCombine_nullList_exception() {
        List<AdditiveStyle> list = null;
        Styles.combine(list);
    }

    @Test(expected = NullPointerException.class)
    public void testCombine_nullVarargs_exception() {
        Styles.combine(null, null);
    }

    @Test
    public void testCombine_oneStyleInList_itIsReturned() {
        // do
        AdditiveStyle result = Styles.combine(style1type1);

        // verify
        assertEquals(style1type1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCombine_emptyList_exception() {
        Styles.combine(emptyList());
    }

    @Test
    public void testCombine_stylePassedDirectlyAndTheSameStyleInsideComposite_resultHasOneStyle() {
        // given
        AdditiveStyle compositeFromStyle1 = new CompositeStyle(singletonList(style1type1));

        // do
        CompositeStyle result = (CompositeStyle) Styles.combine(compositeFromStyle1, style1type1);

        // verify
        assertEquals(1, result.getStyles().size());
        assertEquals(style1type1, result.getStyles().iterator().next());
    }

    @Test
    public void testCombine_oneStyleWrappedInsideCompositeAndSecondStyleOfDifferentType_resultHasBoth() {
        // given
        AdditiveStyle compositeFromStyle1 = new CompositeStyle(singletonList(style1type1));

        // do
        CompositeStyle result = (CompositeStyle) Styles.combine(compositeFromStyle1, style3type2);

        // verify
        assertEquals(2, result.getStyles().size());
        assertTrue(result.getStyles().contains(style1type1));
        assertTrue(result.getStyles().contains(style3type2));
    }

    @Test
    public void testCombine_twoStylesOfSameType_secondOverridesFirst() {
        // do
        CompositeStyle result = (CompositeStyle) Styles.combine(style2type1, style1type1);

        // verify
        assertEquals(1, result.getStyles().size());
        assertEquals(style1type1, result.getStyles().iterator().next());
    }
}
