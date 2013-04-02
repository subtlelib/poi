package org.subtlelib.poi.impl.row;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.api.style.Style;
import org.subtlelib.poi.api.style.StyleConfiguration;
import org.subtlelib.poi.api.style.StyleRegistry;
import org.subtlelib.poi.api.totals.ColumnTotalsDataRange;
import org.subtlelib.poi.api.totals.Formula;

/**
 * Created on 02/04/13
 * @author d.serdiuk
 */
public class RowContextImplTest {
    Row row = mock(Row.class);
    SheetContext sheetContext = mock(SheetContext.class);
    StyleRegistry styleRegistry = mock(StyleRegistry.class);
    StyleConfiguration styleConfig = mock(StyleConfiguration.class);
    RowContextImpl rowContext;
    Cell cell = mock(Cell.class);
    Style style = mock(Style.class);
    ColumnTotalsDataRange dataRange = mock(ColumnTotalsDataRange.class);
    ArgumentCaptor<RichTextString> richTextStringCaptor = ArgumentCaptor.forClass(RichTextString.class);

    @Before
    public void setUp() throws Exception {
        when(sheetContext.getStyleConfiguration()).thenReturn(styleConfig);
        when(sheetContext.getDateStyle()).thenReturn(style);
        when(sheetContext.getHeaderStyle()).thenReturn(style);
        when(sheetContext.getNumberStyle()).thenReturn(style);
        when(sheetContext.getPercentageStyle()).thenReturn(style);
        when(sheetContext.getTextStyle()).thenReturn(style);
        when(sheetContext.getTotalStyle()).thenReturn(style);

        rowContext = new RowContextImpl(row, sheetContext, styleRegistry, 1);

        when(row.createCell(anyInt())).thenReturn(cell);
    }

    @Test
    public void testTotalsFromFormula() {
        // given
        setDataRange2to15();

        // do
        rowContext.setTotalsDataBlock(dataRange)
                .total(Formula.SUM)
                .total(Formula.SUM);

        // verify
        verify(cell, times(2)).setCellValue(richTextStringCaptor.capture());
        List<RichTextString> results = richTextStringCaptor.getAllValues();
        assertEquals("SUM(A2:A15)", results.get(0).getString());
        assertEquals("SUM(B2:B15)", results.get(1).getString());
    }

    @Test
    public void testTotalFromFormula2timesSkippingFirst27Columns() {
        // given
        setDataRange2to15();

        // do
        rowContext.setTotalsDataBlock(dataRange)
                .skipCells(27)
                .totals(Formula.SUM, 2);

        // verify
        verify(cell, times(2)).setCellValue(richTextStringCaptor.capture());
        List<RichTextString> results = richTextStringCaptor.getAllValues();
        assertEquals("SUM(AB2:AB15)", results.get(0).getString());
        assertEquals("SUM(AC2:AC15)", results.get(1).getString());
    }

    private void setDataRange2to15() {
        when(dataRange.getStartLineNo()).thenReturn(2);
        when(dataRange.getEndLineNo()).thenReturn(15);
    }
}