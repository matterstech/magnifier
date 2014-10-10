package com.inovia.magnifier.database.postgresql;

import com.inovia.magnifier.database.objects.Table;

public class PGTable implements Table {
	private String schemaName;
	private String name;
	
	public PGTable(String schema, String name) {
		this.schemaName = schema;
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
