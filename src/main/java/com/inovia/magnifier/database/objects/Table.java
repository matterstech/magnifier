package com.inovia.magnifier.database.objects;

import java.util.Vector;

public interface Table extends Entity {
	public String getSchemaName();
	public String getName();
	public Vector<String> getPrimaryKey();
	public Boolean hasPrimaryKey();
}
