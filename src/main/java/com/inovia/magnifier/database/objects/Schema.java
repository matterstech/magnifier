package com.inovia.magnifier.database.objects;

import java.util.Vector;

public interface Schema extends Entity {
	public String getName();
	public Vector<Function> getFunctions();
	public String toString();
}