package com.inovia.magnifier.database.objects;

/**
 * The Index interface deals with the expected behaviour
 * of indexes in the database.
 * 
 * The Index interface inherit the Entity interface
 */
public interface Index extends Entity {
	public String getSchemaName();
	public String getTableName();
	public String getName();
}
