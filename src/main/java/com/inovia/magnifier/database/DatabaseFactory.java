package com.inovia.magnifier.database;

public class DatabaseFactory {
	public static Database getDatabase(String type, String host, String port, String database, String user, String password) {
		System.out.println(type);
		
		if(type != null && !type.isEmpty()) {
			
			// If PostgreSql
			if(type.equals("postgresql")) {
				return (Database) new PostgresqlDatabase(database, host, port, user, password);
			}
			
			throw new IllegalArgumentException("args[type]");
		}
		
		throw new IllegalArgumentException("no type");
	}
}
