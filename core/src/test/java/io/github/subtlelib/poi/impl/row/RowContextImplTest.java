package io.github.subtlelib.poi.impl.row;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import io.github.subtlelib.poi.api.sheet.SheetContext;
import io.github.subtlelib.poi.api.style.Style;
import io.github.subtlelib.poi.api.style.StyleConfiguration;
import io.github.subtlelib.poi.api.style.StyleRegistry;
import io.github.subtlelib.poi.api.totals.ColumnTotalsDataRange;
import io.github.subtlelib.poi.api.totals.Formula;

import static org.mockito.Mockito.*;

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
    Sheet nativeSheet = mock(Sheet.class, Answers.RETURNS_DEEP_STUBS);

    @Before
    public void setUp() {
        when(sheetContext.getStyleConfiguration()).thenReturn(styleConfig);
        when(sheetContext.getDateStyle()).thenReturn(style);
        when(sheetContext.getHeaderStyle()).thenReturn(style);
        when(sheetContext.getNumberStyle()).thenReturn(style);
        when(sheetContext.getPercentageStyle()).thenReturn(style);
        when(sheetContext.getTextStyle()).thenReturn(style);
        when(sheetContext.getTotalStyle()).thenReturn(style);
        when(sheetContext.getNativeSheet()).thenReturn(nativeSheet);
        when(nativeSheet.getWorkbook().getCreationHelper().createFormulaEvaluator()
                .evaluateInCell(any(Cell.class))).thenReturn(null); //result is not used

        rowContext = new RowContextImpl(row, sheetContext, styleRegistry, 0);

        when(row.createCell(anyInt())).thenReturn(cell);
    }

    @Test
    public void testTotalsFromFormula() {
        // given
        setDataRange2to15();

        // do
        rowContext.setTotalsDataRange(dataRange)
                .total(Formula.SUM)
                .total(Formula.SUM);

        // verify
        verify(cell).setCellFormula("SUM(A2:A15)");
        verify(cell).setCellFormula("SUM(B2:B15)");
    }

    @Test
    public void testTotalFromFormula2timesSkippingFirst27Columns() {
        // given
        setDataRange2to15();

        // do
        rowContext.setTotalsDataRange(dataRange)
                .skipCells(27)
                .totals(Formula.SUM, 2);

        // verify
        verify(cell).setCellFormula("SUM(AB2:AB15)");
        verify(cell).setCellFormula("SUM(AC2:AC15)");
    }

    private void setDataRange2to15() {
        when(dataRange.getStartRowNo()).thenReturn(2);
        when(dataRange.getEndRowNo()).thenReturn(15);
    }
}