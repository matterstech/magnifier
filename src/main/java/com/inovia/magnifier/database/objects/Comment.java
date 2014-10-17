package com.inovia.magnifier.database.objects;

/**
 * The Comment interface deals with the expected behaviour
 * of comments in the database.
 * 
 * The Comment interface inherit the Entity interface
 */
public interface Comment extends Entity {
	public String getSchemaName();
	public String getEntityType();
	public String getEntityName();
	public String toString();
}
