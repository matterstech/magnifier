package com.inovia.magnifier.database.objects;

/**
 * The Comment interface deals with the expected behaviour
 * of comments in the database.
 * 
 * The Comment interface inherit the Entity interface
 */
public interface Comment extends Entity {
	static final String TABLE_TYPE    = "table";
	static final String VIEW_TYPE     = "view";
	static final String TRIGGER_TYPE  = "trigger";
	static final String FUNCTION_TYPE = "function";
	
	/**
	 * @return the name of the schema containing the entity
	 */
	public String getSchemaName();
	
	/**
	 * @return the type of the commented entity (table, view, function, ...)
	 */
	public String getEntityType();
	
	/**
	 * @return the name of the comment
	 */
	public String getEntityName();
}
