package com.github.subtlelib.poi.impl.style;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import com.github.subtlelib.poi.fixtures.AdditiveStyleTestImpl;
import com.github.subtlelib.poi.fixtures.NonAdditiveStyleTestImpl;
import com.github.subtlelib.poi.fixtures.StyleType;
import org.junit.Test;
import com.github.subtlelib.poi.api.style.AdditiveStyle;
import com.github.subtlelib.poi.api.style.Style;


/**
 * Created on 10/04/13
 * @author d.serdiuk
 */
public class StylesInternalTest {
    AdditiveStyle additive = new AdditiveStyleTestImpl("additive", StyleType.type1);
    AdditiveStyle additive2 = new AdditiveStyleTestImpl("additive2", StyleType.type2);
    Style nonAdditive = new NonAdditiveStyleTestImpl("non-additive");
    Style nonAdditive2 = new NonAdditiveStyleTestImpl("non-additive2");

    @Test
    public void testCombineOrOverride_firstIsNonAdditive_secondIsReturned() {
        // do
        Style result = StylesInternal.combineOrOverride(nonAdditive, additive);

        // verify
        assertEquals(additive, result);
    }

    @Test
    public void testCombineOrOverride_secondIsNonAdditive_secondIsReturned() {
        // do
        Style result = StylesInternal.combineOrOverride(additive, nonAdditive);

        // verify
        assertEquals(nonAdditive, result);
    }

    @Test
    public void testCombineOrOverride_bothNonAdditive_secondIsReturned() {
        // do
        Style result = StylesInternal.combineOrOverride(nonAdditive, nonAdditive2);

        // verify
        assertEquals(nonAdditive2, result);
    }

    @Test
    public void testCombineOrOverride_bothAdditive_compositeReturned() {
        // do
        Style result = StylesInternal.combineOrOverride(additive, additive2);

        // verify
        assertTrue(result instanceof CompositeStyle);
    }

    @Test
    public void testCombineOrOverride_bothAdditive_stylesReturnedInTheSameOrderAsAdded() {
        // do
        Style result = StylesInternal.combineOrOverride(additive, additive2);

        // verify
        Collection<AdditiveStyle> styles = ((CompositeStyle) result).getStyles();
        assertArrayEquals(new Style[] {additive, additive2}, styles.toArray());
    }

}
