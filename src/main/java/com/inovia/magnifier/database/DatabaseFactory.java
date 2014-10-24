package com.inovia.magnifier.database;

/**
 * it instantiate an object according to the given database type.
 * this class applies the factory pattern.
 */
public class DatabaseFactory {
	public static Database getDatabase(String database, String dbms) {
		if(dbms != null && !dbms.isEmpty()) {
			
			// If PostgreSql
			if(dbms.equals("postgresql")) {
				return (Database) new PostgreSqlDatabase(database);
			}
			
			throw new IllegalArgumentException("args[type]");
		}
		
		throw new IllegalArgumentException("no dbms");
	}
}
