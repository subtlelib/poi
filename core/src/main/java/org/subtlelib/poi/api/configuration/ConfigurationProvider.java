package org.subtlelib.poi.api.configuration;

/**
 * Configuration provider.
 * 
 * @author i.voshkulat
 *
 */
public interface ConfigurationProvider {

	/**
	 * Get workbook wide configuration.
	 * 
	 * @return configuration
	 */
	public Configuration getConfiguration();

}
