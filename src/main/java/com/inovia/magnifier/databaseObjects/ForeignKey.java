package com.inovia.magnifier.databaseObjects;

/**
 * 
 * @author joeyrogues
 *
 */
public class ForeignKey extends DatabaseObject {
	private String tableName;
	private String referencedTableName;
	private String referencedColumnName;
	private String schemaName;
	
	/**
	 * 
	 * @param schemaName The name of the schema which contains the foreign key table
	 * @param name The name of the foreign key
	 * @param tableName The name of the table that contains the foreign key
	 * @param referencedTableName The name off the referenced table
	 * @param referencedColumnName The name of the referenced key
	 */
	public ForeignKey(String schemaName, String name, String tableName, String referencedTableName, String referencedColumnName) {
		super(name);
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.referencedTableName = referencedTableName;
		this.referencedColumnName = referencedColumnName;
	}

	/**
	 * 
	 * @return The name of the table containing the foreign key
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * 
	 * @return The name of the referenced table
	 */
	public String getReferencedTableName() {
		return referencedTableName;
	}

	/**
	 * 
	 * @return The name of the referenced column
	 */
	public String getReferencedColumnName() {
		return referencedColumnName;
	}
	
	/**
	 * 
	 * @return The name of the schema which contains the foreign key table
	 */
	public String getSchemaName() {
		return schemaName;
	}

	public String toString() {
		return  tableName + "." + name + " REFERENCES " + referencedTableName + "." + referencedColumnName + "]";
	}
}
