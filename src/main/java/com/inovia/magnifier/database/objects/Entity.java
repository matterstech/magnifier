package com.inovia.magnifier.database.objects;

/**
 * The Entity interface deals with the global expected behaviour
 * of any entity in the database.
 */
public interface Entity {
	/**
	 * @return a human readable string describing the entity
	 */
	public String getEntityDescription();
}
