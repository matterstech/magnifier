package com.inovia.magnifier.database.objects;

/**
 * The Index interface deals with the expected behaviour
 * of indexes in the database.
 * 
 * The Index interface inherit the Entity interface
 */
public interface Index extends Entity {
	/**
	 * @return the name of the schema containing the index
	 */
	public String getSchemaName();
	
	/**
	 * @return the name of the indexed table
	 */
	public String getTableName();
	
	/**
	 * @return the name of the index
	 */
	public String getName();
}
