package org.subtlelib.poi.api.condition;

/**
 * Sets execution of the subsequent cell dependent on condition.
 * 
 * @author i.voshkulat
 *
 * @param <T> implementing builder type
 */
public interface CellCondition<T> {

	/**
	 * Make execution of the next cell conditional.
	 * Execution will take place only if condition evaluates to true.
	 * 
	 * @param condition execution condition
     * @return this
	 */
	public T conditionalCell(boolean condition);
	
}
