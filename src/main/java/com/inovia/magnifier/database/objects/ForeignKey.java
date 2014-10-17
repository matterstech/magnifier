package com.inovia.magnifier.database.objects;

/**
 * The ForeignKey interface deals with the expected behaviour
 * of foreign keys in the database.
 * 
 * The ForeignKey interface inherit the Entity interface
 */
public interface ForeignKey extends Entity {
	public String getSchemaName();
	public Table getTable();
	public String getColumnName();
	
	public String getForeignSchemaName();
	public String getForeignTableName();
	public String getForeignColumnName();
	
	public String toString();
}
