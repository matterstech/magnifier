package com.inovia.magnifier.database.objects;

/**
 * The FunctionParameter interface deals with the expected behaviour
 * of function parameters in the database.
 * 
 * The FunctionParameter interface inherit the Entity interface
 */
public interface FunctionParameter extends Entity {
	public Function getFunction();
	public String getName();
	public String getMode();
}
