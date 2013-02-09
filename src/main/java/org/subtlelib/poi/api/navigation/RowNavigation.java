package org.subtlelib.poi.api.navigation;


public interface RowNavigation<T, R> {

    public R nextRow();
    
    public T skipRow();
    public T skipRows(int linesNumber);

}
