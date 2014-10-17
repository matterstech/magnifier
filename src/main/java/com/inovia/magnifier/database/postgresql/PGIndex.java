package com.inovia.magnifier.database.postgresql;

import com.inovia.magnifier.database.objects.Index;

public class PGIndex implements Index {
	private String schemaName;
	private String tableName;
	private String name;
	
	public PGIndex(String schemaName, String tableName, String name) {
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.name = name;
	}
	
	public String getEntityDescription() {
		return name + " ON TABLE " + name;
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
}
