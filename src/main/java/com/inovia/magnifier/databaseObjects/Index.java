package com.inovia.magnifier.databaseObjects;

/**
 * 
 * @author joeyrogues
 *
 */
public class Index extends DatabaseObject {
	private String tableName;
	
	/**
	 * 
	 * @param name      The name of the index
	 * @param tableName The name of the indexed table
	 */
	public Index(String name, String tableName) {
		super(name);
		this.tableName = tableName;
	}
	
	/**
	 * 
	 * @return The name of the indexed table
	 */
	public String getTableName() {
		return tableName;
	}
	
	public String toString() {
		return name + " on " + tableName;
	}
}
