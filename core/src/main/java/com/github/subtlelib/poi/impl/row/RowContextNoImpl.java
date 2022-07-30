package com.github.subtlelib.poi.impl.row;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import com.github.subtlelib.poi.api.row.RowContext;
import com.github.subtlelib.poi.api.sheet.SheetContext;
import com.github.subtlelib.poi.api.style.Style;
import com.github.subtlelib.poi.api.totals.ColumnTotalsDataRange;
import com.github.subtlelib.poi.api.totals.Formula;

//TODO think of the case conditionalCell().text() - is text() supposed to move pointer in delegate?
public class RowContextNoImpl extends AbstractDelegatingRowContext {

    private final RowContext delegate;

    public RowContextNoImpl(SheetContext sheet) {
        super(sheet);
        this.delegate = this;
    }
    
    public RowContextNoImpl(SheetContext sheet, RowContext delegate) {
        super(sheet);
        this.delegate = delegate;
    }

    @Override
    public RowContext total(String text) {
        return delegate;
    }

	@Override
    public RowContext header(String text) {
        return delegate;
    }

    @Override
    public RowContext text(String text) {
        return delegate;
    }

    @Override
    public RowContext text(String text, Style style) {
        return delegate;
    }

    @Override
    public RowContext text(Optional<String> text) {
        return delegate;
    }

    @Override
    public RowContext text(Optional<String> text, Style style) {
        return delegate;
    }

    @Override
	public RowContext multilineText(Collection<String> lines) {
		return delegate;
	}

	@Override
	public RowContext multilineText(Collection<String> lines, Style style) {
		return delegate;
	}

	@Override
    public RowContext number(Number number, Style style) {
        return delegate;
    }

    @Override
    public RowContext number(Optional<? extends Number> number) {
        return delegate;
    }

    @Override
    public RowContext number(Optional<? extends Number> number, Style style) {
        return delegate;
    }

    @Override
    public RowContext number(Number number) {
        return delegate;
    }

	@Override
	public RowContext date(LocalDate date) {
		return delegate;
	}

	@Override
	public RowContext date(LocalDate date, Style style) {
		return delegate;
	}

    @Override
    public RowContext date(Optional<LocalDate> date) {
        return delegate;
    }

    @Override
    public RowContext date(Optional<LocalDate> date, Style style) {
        return delegate;
    }

    @Override
    public RowContext skipCell() {
        return delegate;
    }

    @Override
    public RowContext skipCells(int offset) {
        return delegate;
    }

    @Override
    public RowContext cellAt(int newIndex) {
        return delegate;
    }

    @Override
    public RowContext percentage(Number number) {
        return delegate;
    }

    @Override
    public RowContext percentage(Optional<? extends Number> number) {
        return delegate;
    }

    @Override
    public RowContext conditionalCell(boolean condition) {
        return delegate;
    }

	@Override
	public RowContext setColumnWidth(int width) {
		return delegate;
	}

    @Override
    public RowContext setRowHeight(int height) {
        return delegate;
    }

    @Override
	public RowContext mergeCells(int number) {
		return delegate;
	}
	
    @Override
    public Row getNativeRow() {
        throw new UnsupportedOperationException("RowContextNoImpl doesn't have underlying poi row");
    }

    @Override
    public RowContext setTotalsDataRange(ColumnTotalsDataRange data) {
        return delegate;
    }

    @Override
    public RowContext total(Formula formula) {
        return delegate;
    }

    @Override
    public RowContext total(Formula formula, Style style) {
        return delegate;
    }

    @Override
    public RowContext totals(Formula formula, int times) {
        return delegate;
    }

    @Override
    public RowContext totals(Formula formula, int times, Style style) {
        return delegate;
    }
}
