package com.inovia.magnifier.database.postgresql;

import java.util.Vector;

import com.inovia.magnifier.database.objects.ForeignKey;
import com.inovia.magnifier.database.objects.Table;

public class PGTable implements Table {
	private String schemaName;
	private String name;
	private Vector<String> primaryKey;
	private Vector<ForeignKey> foreignKeys;
	
	public PGTable(String schema, String name) {
		this.schemaName = schema;
		this.name = name;
		primaryKey = new Vector<String>();
		foreignKeys = new Vector<ForeignKey>();
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
	
	public boolean addPrimaryKey(String primaryKeyName) {
		return primaryKey.add(primaryKeyName);
	}

	public Boolean addForeignKey(ForeignKey foreignKey) {
		return foreignKeys.add(foreignKey);
	}
	
	public Vector<String> getPrimaryKey() {
		return primaryKey;
	}
	
	public Boolean hasPrimaryKey() {
		return primaryKey.size() > 0;
	}

	public Vector<ForeignKey> getForeignKeys() {
		return foreignKeys;
	}
	
	public String toString() {
		return schemaName + "." + name;
	}
}
