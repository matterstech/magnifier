package com.inovia.magnifier;

import java.io.File;

import org.apache.commons.cli.*;

/**
 * it parses the command line parameters, check if all required ones are provided and make them available via getters 
 */
public class Configuration {
	private static final String[] EXPECTED_DBMS = {"postgresql"};
	private static final String DEFAULT_REPORT_FILE = "report.html";
	private static final String ABORT_MESSAGE = "Cannot continue";
	private static final String DEFAULT_HOST = "127.0.0.1";

	private static final String PARAM_HELP           = "help";
	private static final String PARAM_HOST           = "host";
	private static final String PARAM_PORT           = "port";
	private static final String PARAM_DBMS           = "dbms";
	private static final String PARAM_DRIVER         = "driver";
	private static final String PARAM_DATABASE       = "database";
	private static final String PARAM_USER           = "user";
	private static final String PARAM_PASSWORD       = "password";
	private static final String PARAM_OUTPUT         = "output";

	private static final String POSTGRESQL_TYPE = "postgresql";
	private static final Integer POSTGRESQL_DEFAULT_PORT = 5432;

	private String connectionURL;
	private String host = DEFAULT_HOST;
	private String port;
	private String databaseName;
	private String databaseType;
	private String driverPath;
	private String user;
	private String password;
	private String reportPath = DEFAULT_REPORT_FILE;

	private String[] args;
	private Options options;

	/**
	 * 
	 * @param args the arguments provided when Magnifier was run
	 */
	public Configuration(String[] args) {
		this.options = new Options();
		this.args = args.clone();

		// We describe the parameters Magnifier can be provided
		options.addOption(null, PARAM_HELP,     false, "print this message");
		options.addOption(null, PARAM_HOST,     true, "The database host, default is localhost");
		options.addOption(null, PARAM_PORT,     true, "The database listening port, default is the specified DBMS default port");
		options.addOption(null, PARAM_DBMS,     true, "The database type");
		options.addOption(null, PARAM_DRIVER,   true, "The JDBC driver package");
		options.addOption(null, PARAM_DATABASE, true, "The database name");
		options.addOption(null, PARAM_USER,     true, "The username");
		options.addOption(null, PARAM_PASSWORD, true, "The password");
		options.addOption(null, PARAM_OUTPUT,   true, "The report output path/name, default is ./report.html");
	}

	/**
	 * parses the command line parameters and performs validation
	 * 
	 * @return whether the program should continue or not
	 * @throws ParseException when an required parameter is not provided
	 */
	public Boolean parseCommandLine() throws ParseException {
		BasicParser parser = new BasicParser();
		CommandLine params = parser.parse(options, args);

		if(params.hasOption(PARAM_HELP)) {
			new HelpFormatter().printHelp("OptionTips", options);
			return false;
		}

		if(!params.hasOption(PARAM_DATABASE)) { throw new ParseException("Please provide a database name"); }
		databaseName = params.getOptionValue(PARAM_DATABASE);

		if(!params.hasOption(PARAM_DBMS)) { throw new ParseException("Please provide a dbms name"); }
		databaseType = params.getOptionValue(PARAM_DBMS);
		port = getDefaultPort(databaseType).toString();

		if(!params.hasOption(PARAM_DRIVER)) { throw new ParseException("Please provide a JDBC driver to connect to the database"); }
		driverPath = params.getOptionValue(PARAM_DRIVER);

		if(!params.hasOption(PARAM_USER)) { throw new ParseException("Please provide a username to connect to the database"); }
		user = params.getOptionValue(PARAM_USER);

		if(!params.hasOption(PARAM_PASSWORD)) { throw new ParseException("Please provide a password to connect to the database"); }
		password = params.getOptionValue(PARAM_PASSWORD);

		if(params.hasOption(PARAM_OUTPUT)) {
			reportPath = params.getOptionValue(PARAM_OUTPUT);
		}

		if(params.hasOption(PARAM_HOST)) {
			host = params.getOptionValue(PARAM_HOST);
		}

		if(params.hasOption(PARAM_PORT)) {
			port = params.getOptionValue(PARAM_PORT);
		}


		// Check if report file already exists
		File f = new File(reportPath);
		if(f.exists() && f.isDirectory()) {
			// Provided report file is actually a directory
			reportPath = reportPath + "/" + DEFAULT_REPORT_FILE;
			System.err.println("The report will be called: " + DEFAULT_REPORT_FILE);
		}

		// We check if the DBMS is acceptable
		Boolean isExpectedDBMS = false;
		// Try to find the provided DBMS inside the supported ones.
		for(Integer i = EXPECTED_DBMS.length-1 ; i >= 0 && !isExpectedDBMS; i--) {
			if(EXPECTED_DBMS[i].equals(databaseType)) {
				isExpectedDBMS = true;
			}
		}

		if(!isExpectedDBMS) {
			System.err.println(ABORT_MESSAGE + ": Your DBMS is wrong or not yet supported");
			System.exit(1);
		}

		connectionURL = "jdbc:" + databaseType + "://" + host + ":" + port + "/" + databaseName;

		// We inform the called that the required parameters are provided, so it can proceed
		return true;
	}

	private static Integer getDefaultPort(String type) {
		if(type.equals(POSTGRESQL_TYPE)) {
			return POSTGRESQL_DEFAULT_PORT;
		}
		return null;
	}

	/**
	 * 
	 * @return the host on which the database runs
	 */
	public String getHost() {
		return host;
	}

	/**
	 * 
	 * @return the port on which the database is listening
	 */
	public String getPort() {
		return port;
	}

	/**
	 * 
	 * @return the name of the database
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * 
	 * @return the database management system
	 */
	public String getDatabaseType() {
		return databaseType;
	}

	/**
	 * 
	 * @return the JDBC driver file url
	 */
	public String getDriverPath() {
		return driverPath;
	}

	/**
	 * 
	 * @return the user with which to authenticate to the database
	 */
	public String getUser() {
		return user;
	}

	/**
	 * 
	 * @return the password with which to authenticate to the database
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @return the desired path in which the report will be generated
	 */
	public String getReportPath() {
		return reportPath;
	}

	/**
	 * 
	 * @return the URL JDBC will need to connect to the database
	 */
	public String getConnectionURL() {
		return connectionURL;
	}

	public Options getOptions() {
		return options;
	}
}
