package com.inovia.magnifier.database;

import java.net.*;
import java.sql.*;
import java.util.*;


public class PostgreSqlDatabase implements Database {
	private Connection connection;
	private String name;
	private String host;
	private String port;
	private String user;
	private String password;

	private List<Schema>   schemas;
	private List<Index>    indexes;
	
	private List<Table>    tables;
	private List<Comment>  tableComments;
	
	private List<Function> functions;
	private List<Comment>  functionComments;
	
	private List<Trigger>  triggers;
	private List<Comment>  triggerComments;
	
	private List<View>     views;
	private List<Comment>  viewComments;

	/**
	 * @param driverFile   The JDBC driver file
	 * @param databaseName The name of the database to analyze
	 * @param host         The host on which the database is running (default:  127.0.0.1)
	 * @param port         The port on which the database is listening (default: default for DBMS)
	 * @param user
	 * @param password
	 */
	public PostgreSqlDatabase(String driverFile, String databaseName, String host, String port, String user, String password) {
		this.name = databaseName;
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;

		try {
			URL url = new URL("jar:file:" + driverFile + "!/");
			
			String classname = "org.postgresql.Driver";
			URLClassLoader urlcl = new URLClassLoader(new URL[] { url });
			
			Driver d = (Driver) Class.forName(classname, true, urlcl).newInstance();
			DriverManager.registerDriver((Driver) new DriverShim(d));
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

	/**
	 * Establish the connection to the database specified in constructor.
	 */
	public void connect() {
		if(connection == null) {
			try {
				connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + name, user, password);
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				System.exit(1);
			}
		}
	}

	/**
	 * Retrieve/load all the entities from the database
	 */
	public void load() {
		loadSchemas();

		loadFunctions();
		loadFunctionComments();

		loadTables();
		loadTableComments();
		
		loadIndexes();
		
		loadTriggers();
		loadTriggerComments();

		loadViews();
		loadViewComments();
	}

	/**
	 * Retrieve/load the schemas from the database
	 */
	private void loadSchemas() {
		if(schemas == null) {
			String SQL = "SELECT schema_name FROM information_schema.schemata";

			Statement statement = null;
			ResultSet results = null;
			try {
				statement = connection.createStatement();
				results = statement.executeQuery(SQL);

				schemas = new Vector<Schema>();

				Boolean exitLoop = results.next();
				while(exitLoop) {
					schemas.add(new Schema(results.getString("schema_name")));

					exitLoop = results.next();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					if(results != null) {
						results.close();
					}
					if(statement != null) {
						statement.close();						
					}
				} catch (SQLException e) {
					System.err.println(e.getMessage());
					System.exit(1);
				}
			}
		}
	}
	
	/**
	 * Retrieve/load the triggers from the database
	 */
	private void loadTriggers() {
		if(triggers == null) {
			String SQL = "select"
					+ " trigger_schema,"
					+ " trigger_name,"
					+ " event_manipulation,"
					+ " event_object_schema AS table_schema,"
					+ " event_object_table  AS table_name,"
					+ " event_manipulation as action,"
					+ " action_timing as timing"
					+ " FROM information_schema.triggers";

			Statement statement = null;
			ResultSet results = null;
			try {
				statement = connection.createStatement();
				results = statement.executeQuery(SQL);

				triggers = new Vector<Trigger>();

				Boolean exitLoop = results.next();
				while(exitLoop) {
					String schemaName  = results.getString("trigger_schema");
					String tableName   = results.getString("table_name");
					String triggerName = results.getString("trigger_name");
					String action      = results.getString("action");
					String timing      = results.getString("timing");
					
					triggers.add(new Trigger(schemaName, tableName, triggerName, action, timing));

					exitLoop = results.next();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					if(results != null) {
						results.close();
					}
					if(statement != null) {
						statement.close();						
					}
				} catch (SQLException e) {
					System.err.println(e.getMessage());
					System.exit(1);
				}
			}
		}
	}

	/**
	 * Retrieve/load the functions from the database
	 */
	private void loadFunctions() {
		if(functions == null) {
			String SQL = "SELECT routine_schema, routine_name, p.parameter_name, p.parameter_mode"
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
				String SCHEMA_NAME_FIELD = "routine_schema";
				String ROUTINE_NAME_FIELD = "routine_name";
				String PARAMETER_NAME_FIELD = "parameter_name";
				String PARAMETER_TYPE_FIELD = "parameter_mode";

				Boolean doLoop = results.next();
				while(doLoop) {
					Function function = new Function(results.getString(SCHEMA_NAME_FIELD), results.getString(ROUTINE_NAME_FIELD));

					List<FunctionParameter> parameters = new Vector<FunctionParameter>();

					FunctionParameter parameter = null;
					String parameterName = results.getString(PARAMETER_NAME_FIELD);
					String parameterMode = results.getString(PARAMETER_TYPE_FIELD);

					if(parameterName != null && parameterMode != null) {
						parameter = new FunctionParameter(function, parameterName, parameterMode);
						parameters.add(parameter);

						function.addParameter(parameter);
					}

					String schemaName = results.getString(SCHEMA_NAME_FIELD);
					String routineName = results.getString(ROUTINE_NAME_FIELD);

					doLoop = results.next();
					while(doLoop && results.getString(SCHEMA_NAME_FIELD).equals(schemaName) && results.getString(ROUTINE_NAME_FIELD).equals(routineName)) {
						schemaName = results.getString(SCHEMA_NAME_FIELD);
						routineName = results.getString(ROUTINE_NAME_FIELD);

						parameter = new FunctionParameter(function, results.getString(PARAMETER_NAME_FIELD), results.getString(PARAMETER_TYPE_FIELD));
						function.addParameter(parameter);
						doLoop = results.next();
					}

					functions.add(function);
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					if(results != null) {
						results.close();
					}
					if(statement != null) {
						statement.close();						
					}
				} catch (SQLException e) {
					System.err.println(e.getMessage());
					System.exit(1);
				}
			}
		}
	}
	
	/**
	 * Retrieve/load the views from the database
	 */
	private void loadViews() {
		if(views == null) {
			String SQL = "SELECT table_schema, table_name"
					+ " FROM information_schema.views"
					+ " WHERE table_schema"
					+ " NOT IN ('pg_catalog', 'information_schema')";

			Statement statement = null;
			ResultSet results = null;
			try {
				statement = connection.createStatement();
				results = statement.executeQuery(SQL);

				views = new Vector<View>();
				String SCHEMA_NAME_FIELD = "table_schema";
				String ROUTINE_NAME_FIELD = "table_name";

				Boolean doLoop = results.next();
				while(doLoop) {
					views.add(new View(results.getString(SCHEMA_NAME_FIELD), results.getString(ROUTINE_NAME_FIELD)));
					
					doLoop = results.next();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					if(results != null) {
						results.close();
					}
					if(statement != null) {
						statement.close();						
					}
				} catch (SQLException e) {
					System.err.println(e.getMessage());
					System.exit(1);
				}
			}
		}
	}

	/**
	 * Retrieve/load the function comments from the database
	 */
	private void loadFunctionComments() {
		if(functionComments == null) {
			String SQL = "SELECT nspname, proname, description"
					+ " FROM pg_description"
					+ " JOIN pg_proc"
					+ " ON pg_description.objoid = pg_proc.oid"
					+ " JOIN pg_namespace"
					+ " ON pg_proc.pronamespace = pg_namespace.oid"
					+ " WHERE nspname"
					+ " NOT IN ('pg_catalog', 'information_schema')";

			Statement statement = null;
			ResultSet results = null;
			try {
				statement = connection.createStatement();
				results = statement.executeQuery(SQL);

				functionComments = new Vector<Comment>();

				Boolean exitLoop = results.next();
				while(exitLoop) {
					Comment f = new Comment(results.getString("nspname"), results.getString("proname"), results.getString("description"), Comment.FUNCTION_TYPE);

					functionComments.add(f);

					exitLoop = results.next();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					if(results != null) {
						results.close();
					}
					if(statement != null) {
						statement.close();						
					}
				} catch (SQLException e) {
					System.err.println(e.getMessage());
					System.exit(1);
				}
			}
		}
	}
	
	/**
	 * Retrieve/load the view comments from the database
	 */
	private void loadViewComments() {
			if(viewComments == null) {
				String SQL = "SELECT nspname, relname, description"
						+ " FROM pg_class c"
						+ " JOIN pg_namespace n ON n.oid = c.relnamespace"
						+ " JOIN pg_description d ON relfilenode = d.objoid"
						+ " WHERE relkind = 'v'"
						+ " AND n.nspname"
						+ " NOT IN ('pg_catalog', 'information_schema')";

				Statement statement = null;
				ResultSet results = null;
				try {
					statement = connection.createStatement();
					results = statement.executeQuery(SQL);

					viewComments = new Vector<Comment>();

					Boolean exitLoop = results.next();
					while(exitLoop) {
						Comment f = new Comment(results.getString("nspname"), results.getString("relname"), results.getString("description"), Comment.VIEW_TYPE);

						viewComments.add(f);

						exitLoop = results.next();
					}
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				} finally {
					try {
						if(results != null) {
							results.close();
						}
						if(statement != null) {
							statement.close();						
						}
					} catch (SQLException e) {
						System.err.println(e.getMessage());
						System.exit(1);
					}
				}
			}
	}

	/**
	 * Retrieve/load the table comments from the database
	 */
	private void loadTableComments() {
		if(tableComments == null) {
			String SQL = "SELECT nspname, relname, description"
					+ " FROM pg_description"
					+ " JOIN pg_class"
					+ " ON pg_description.objoid = pg_class.oid"
					+ " JOIN pg_namespace"
					+ " ON pg_class.relnamespace = pg_namespace.oid"
					+ " WHERE nspname"
					+ " NOT IN ('pg_catalog', 'pg_catalog')";

			Statement statement = null;
			ResultSet results = null;
			try {
				statement = connection.createStatement();
				results = statement.executeQuery(SQL);

				tableComments = new Vector<Comment>();

				Boolean exitLoop = results.next();
				while(exitLoop) {
					Comment f = new Comment(results.getString("nspname"), results.getString("relname"), results.getString("description"), Comment.TABLE_TYPE);

					tableComments.add(f);

					exitLoop = results.next();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					if(results != null) {
						results.close();
					}
					if(statement != null) {
						statement.close();						
					}
				} catch (SQLException e) {
					System.err.println(e.getMessage());
					System.exit(1);
				}
			}
		}
	}

	/**
	 * Retrieve/load the tables from the database
	 */
	private void loadTables() {
		if(tables == null) {
			String SQL = "select distinct"
					+ "   tc.table_schema    as local_schema,"
					+ "   tc.table_name      as local_table,"
					+ "   kcu.column_name    as local_column,"
					+ "   tc.constraint_type as key_type,"
					+ "   ccu.table_schema   as foreign_schema,"
					+ "   ccu.table_name     as foreign_table,"
					+ "   ccu.column_name    as foreign_column"
					+ " from information_schema.key_column_usage kcu"
					+ " join information_schema.table_constraints tc"
					+ "      on kcu.constraint_schema = tc.constraint_schema"
					+ "     and kcu.constraint_name = tc.constraint_name"
					+ " join information_schema.constraint_column_usage ccu"
					+ "      on tc.constraint_schema = ccu.constraint_schema"
					+ "     and tc.constraint_name = ccu.constraint_name"
					+ " where constraint_type in ('FOREIGN KEY', 'PRIMARY KEY')"
					+ " order by"
					+ "   local_schema asc,"
					+ "   local_table  asc,"
					+ "   local_column asc,"
					+ "   key_type asc,"
					+ "   foreign_schema asc,"
					+ "   foreign_table asc";

			Statement statement = null;
			ResultSet results = null;
			try {
				statement = connection.createStatement();
				results = statement.executeQuery(SQL);
				tables = new Vector<Table>();
				String LOCAL_SCHEMA_FIELD   = "local_schema";
				String LOCAL_TABLE_FIELD    = "local_table";
				String LOCAL_COLUMN_FIELD   = "local_column";
				String FOREIGN_SCHEMA_FIELD = "foreign_schema";
				String FOREIGN_TABLE_FIELD  = "foreign_table";
				String FOREIGN_COLUMN_FIELD = "foreign_column";
				String KEY_TYPE_FIELD       = "key_type";

				Boolean doLoop = results.next();
				// Loop on tables
				while(doLoop) {
					String localSchemaName  = results.getString(LOCAL_SCHEMA_FIELD);
					String localTableName   = results.getString(LOCAL_TABLE_FIELD);

					Table table = new Table(localSchemaName, localTableName);

					// Loop on columns
					while(doLoop && localSchemaName.equals(results.getString(LOCAL_SCHEMA_FIELD)) && localTableName.equals(results.getString(LOCAL_TABLE_FIELD))) {
						String keyType           = results.getString(KEY_TYPE_FIELD);
						String localColumn       = results.getString(LOCAL_COLUMN_FIELD);
						String foreignSchemaName = results.getString(FOREIGN_SCHEMA_FIELD);
						String foreignTableName  = results.getString(FOREIGN_TABLE_FIELD);
						String foreignColumnName = results.getString(FOREIGN_COLUMN_FIELD);
						
						if(keyType.equals("PRIMARY KEY")) {
							table.addPrimaryKey(localColumn);
						} else if(keyType.equals("FOREIGN KEY")) {
							table.addForeignKey(new ForeignKey(table, localColumn, foreignSchemaName, foreignTableName, foreignColumnName));
						}

						doLoop = results.next();
					}

					tables.add(table);
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					if(results != null) {
						results.close();
					}
					if(statement != null) {
						statement.close();						
					}
				} catch (SQLException e) {
					System.err.println(e.getMessage());
					System.exit(1);
				}
			}
		}
	}

	/**
	 * Retrieve/load the triggers comments from the database
	 */
	private void loadTriggerComments() {
		if(triggerComments == null) {
			String SQL = "SELECT"
					+ "   n.nspname     AS schema_name,"
					+ "   c.relname     AS table_name,"
					+ "   t.tgname      AS trigger_name,"
					+ "   d.description AS description"
					+ " FROM pg_description d"
					+ " JOIN pg_trigger   t ON d.objoid = t.oid"
					+ " JOIN pg_class     c ON t.tgrelid = c.oid"
					+ " JOIN pg_namespace n ON n.oid = c.relnamespace";

			Statement statement = null;
			ResultSet results = null;
			try {
				statement = connection.createStatement();
				results = statement.executeQuery(SQL);

				triggerComments = new Vector<Comment>();

				Boolean exitLoop = results.next();
				while(exitLoop) {
					Comment f = new Comment(results.getString("schema_name"), results.getString("table_name"), results.getString("description"), Comment.TRIGGER_TYPE);

					triggerComments.add(f);

					exitLoop = results.next();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					if(results != null) {
						results.close();
					}
					if(statement != null) {
						statement.close();						
					}
				} catch (SQLException e) {
					System.err.println(e.getMessage());
					System.exit(1);
				}
			}
		}
	}
	
	/**
	 * Retrieve/load the indexes from the database
	 */
	private void loadIndexes() {
		if(indexes == null) {
			String SQL = "SELECT schemaname, tablename, indexname"
					+ " FROM pg_indexes"
					+ " WHERE schemaname NOT IN ('pg_catalog', 'information_schema')";

			Statement statement = null;
			ResultSet results = null;
			try {
				statement = connection.createStatement();
				results = statement.executeQuery(SQL);

				indexes = new Vector<Index>();

				Boolean exitLoop = results.next();
				while(exitLoop) {
					indexes.add(new Index(results.getString("schemaname"), results.getString("tablename"), results.getString("indexname")));

					exitLoop = results.next();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
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
	
	/**
	 * Close the previously established database connection
	 */
	public void disconnect() {
		try {
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

	/**
	 * Get the list of functions found in the database.
	 * Important: the method load() should be have been executed before this getter.
	 */
	public List<Function> getFunctions() {
		return functions;
	}

	/**
	 * Get the list of schemas found in the database.
	 * Important: the method load() should be have been executed before this getter.
	 */
	public List<Schema> getSchemas() {
		return schemas;
	}

	/**
	 * Get the list of tables found in the database.
	 * Important: the method load() should be have been executed before this getter.
	 */
	public List<Table> getTables() {
		return tables;
	}
	
	/**
	 * Get the list of indexes found in the database.
	 * Important: the method load() should be have been executed before this getter.
	 */
	public List<Index> getIndexes() {
		return indexes;
	}
	
	/**
	 * Get the list of views found in the database.
	 * Important: the method load() should be have been executed before this getter.
	 */
	public List<View> getViews() {
		return views;
	}

	/**
	 * Get the list of comments found in the database.
	 * Important: the method load() should be have been executed before this getter.
	 */
	public List<Comment> getComments() {
		List<Comment> res = new Vector<Comment>();
		res.addAll(tableComments);
		res.addAll(functionComments);
		res.addAll(viewComments);
		res.addAll(triggerComments);
		return res;
	}

	/**
	 * Get the list of triggers found in the database.
	 * Important: the method load() should be have been executed before this getter.
	 */
	public List<Trigger> getTriggers() { 
		return triggers;
	}
	
	public String getName() {
		return name;
	}
}