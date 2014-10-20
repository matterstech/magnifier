package com.inovia.magnifier.database.objects;

/**
 * The Schema interface deals with the expected behaviour
 * of schemas in the database.
 * 
 * The Schema interface inherit the Entity interface
 */
public interface Schema extends Entity {
	/**
	 * @return the name of the schema
	 */
	public String getName();
}
