package com.inovia.magnifier.database.postgresql;

import com.inovia.magnifier.database.Function;


public class PGFunction extends Function {
	
	
	/*public static PGFunction[] getAll(Connection connection) {
		if(pool == null) {
			final String SQL = "SELECT routines.routine_name, parameters.data_type, parameters.parameter_mode, parameters.parameter_name, parameters.data_type FROM information_schema.routines JOIN information_schema.parameters ON routines.specific_name = parameters.specific_name WHERE routines.specific_schema not in ('pg_catalog', 'information_schema') ORDER BY routines.routine_name ASC, parameters.ordinal_position ASC;";

			Statement statement = null;
			ResultSet results = null;
			try {
				statement = connection.createStatement();
				results = statement.executeQuery(SQL);

				pool = new HashSet<PGFunction>();

				Boolean exitLoop = results.next();
				while(exitLoop) {
					

					exitLoop = results.next();
					while(exitLoop && results.getString(FUNCTION_NAME_FIELD).equals(functionName)) {
						
						
						exitLoop = results.next();
					}

					pool.add(new PGFunction(functionName, parameters));
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

		return pool;
	}*/


}
