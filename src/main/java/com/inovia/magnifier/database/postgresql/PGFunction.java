package com.inovia.magnifier.database.postgresql;

import com.inovia.magnifier.database.objects.*;


public class PGFunction implements Function {
	private String name;
	private String schemaName;

	public PGFunction(String schemaName, String name) {
		this.schemaName = schemaName;
		this.name = name;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return "schema: "+ schemaName + ", name: "+ name;
	}
}
