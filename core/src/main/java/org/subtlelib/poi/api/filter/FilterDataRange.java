package org.subtlelib.poi.api.filter;

/**
 * Created by beni on 10.12.14.
 */
public interface FilterDataRange {

	public void endOnCurrentRow();
	public void endOnCurrentCol();
	public void endOnPreviousRow();
	public void endOnPreviousCol();
	public void endOnRow(int rowOffset);
	public void endOnCol(int colOffset);
	public int getStartRowNo();
	public int getEndRowNo();
	public int getStartColNo();
	public int getEndColNo();
	public boolean isEndRowMarked();
	public boolean isEndColMarked();
	public String toString();
	
}
