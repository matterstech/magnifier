package com.inovia.magnifier;

import org.apache.commons.cli.*;

/**
 * 
 * @author joeyrogues
 *
 */
public class Configuration {
	private static final String[] EXPECTED_PARAMETERS = {"h", "p", "t", "dp", "d", "s", "u", "pw", "o"};
	private static final String[] EXPECTED_DBMS = {"postgresql"};

	private String connectionURL;
	private String host;
	private String port;
	private String databaseName;
	private String schema;
	private String databaseType;
	private String driverPath;
	private String user;
	private String password;
	private String reportPath;

	public Configuration(String[] args) throws UnsupportedOperationException {
		Options options = new Options();

		// We describe the parameters Magnifier can be given
		options.addOption("h", true, "The database host");
		options.addOption("p", true, "The database listening port");
		options.addOption("t", true, "DBMS");
		options.addOption("dp", true, "The JDBC driver package");
		options.addOption("d", true, "The database name");
		options.addOption("s", true, "The database schema");
		options.addOption("u", true, "The username");
		options.addOption("pw", true, "The password");
		options.addOption("o", true, "The report output path/name");

		BasicParser parser = new BasicParser();
		try {
			CommandLine commandLine = parser.parse(options, args);

			// We check if all the necessary parameters are provided
			// If not, we exit the program
			for(String parameter : EXPECTED_PARAMETERS) {
				if(!commandLine.hasOption(parameter)) {
					new HelpFormatter().printHelp("OptionsTip", options);

					throw new MissingOptionException(parameter);
				}
			}
			
			host         = commandLine.getOptionValue("h");
			port         = commandLine.getOptionValue("p");
			databaseName = commandLine.getOptionValue("d");
			schema       = commandLine.getOptionValue("s");
			databaseType = commandLine.getOptionValue("t");
			driverPath   = commandLine.getOptionValue("dp");
			user         = commandLine.getOptionValue("u");
			password     = commandLine.getOptionValue("pw");
			reportPath   = commandLine.getOptionValue("o");
			
			// We check if the DBMS is acceptable
			Boolean is_expected_dbms = false;
			// Try to find the provided DBMS inside the supported ones.
			for(Integer i = EXPECTED_DBMS.length-1 ; i >= 0 && !is_expected_dbms; i--) {
				if(EXPECTED_DBMS[i].equals(getDatabaseType())) {
					is_expected_dbms = true;
				}
			}
			
			if(!is_expected_dbms) {
				throw new UnsupportedOperationException("Your DBMS is wrong or not yet supported");
			}
			
			connectionURL = "jdbc:" + getDatabaseType() + "://" + getHost() + ":" + getPort() + "/" + getDatabaseName();
			
			// Database type validation
			// For now, only Postgres is supported
		} catch (ParseException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}
	
	public String getDatabaseName() {
		return databaseName;
	}
	
	public String getSchema() {
		return schema;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public String getDriverPath() {
		return driverPath;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getReportPath() {
		return reportPath;
	}

	public String getConnectionURL() {
		return connectionURL;
	}
}
