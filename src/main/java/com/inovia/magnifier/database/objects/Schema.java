package com.inovia.magnifier.database.objects;

import java.util.List;

public interface Schema extends Entity {
	public String getName();
	public List<Function> getFunctions();
	public String toString();
}