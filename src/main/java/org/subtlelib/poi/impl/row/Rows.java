package org.subtlelib.poi.impl.row;

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Created on 15/03/13
 * @author d.serdiuk
 */
public class Rows {
    public static Row getOrCreate(Sheet sheet, int rowNum) {
        Row row = checkNotNull(sheet, "sheet cannot be null").getRow(rowNum);
        if (row == null) {
            return sheet.createRow(rowNum);
        } else {
            return row;
        }
    }
}
