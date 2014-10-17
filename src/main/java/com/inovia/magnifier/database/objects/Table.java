package com.inovia.magnifier.database.objects;

import java.util.Vector;

/**
 * The Table interface deals with the expected behaviour
 * of tables in the database.
 * 
 * The Table interface inherit the Entity interface
 */
public interface Table extends Entity {
	public String getSchemaName();
	public String getName();
	public Vector<String> getPrimaryKey();
	public Vector<ForeignKey> getForeignKeys();
	public Boolean hasPrimaryKey();
}
