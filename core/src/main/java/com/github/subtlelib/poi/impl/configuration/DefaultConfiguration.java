package com.github.subtlelib.poi.impl.configuration;

import com.github.subtlelib.poi.api.configuration.Configuration;

/**
 * Not intended to be used in real application however can.
 * Please consider implementing enum-based configuration and reuse it across the application.
 * 
 * @author i.voshkulat
 *
 */
public class DefaultConfiguration implements Configuration {
	private double columnWidthBaseValue = 256 * 1.1;
    private int rowHeightBaseValue = 20;

    @Override
	public double getColumnWidthBaseValue() {
		return columnWidthBaseValue;
	}

    public DefaultConfiguration setColumnWidthBaseValue(double value) {
        this.columnWidthBaseValue = value;
        return this;
    }

    @Override
    public double getRowHeightBaseValue() {
        return rowHeightBaseValue;
    }

    public DefaultConfiguration setRowHeightBaseValue(int rowHeightBaseValue) {
        this.rowHeightBaseValue = rowHeightBaseValue;
        return this;
    }
}
