package com.inovia.magnifier.database.postgresql;

import java.util.Vector;

import com.inovia.magnifier.database.objects.ForeignKey;
import com.inovia.magnifier.database.objects.Table;

/**
 * The class PGTable deals with the PostgreSql
 * implementation of the Table interface
 */
public class PGTable implements Table {
	private String schemaName;
	private String name;
	private Vector<String> primaryKey;
	private Vector<ForeignKey> foreignKeys;
	
	/**
	 * @param schema The name of the schema containing the table
	 * @param name   The name of the table
	 */
	public PGTable(String schemaName, String name) {
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
