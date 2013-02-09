package org.subtlelib.poi.api.row;

import org.subtlelib.poi.api.style.Style;

public interface PlainDataOutput {

    public RowContext text(String text);
    public RowContext text(String text, Style style);
    public RowContext optionalText(String text);
    public RowContext optionalText(String text, Style style);
	
    public RowContext number(Number number);
    public RowContext number(Number number, Style style);
    public RowContext optionalNumber(Number number);
    public RowContext optionalNumber(Number number, Style style);

}
