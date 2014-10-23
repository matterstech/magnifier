package com.inovia.magnifier.database;

/**
 * The class PGSchema deals with the PostgreSql
 * implementation of the Schema interface
 */
public class Schema {
	private String name;

	/**
	 * @param name The name of the schema
	 */
	public Schema(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return "name: " + name;
	}

	public String getEntityDescription() {
		return "schema \"" + name + "\"";
	}
}
