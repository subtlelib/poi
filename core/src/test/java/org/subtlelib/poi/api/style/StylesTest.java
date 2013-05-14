package org.subtlelib.poi.api.style;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.subtlelib.poi.fixtures.AdditiveStyleTestImpl;
import org.subtlelib.poi.fixtures.StyleType;
import org.subtlelib.poi.impl.style.CompositeStyle;

import com.google.common.collect.ImmutableList;

/**
 * Created on 10/04/13
 * @author d.serdiuk
 */
public class StylesTest {

    private AdditiveStyle style1type1 = new AdditiveStyleTestImpl("style1", StyleType.type1);
    private AdditiveStyle style2type1 = new AdditiveStyleTestImpl("style2", StyleType.type1);
    private AdditiveStyle style3type2 = new AdditiveStyleTestImpl("style3", StyleType.type2);

    @Test(expected = NullPointerException.class)
    public void testCombine_nullList_exception() {
        List<AdditiveStyle> list = null;
        Styles.combine(list);
    }

    @Test(expected = NullPointerException.class)
    public void testCombine_nullVarargs_exception() {
        Styles.combine(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCombine_emptyList_exception() {
        Styles.combine(ImmutableList.<AdditiveStyle>of());
    }

    @Test
    public void testCombine_oneStyleInList_itIsReturned() {
        // do
        AdditiveStyle result = Styles.combine(style1type1);

        // verify
        assertEquals(style1type1, result);
    }

    @Test
    public void testCombine_stylePassedDirectlyAndTheSameStyleInsideComposite_resultHasOneStyle() {
        // given
        AdditiveStyle compositeFromStyle1 = new CompositeStyle(ImmutableList.of(style1type1));

        // do
        CompositeStyle result = (CompositeStyle) Styles.combine(compositeFromStyle1, style1type1);

        // verify
        assertEquals(1, result.getStyles().size());
        assertEquals(style1type1, result.getStyles().iterator().next());
    }

    @Test
    public void testCombine_oneStyleWrappedInsideCompositeAndSecondStyleOfDifferentType_resultHasBoth() {
        // given
        AdditiveStyle compositeFromStyle1 = new CompositeStyle(ImmutableList.of(style1type1));

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
