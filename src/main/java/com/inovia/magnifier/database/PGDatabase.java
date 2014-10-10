package com.inovia.magnifier.database;

import java.sql.*;
import java.util.Vector;

import com.inovia.magnifier.database.objects.Comment;
import com.inovia.magnifier.database.objects.Function;
import com.inovia.magnifier.database.objects.Schema;
import com.inovia.magnifier.database.postgresql.*;

public class PGDatabase implements Database {
	private Connection connection;
	private String name;
	private String host;
	private String port;
	private String user;
	private String password;

	private Vector<Schema> schemas;
	private Vector<Function> functions;
	private Vector<Comment> comments;

	public PGDatabase(String databaseName, String host, String port, String user, String password) {
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
		loadFunctionComments();
	}

	private void loadSchemas() {
		if(schemas == null) {
			final String SQL = "SELECT schema_name FROM information_schema.schemata";

			Statement statement = null;
			ResultSet results = null;
			try {
				statement = connection.createStatement();
				results = statement.executeQuery(SQL);

				schemas = new Vector<Schema>();

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
			// Previous One
			/* final String SQL = "SELECT routines.routine_schema, routines.routine_name"
					+ " FROM information_schema.routines"
					+ " WHERE routine_schema NOT IN ('pg_catalog', 'information_schema')"
					+ " ORDER BY routines.routine_schema ASC, routines.routine_name ASC"; */

			final String SQL = "SELECT routine_schema, routine_name, p.parameter_name, p.parameter_mode"
					+ " FROM information_schema.routines r"
					+ " LEFT OUTER JOIN information_schema.parameters p"
					+ " ON r.specific_name   = p.specific_name"
					+ " AND r.specific_schema = r.specific_schema"
					+ " WHERE routine_schema"
					+ " NOT IN ('pg_catalog', 'information_schema')"
					+ " ORDER BY r.routine_schema ASC, r.routine_name ASC, p.parameter_name ASC";



			Statement statement = null;
			ResultSet results = null;
			try {
				statement = connection.createStatement();
				results = statement.executeQuery(SQL);

				functions = new Vector<Function>();
				final String SCHEMA_NAME_FIELD = "routine_schema";
				final String ROUTINE_NAME_FIELD = "routine_name";
				final String PARAMETER_NAME_FIELD = "parameter_name";
				final String PARAMETER_TYPE_FIELD = "parameter_mode";

				Boolean doLoop = results.next();
				while(doLoop) {
					PGFunction function = new PGFunction(results.getString(SCHEMA_NAME_FIELD), results.getString(ROUTINE_NAME_FIELD));
					
					Vector<PGFunctionParameter> parameters = new Vector<PGFunctionParameter>();
					
					PGFunctionParameter parameter = null;
					String parameterName = results.getString(PARAMETER_NAME_FIELD);
					String parameterMode = results.getString(PARAMETER_TYPE_FIELD);

					if(parameterName != null && parameterMode != null) {
						parameter = new PGFunctionParameter(function, parameterName, parameterMode);
						parameters.add(parameter);
						
						function.addParameter(parameter);
					}
					
					
					String schemaName = results.getString(SCHEMA_NAME_FIELD);
					String routineName = results.getString(ROUTINE_NAME_FIELD);
					
					doLoop = results.next();
					while(doLoop && results.getString(SCHEMA_NAME_FIELD).equals(schemaName) && results.getString(ROUTINE_NAME_FIELD).equals(routineName)) {
						schemaName = results.getString(SCHEMA_NAME_FIELD);
						routineName = results.getString(ROUTINE_NAME_FIELD);
						
						parameter = new PGFunctionParameter(function, results.getString(PARAMETER_NAME_FIELD), results.getString(PARAMETER_TYPE_FIELD));
						function.addParameter(parameter);
						doLoop = results.next();
					}
					
					functions.add(function);
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

	private void loadFunctionComments() {
		if(comments == null) {
			final String SQL = "SELECT nspname, proname, description"
					+ " FROM pg_description"
					+ " JOIN pg_proc"
					+ " ON pg_description.objoid = pg_proc.oid"
					+ " JOIN pg_namespace"
					+ " ON pg_proc.pronamespace = pg_namespace.oid"
					+ " WHERE nspname"
					+ " NOT IN ('pg_catalog', 'pg_catalog')";

			Statement statement = null;
			ResultSet results = null;
			try {
				statement = connection.createStatement();
				results = statement.executeQuery(SQL);

				comments = new Vector<Comment>();

				Boolean exitLoop = results.next();
				while(exitLoop) {
					Comment f = new PGComment(results.getString("nspname"), results.getString("proname"), results.getString("description"));

					comments.add(f);

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

	public Vector<Function> getFunctions() {
		return functions;
	}

	public Vector<Schema> getSchemas() {
		return schemas;
	}

	public Vector<Comment> getComments() {
		return comments;
	}

	public String getName() {
		return name;
	}
}
