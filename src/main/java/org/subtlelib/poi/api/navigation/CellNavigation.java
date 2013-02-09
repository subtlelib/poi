package org.subtlelib.poi.api.navigation;


public interface CellNavigation<T> {

    /** Skip the next cell position and move the pointer one cell further */
    public T skipCell();

    /** Skip specified number of cells */
    public T skipCells(int i);

    /** Set the next cell location (in absolute values)*/
    public T cellAt(int i);

}
