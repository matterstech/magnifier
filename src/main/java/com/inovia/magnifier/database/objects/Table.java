package com.inovia.magnifier.database.objects;

import java.util.List;

/**
 * The Table interface deals with the expected behaviour
 * of tables in the database.
 * 
 * The Table interface inherit the Entity interface
 */
public interface Table extends Entity {
	/**
	 * @return the name of the schema containing the table
	 */
	public String getSchemaName();
	
	/**
	 * @return the table name
	 */
	public String getName();
	
	/**
	 * @return the names of the fields composing the primary key
	 */
	public List<String> getPrimaryKey();
	
	/**
	 * @return the list of the table foreign keys
	 */
	public List<ForeignKey> getForeignKeys();
	
	/**
	 * @return return whether the table has a primary key or not 
	 */
	public Boolean hasPrimaryKey();
}
