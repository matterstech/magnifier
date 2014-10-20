package com.inovia.magnifier.database.objects;

/**
 * The ForeignKey interface deals with the expected behaviour
 * of foreign keys in the database.
 * 
 * The ForeignKey interface inherit the Entity interface
 */
public interface ForeignKey extends Entity {
	/**
	 * @return the name of the schema containing the table which contains the foreign key
	 */
	public String getSchemaName();
	
	/**
	 * @return the table containing the foreign key
	 */
	public Table getTable();
	
	/**
	 * @return the name of the foreign key column
	 */
	public String getColumnName();
	
	/**
	 * @return the name of the schema containing table referenced by the foreign key
	 */
	public String getForeignSchemaName();
	
	/**
	 * @return the name of the referenced table
	 */
	public String getForeignTableName();
	
	/**
	 * @return column name of the referenced column
	 */
	public String getForeignColumnName();
}
