package com.inovia.magnifier.database.objects;

import java.util.List;

/**
 * The Schema interface deals with the expected behaviour
 * of schemas in the database.
 * 
 * The Schema interface inherit the Entity interface
 */
public interface Schema extends Entity {
	public String getName();
	public List<Function> getFunctions();
	public String toString();
}
