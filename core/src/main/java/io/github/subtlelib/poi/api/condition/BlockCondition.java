package io.github.subtlelib.poi.api.condition;

/**
 * Defines boundaries of arbitrary-size conditional block.
 * 
 * @author i.voshkulat
 *
 * @param <T> implementing builder type
 */
public interface BlockCondition<T> {

	/**
	 * Demarcates start of a conditional block and defines execution condition.
	 * Block is executed only if condition evaluates to true.
	 * 
	 * @param condition execution condition
     * @return this
	 */
	T startConditionalBlock(boolean condition);
	
	/**
	 * Designates end of a conditional block.
     *
     * @return this
	 */
	T endConditionalBlock();
	
}
