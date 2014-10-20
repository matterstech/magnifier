package com.inovia.magnifier.database.postgresql;

import com.inovia.magnifier.database.objects.View;

/**
 * The class PGView deals with the PostgreSql
 * implementation of the View interface
 */
public class PGView implements View {
	public String schemaName;
	public String name;
	
	/**
	 * @param schemaName The name of the schema containing the view
	 * @param name       The name of the view
	 */
	public PGView(String schemaName, String name) {
		this.schemaName = schemaName;
		this.name = name;
	}
	
	public String getEntityDescription() {
		return schemaName + "." + name;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public String getName() {
		return name;
	}

}
