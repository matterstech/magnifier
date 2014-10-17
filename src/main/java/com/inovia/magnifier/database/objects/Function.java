package com.inovia.magnifier.database.objects;

import java.util.Vector;

/**
 * The Function interface deals with the expected behaviour
 * of functions in the database.
 * 
 * The Function interface inherit the Entity interface
 */
public interface Function extends Entity {
	public String getSchemaName();
	public String getName();
	public Vector<FunctionParameter> getParameters();
	public void addParameter(FunctionParameter parameter);
	public String toString();
}
