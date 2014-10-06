package com.inovia.magnifier.databaseObjects;

import javax.swing.ActionMap;


/**
 * 
 * @author joeyrogues
 *
 */
public class Trigger extends DatabaseObject {
	private String actionTiming;
	private String tableName;
	private String eventManipulation;
	
	/**
	 * 
	 * @param name The name of the trigger
	 * @param actionTiming The moment when the trigger is performed (BEFORE, AFTER, ...)
	 * @param tableName The name of the table which pull the trigger
	 * @param eventManipulation The event that trigger the trigger (UPDATE, DELETE, ...)
	 */
	public Trigger(String name, String actionTiming, String tableName, String eventManipulation) {
		super(name);
		this.actionTiming = actionTiming;
		this.tableName = tableName;
		this.eventManipulation = eventManipulation;
	}
	
	/**
	 * 
	 * @return The action timing, the moment when the action is performed (BEFORE, AFTER, ...)
	 */
	public String getActionTime() {
		return actionTiming;
	}
	
	/**
	 * 
	 * @return The table name which pull the trigger
	 */
	public String getTableName() {
		return tableName;
	}

	public String toString() {
		return name + " [" + actionTiming + " " + eventManipulation + " ON " + tableName + "]";
	}
}
