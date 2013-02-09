package org.subtlelib.poi.impl.configuration;

import org.subtlelib.poi.api.configuration.Configuration;

/**
 * Not intended to be used in real application however can.
 * Please consider implementing enum-based configuration and reuse it across the application.
 * 
 * @author i.voshkulat
 *
 */
public class DefaultConfiguration implements Configuration {

	private double columnWidthBaseValue = 256 * 1.1;
	
	@Override
	public double getColumnWidthBaseValue() {
		return columnWidthBaseValue;
	}

	public DefaultConfiguration setColumnWidthBaseValue(double value) {
		this.columnWidthBaseValue = value;
		return this;
	}

}
