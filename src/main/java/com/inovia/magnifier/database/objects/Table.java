package com.inovia.magnifier.database.objects;

import java.util.List;

public interface Table extends Entity {
	public String getSchemaName();
	public String getName();
	public List<String> getPrimaryKey();
	public List<ForeignKey> getForeignKeys();
	public Boolean hasPrimaryKey();
}
