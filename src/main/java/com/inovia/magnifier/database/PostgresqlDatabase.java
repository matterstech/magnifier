package com.inovia.magnifier.database;

import java.sql.*;
import java.util.Vector;

import com.inovia.magnifier.database.postgresql.*;

public class PostgresqlDatabase implements Database {
	private Connection connection;
	private String name;
	private String host;
	private String port;
	private String user;
	private String password;
	
	private Vector<PGSchema> schemas;
	private Vector<PGFunction> functions;

	public PostgresqlDatabase(String databaseName, String host, String port, String user, String password) {
		this.name = databaseName;
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
		
		try {
			// Register JDBC driver
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void connect() {
		if(connection == null) {
			try {
				connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + name, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public void load() {
		loadSchemas();
		loadFunctions();
	}
	
	private void loadSchemas() {
		if(schemas == null) {
			final String SQL = "SELECT schema_name FROM information_schema.schemata";

			Statement statement = null;
			ResultSet results = null;
			try {
				statement = connection.createStatement();
				results = statement.executeQuery(SQL);

				schemas = new Vector<PGSchema>();

				Boolean exitLoop = results.next();
				while(exitLoop) {
					schemas.add(new PGSchema(results.getString("schema_name")));

					exitLoop = results.next();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(results != null) {
						results.close();
					}
					if(statement != null) {
						statement.close();						
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		}
	}
	
	private void loadFunctions() {
		if(functions == null) {
			final String SQL = "SELECT routines.routine_name, parameters.data_type, parameters.ordinal_position"
					+ " FROM information_schema.routines"
					+ " JOIN information_schema.parameters"
					+ " ON routines.specific_name = parameters.specific_name"
					+ " WHERE routines.specific_schema"
					+ " NOT IN ('pg_catalog', 'information_schema')"
					+ " ORDER BY routines.routine_name, parameters.ordinal_position;";

			Statement statement = null;
			ResultSet results = null;
			try {
				statement = connection.createStatement();
				results = statement.executeQuery(SQL);

				functions = new Vector<PGFunction>();

				Boolean exitLoop = results.next();
				while(exitLoop) {
					functions.add(new PGSchema(results.getString("schema_name")));

					exitLoop = results.next();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(results != null) {
						results.close();
					}
					if(statement != null) {
						statement.close();						
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		}
	}
	
	public void disconnect() {
		try {
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public Vector<PGSchema> getSchemas() {
		return schemas;
	}

	public Vector<? extends Function> getFunctions() {
		return functions;
	}
}
