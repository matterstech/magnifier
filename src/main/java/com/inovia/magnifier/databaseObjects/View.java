package com.inovia.magnifier.databaseObjects;

/**
 * 
 * @author joeyrogues
 *
 */
public class View extends DatabaseObject {
	private String schema;
	
	/**
	 * 
	 * @param The name of the view
	 */
	public View(String schema, String name) {
		super(name);
		
		if(name == null || name.isEmpty()) {
			throw new IllegalArgumentException("A view needs a name");
		}
		
		if(schema == null || schema.isEmpty()) {
			throw new IllegalArgumentException("A view needs a schema");
		}
		
		this.schema = schema;
	}
	
	public String getSchema() {
		return schema;
	}
	
	public String toString() {
		return schema + "." + name;
	}
}
