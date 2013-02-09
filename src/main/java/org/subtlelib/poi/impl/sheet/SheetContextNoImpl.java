package org.subtlelib.poi.impl.sheet;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.subtlelib.poi.api.row.RowContext;
import org.subtlelib.poi.api.sheet.SheetContext;
import org.subtlelib.poi.api.style.Style;
import org.subtlelib.poi.api.style.StyleConfiguration;
import org.subtlelib.poi.impl.row.RowContextNoImpl;
import org.subtlelib.poi.impl.style.DefaultStyleConfiguration;

public class SheetContextNoImpl extends DefaultStyleConfiguration implements SheetContext {

	private final SheetContext parent;
	
	private final RowContext noImplRowContext = new RowContextNoImpl(this);
	
	public SheetContextNoImpl(SheetContext parent) {
		this.parent = parent;
	}

	@Override
	public RowContext nextRow() {
		return noImplRowContext;
	}

	@Override
	public RowContext nextConditionalRow(boolean condition) {
		return noImplRowContext;
	}

	@Override
	public RowContext currentRow() {
		return noImplRowContext;
	}
	
	@Override
	public SheetContext skipRow() {
		return this;
	}

	@Override
	public SheetContext skipRows(int linesNumber) {
		return this;
	}

	@Override
	public SheetContext startConditionalBlock(boolean condition) {
		return this;
	}

	@Override
	public SheetContext endConditionalBlock() {
		return parent;
	}
	
	@Override
	public HSSFSheet getNativeSheet() {
		throw new UnsupportedOperationException("Native sheet is not supported by SheetContextNoImpl");
	}

	@Override
	public SheetContext setColumnWidths(int... multipliers) {
		return this;
	}
	
	@Override
	public SheetContext hideGrid() {
		return this;
	}
	
	@Override
	public SheetContext setStyleConfiguration(StyleConfiguration styleConfiguration) {
		return this;
	}

	@Override
	public StyleConfiguration getStyleConfiguration() {
		throw new UnsupportedOperationException("No style configuration defined for SheetContextNoImpl");
	}

	@Override
	public SheetContext setTotalStyle(Style style) {
		return this;
	}

	@Override
	public SheetContext setHeaderStyle(Style style) {
		return this;
	}

	@Override
	public SheetContext setPercentageStyle(Style style) {
		return this;
	}
	
	@Override
	public SheetContext setTextStyle(Style style) {
		return this;
	}

	@Override
	public SheetContext setNumberStyle(Style style) {
		return this;
	}

}