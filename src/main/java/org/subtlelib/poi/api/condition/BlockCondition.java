package org.subtlelib.poi.api.condition;

public interface BlockCondition<T> {

	public T startConditionalBlock(boolean condition);
	public T endConditionalBlock();
	
}
