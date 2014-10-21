package com.inovia.magnifier.database;

/**
 * The class PGTrigger deals with the PostgreSql
 * implementation of the Trigger interface
 */
public class Trigger {
	private String schemaName;
	private String tableName;
	private String name;
	private String action;
	private String timing;
	
	/**
	 * @param schemaName The name of the schema containing the trigger
	 * @param tableName  The name of the table firing the trigger
	 * @param name       The name of the trigger
	 * @param action     The action firing the trigger (UPDATE, INSERT, ...)
	 * @param timing     The moment the trigger is executed (BEFORE, AFTER, ...)
	 */
	public Trigger(String schemaName, String tableName, String name, String action, String timing) {
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.name = name;
		this.action = action;
		this.timing = timing;
	}
	
	public String getEntityDescription() {
		return name + "() ON TABLE " + schemaName + "." + tableName;
	}

	public String getName() {
		return name;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public String getTableName() {
		return tableName;
	}

	public String getAction() {
		return action;
	}

	public String getTiming() {
		return timing;
	}
	
	public String toString() {
		return name;
	}
}
