package com.inovia.magnifier;

import java.sql.*;
import java.util.ArrayList;

import databaseObjects.*;

public class Database {
	private Configuration configuration;
	private Connection connection = null;

	private ArrayList<Table> tables = null;
	private ArrayList<Index> indexes = null;

	public Database(Configuration configuration) {
		this.configuration = configuration;

		try {
			// Register JDBC driver
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private Connection getConnection() {
		if(connection == null) {
			try {
				System.out.println("Connecting to database...");
				connection = DriverManager.getConnection(configuration.getConnectionURL(), configuration.getUser(), configuration.getPassword());
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		return connection;
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

	public ArrayList<Index> getIndexes() {
		if(indexes == null) {
			final String SQL = "SELECT tablename, indexname FROM pg_indexes WHERE schemaname = '" + configuration.getSchema() + "';";

			Statement statement = null;
			ResultSet results = null;
			try {
				statement = getConnection().createStatement();
				results = statement.executeQuery(SQL);

				indexes = new ArrayList<Index>();
				while(results.next()) {
					indexes.add(new Index(results.getString("indexname"), results.getString("tablename")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					results.close();
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		}

		return indexes;
	}

	public ArrayList<Table> getTables() {
		if(tables == null) {
			final String SQL = "SELECT table_name, column_name FROM information_schema.tables NATURAL JOIN information_schema.columns WHERE table_schema = '" + configuration.getSchema() + "' ORDER BY table_name ASC;";

			Statement statement = null;
			ResultSet results = null;
			try {
				statement = getConnection().createStatement();
				results = statement.executeQuery(SQL);

				tables = new ArrayList<Table>();
				
				final String TABLE_NAME_FIELD = "table_name";
				final String COLUMN_NAME_FIELD = "column_name";
				
				Table t = null;
				Boolean exitLoop = results.next();
				while(exitLoop) {
					t = new Table(results.getString(TABLE_NAME_FIELD));
					t.addColumn(results.getString(COLUMN_NAME_FIELD));
					
					exitLoop = results.next();
					while(exitLoop && results.getString(TABLE_NAME_FIELD).equals(t.getName())) {
						t.addColumn(results.getString(COLUMN_NAME_FIELD));
						exitLoop = results.next();
					}
					
					tables.add(t);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					results.close();
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		}

		return tables;
	}
}
