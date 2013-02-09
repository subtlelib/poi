package org.subtlelib.poi.api.row;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.subtlelib.poi.api.condition.CellCondition;
import org.subtlelib.poi.api.condition.RowCondition;
import org.subtlelib.poi.api.navigation.CellNavigation;
import org.subtlelib.poi.api.navigation.RowNavigation;
import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.api.style.StyleConfigurable;
import org.subtlelib.poi.api.style.StyleConfiguration;

public interface RowContext extends PlainDataOutput, FormattedDataOutput, CellNavigation<RowContext>, CellCondition<RowContext>,
		RowNavigation<SheetContext, RowContext>, RowCondition<RowContext>, StyleConfiguration, StyleConfigurable<RowContext> {

    public HSSFRow getNativeRow();

}