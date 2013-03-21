package org.subtlelib.poi.impl.row;

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * Created on 15/03/13
 * @author d.serdiuk
 */
public class HSSFRows {
    public static HSSFRow getOrCreate(HSSFSheet sheet, int rowNum) {
        HSSFRow row = checkNotNull(sheet, "sheet cannot be null").getRow(rowNum);
        if (row == null) {
            return sheet.createRow(rowNum);
        } else {
            return row;
        }
    }
}
