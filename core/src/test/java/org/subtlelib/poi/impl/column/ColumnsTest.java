package org.subtlelib.poi.impl.column;

import static org.junit.Assert.assertEquals;
import static org.subtlelib.poi.impl.column.Columns.columnIndexAsLetters;

import org.junit.Test;

/**
 * Created on 02/04/13
 * @author d.serdiuk
 */
public class ColumnsTest {
    @Test
    public void testColumnIndexAsLetters() {
        assertEquals("A", columnIndexAsLetters(1));
        assertEquals("B", columnIndexAsLetters(2));
        assertEquals("Z", columnIndexAsLetters(26));
        assertEquals("AA", columnIndexAsLetters(27));
        assertEquals("IV", columnIndexAsLetters(256));
        assertEquals("ZZ", columnIndexAsLetters(702));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testColumnIndexExceedsMaxLimit() {
        columnIndexAsLetters(703);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testColumnIndexIsNotPositive() {
        columnIndexAsLetters(0);
    }
}
