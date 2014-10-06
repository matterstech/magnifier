package com.inovia.magnifier;

import java.sql.*;
import java.util.ArrayList;

import com.inovia.magnifier.databaseObjects.*;

public class Database {
	private Configuration configuration;
	private Connection connection = null;

	private ArrayList<Table> tables = null;
	private ArrayList<Index> indexes = null;
	private ArrayList<Function> functions = null;

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
				
				Boolean exitLoop = results.next();
				while(exitLoop) {
					String tableName = results.getString(TABLE_NAME_FIELD);
					ArrayList<String> columns = new ArrayList<String>();
					
					columns.add(results.getString(COLUMN_NAME_FIELD));
					
					exitLoop = results.next();
					while(exitLoop && results.getString(TABLE_NAME_FIELD).equals(tableName)) {
						columns.add(results.getString(COLUMN_NAME_FIELD));
						exitLoop = results.next();
					}
					
					tables.add(new Table(tableName, columns));
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
	
	/**
	 * 
	 * @return The list of functions in the database
	 */
	public ArrayList<Function> getFunctions() {
		if(functions == null) {
			final String SQL = "SELECT routines.routine_name, parameters.data_type, parameters.parameter_mode, parameters.parameter_name, parameters.data_type FROM information_schema.routines JOIN information_schema.parameters ON routines.specific_name = parameters.specific_name WHERE routines.specific_schema='public' ORDER BY routines.routine_name ASC, parameters.ordinal_position ASC;";

			Statement statement = null;
			ResultSet results = null;
			try {
				statement = getConnection().createStatement();
				results = statement.executeQuery(SQL);

				functions = new ArrayList<Function>();
				
				final String FUNCTION_NAME_FIELD = "routine_name";
				final String PARAMETER_NAME_FIELD = "parameter_name";
				final String PARAMETER_TYPE_FIELD = "data_type";
				final String PARAMETER_IN_OUT_FIELD = "parameter_mode";
				
				Boolean exitLoop = results.next();
				while(exitLoop) {
					String functionName = results.getString(FUNCTION_NAME_FIELD);
					ArrayList<FunctionParameter> parameters = new ArrayList<FunctionParameter>();
					
					Boolean isIn = results.getString(PARAMETER_IN_OUT_FIELD).equals("IN");
					parameters.add(new FunctionParameter(results.getString(PARAMETER_NAME_FIELD), results.getString(PARAMETER_TYPE_FIELD), isIn));
					
					exitLoop = results.next();
					while(exitLoop && results.getString(FUNCTION_NAME_FIELD).equals(functionName)) {
						isIn = results.getString(PARAMETER_IN_OUT_FIELD).equals("IN");
						parameters.add(new FunctionParameter(results.getString(PARAMETER_NAME_FIELD), results.getString(PARAMETER_TYPE_FIELD), isIn));
						exitLoop = results.next();
					}
					
					functions.add(new Function(functionName, parameters));
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
		
		return functions;
	}
}
