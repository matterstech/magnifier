package com.inovia.magnifier.database.postgresql;

import com.inovia.magnifier.database.objects.Comment;

public class PGComment implements Comment {
	private String schemaName;
	private String entityName;
	private String content;
	
	public PGComment(String schemaName, String entityName, String content) {
		this.schemaName = schemaName;
		this.entityName = entityName;
		this.content = content;
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
}
