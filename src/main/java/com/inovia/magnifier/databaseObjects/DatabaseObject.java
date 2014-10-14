package com.inovia.magnifier.databaseObjects;

/**
 * A DatabaseObject is a generic class which factorize the attributes and behaviour of
 * tables, indexes and all over entities in the databaseObject package.
 * 
 * @author joeyrogues
 *
 */
public abstract class DatabaseObject {
	protected String name;
	
	public DatabaseObject(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	/**
	 * 
	 * @return The name of the DatabaseObject
	 */
	public String getName() {
		return name;
	}
}
