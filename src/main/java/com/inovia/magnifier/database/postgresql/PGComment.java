package com.inovia.magnifier.database.postgresql;

import com.inovia.magnifier.database.objects.Comment;

/**
 * The class PGComment deals with the PostgreSql
 * implementation of the Comment interface
 */
public class PGComment implements Comment {
	private String schemaName;
	private String entityName;
	private String entityType;
	private String content;
	
	/**
	 * @param schemaName The name of the schema containing the commented entity
	 * @param entityName The name of the commented entity
	 * @param content    The text of the comment
	 * @param entityType The type of the commented entity ({@link Comment}})
	 */
	public PGComment(String schemaName, String entityName, String content, String entityType) {
		this.schemaName = schemaName;
		this.entityName = entityName;
		this.content    = content;
		this.entityType = entityType;
	}
	
	public String getSchemaName() {
		return schemaName;
	}
	
	public String getEntityName() {
		return entityName;
	}
	
	public String toString() {
		return "schema: " + schemaName + ", entity: " + entityName + ", content: \"" + content + "\"";
	}

	public String getEntityDescription() {
		return "COMMENT ON " + entityName;
	}

	public String getEntityType() {
		return entityType;
	}
}
