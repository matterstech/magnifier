package com.inovia.magnifier.database.objects;

/**
 * The Trigger interface deals with the expected behaviour
 * of triggers in the database.
 * 
 * The Trigger interface inherit the Entity interface
 */
public interface Trigger extends Entity {
	/**
	 * @return the name of the schema containing the trigger
	 */
	public String getSchemaName();
	
	/**
	 * @return the name of the table the trigger is fired on
	 */
	public String getTableName();
	
	/**
	 * @return the trigger name
	 */
	public String getName();
	
	/**
	 * @return the action on which the trigger is fired (UPDATE, INSERT, ...)
	 */
	public String getAction();
	
	/**
	 * @return the moment when the trigger is executed (BEFORE, AFTER, ...)
	 */
	public String getTiming();
}
