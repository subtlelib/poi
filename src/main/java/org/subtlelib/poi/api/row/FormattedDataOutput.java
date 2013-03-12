package org.subtlelib.poi.api.row;

public interface FormattedDataOutput {

    public RowContext header(String text);
    public RowContext total(String text);

    public RowContext percentage(Number number);

}
