package com.inovia.magnifier.database;

import java.util.*;

/**
 * The class PGTable deals with the PostgreSql
 * implementation of the Table interface
 */
public class Table {
	private String schemaName;
	private String name;
	private List<String> primaryKey;
	private List<ForeignKey> foreignKeys;
	
	/**
	 * @param schema The name of the schema containing the table
	 * @param name   The name of the table
	 */
	public Table(String schemaName, String name) {
		this.schemaName = schemaName;
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
	
	public List<String> getPrimaryKey() {
		return primaryKey;
	}
	
	public Boolean hasPrimaryKey() {
		return primaryKey.size() > 0;
	}

	public List<ForeignKey> getForeignKeys() {
		return foreignKeys;
	}
	
	public String toString() {
		return schemaName + "." + name;
	}
}
