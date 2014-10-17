package com.inovia.magnifier.database.postgresql;

import com.inovia.magnifier.database.objects.*;

/**
 * The class PGTrigger deals with the PostgreSql
 * implementation of the Trigger interface
 */
public class PGTrigger implements Trigger {
	private String schemaName;
	private String tableName;
	private String name;
	private String action;
	private String timing;
	
	public PGTrigger(String schemaName, String tableName, String name, String action, String timing) {
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
