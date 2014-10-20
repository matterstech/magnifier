package com.inovia.magnifier.database.objects;

/**
 * The FunctionParameter interface deals with the expected behaviour
 * of function parameters in the database.
 * 
 * The FunctionParameter interface inherit the Entity interface
 */
public interface FunctionParameter extends Entity {
	/**
	 * @return the function in which this function parameter is passed
	 */
	public Function getFunction();
	
	/**
	 * @return the name of the function parameter
	 */
	public String getName();
	
	/**
	 * @return the mode of the function parameter (IN, OUT, ...)
	 */
	public String getMode();
}
