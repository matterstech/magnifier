package com.inovia.magnifier.database.objects;

import java.util.List;

/**
 * The Function interface deals with the expected behaviour
 * of functions in the database.
 * 
 * The Function interface inherit the Entity interface
 */
public interface Function extends Entity {
	/**
	 * @return the name of the schema containing the function
	 */
	public String getSchemaName();
	
	/**
	 * @return the name of the function
	 */
	public String getName();
	
	/**
	 * @return the parameters the function takes
	 */
	public List<FunctionParameter> getParameters();
	
	/**
	 * Add a FunctionParameter to the Function
	 */
	public void addParameter(FunctionParameter parameter);
}
