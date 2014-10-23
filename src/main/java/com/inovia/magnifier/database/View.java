package com.inovia.magnifier.database;

/**
 * it represents a database view
 */
public class View {
	private String schemaName;
	private String name;
	
	/**
	 * @param schemaName The name of the schema containing the view
	 * @param name       The name of the view
	 */
	public View(String schemaName, String name) {
		this.schemaName = schemaName;
		this.name = name;
	}
	
	public String getEntityDescription() {
		return "View \"" + name + "\" in schema \"" + schemaName + "\"";
	}

	public String getSchemaName() {
		return schemaName;
	}

	public String getName() {
		return name;
	}

}
