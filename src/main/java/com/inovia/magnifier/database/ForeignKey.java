package com.inovia.magnifier.database;


/**
 * The class PGForeignKey deals with the PostgreSql
 * implementation of the ForeignKey interface
 */
public class ForeignKey {
	private Table table;
	private String columnName;
	private String foreignSchemaName;
	private String foreignTableName;
	private String foreignColumnName;
	
	/**
	 * @param table             The table containing the foreign key
	 * @param columnName        The name of the foreign key field
	 * @param foreignSchemaName The name of the foreign schema
	 * @param foreignTableName  The name of the foreign table
	 * @param foreignColumnName The name of the foreign column
	 */
	public ForeignKey(Table table, String columnName, String foreignSchemaName, String foreignTableName, String foreignColumnName) {
		this.table = table;
		this.columnName = columnName;
		this.foreignSchemaName = foreignSchemaName;
		this.foreignTableName = foreignTableName;
		this.foreignColumnName = foreignColumnName;
	}
	
	public String getSchemaName() {
		return table.getSchemaName();
	}
	
	public Table getTable() {
		return table;
	}
	
	public String getColumnName() {
		return columnName;
	}
	
	public String getForeignSchemaName() {
		return foreignSchemaName;
	}
	
	public String getForeignTableName() {
		return foreignTableName;
	}
	
	public String getForeignColumnName() {
		return foreignColumnName;
	}
	
	public String toString() {
		return table + "." + columnName + " -> " + foreignSchemaName + "." + foreignTableName + "." + foreignColumnName;
	}

	public String getEntityDescription() {
		return table + "." + columnName;
	}
}
