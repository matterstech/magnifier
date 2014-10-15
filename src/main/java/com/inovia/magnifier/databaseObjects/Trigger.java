package com.inovia.magnifier.databaseObjects;

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

		if(name == null || name.isEmpty()) {
			throw new IllegalArgumentException("a trigger should have a name");
		}

		if(actionTiming == null || actionTiming.isEmpty()) {
			throw new IllegalArgumentException("a trigger should have an action timing");
		}

		if(tableName == null || tableName.isEmpty()) {
			throw new IllegalArgumentException("a trigger should have a table name");
		}

		if(eventManipulation == null || eventManipulation.isEmpty()) {
			throw new IllegalArgumentException("a trigger should have an event manipulation");
		}

		this.actionTiming = actionTiming;
		this.tableName = tableName;
		this.eventManipulation = eventManipulation;
	}

	/**
	 * 
	 * @return The action timing, the moment when the action is performed (BEFORE, AFTER, ...)
	 */
	public String getActionTiming() {
		return actionTiming;
	}

	/**
	 * 
	 * @return The table name which pull the trigger
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * 
	 * @return The event that trigger the trigger (UPDATE, DELETE, ...)
	 */
	public String getEventManipulation() {
		return eventManipulation;
	}

	public String toString() {
		return name + " [" + actionTiming + " " + eventManipulation + " ON " + tableName + "]";
	}
}
