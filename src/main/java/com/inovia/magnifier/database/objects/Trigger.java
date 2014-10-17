package com.inovia.magnifier.database.objects;

/**
 * The Trigger interface deals with the expected behaviour
 * of triggers in the database.
 * 
 * The Trigger interface inherit the Entity interface
 */
public interface Trigger extends Entity {
	public String getSchemaName();
	public String getTableName();
	public String getName();
	public String getAction();
	public String getTiming();
}
