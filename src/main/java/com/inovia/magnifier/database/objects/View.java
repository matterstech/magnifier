package com.inovia.magnifier.database.objects;

/**
 * The View interface deals with the expected behaviour
 * of views in the database.
 * 
 * The View interface inherit the Entity interface
 */
public interface View extends Entity {
	/**
	 * @return the name of the schema containing the view
	 */
	public String getSchemaName();
	
	/**
	 * @return the name of the view
	 */
	public String getName();
}
