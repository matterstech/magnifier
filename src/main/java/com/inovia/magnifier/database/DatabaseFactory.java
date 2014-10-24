package com.inovia.magnifier.database;

/**
 * it instantiate an object according to the given database type.
 * this class applies the factory pattern.
 */
public class DatabaseFactory {
	public static Database getDatabase(String driverPath, String type, String host, String port, String database, String user, String password) {
		if(type != null && !type.isEmpty()) {
			
			// If PostgreSql
			if(type.equals("postgresql")) {
				return (Database) new PostgreSqlDatabase(driverPath, database, host, port, user, password);
			}
			
			throw new IllegalArgumentException("args[type]");
		}
		
		throw new IllegalArgumentException("no type");
	}
}
