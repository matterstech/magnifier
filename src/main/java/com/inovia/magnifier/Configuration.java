package com.inovia.magnifier;

import java.io.File;
import java.io.IOException;

import org.apache.commons.cli.*;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

/**
 * it parses the command line parameters, check if all required ones are provided and make them available via getters 
 */
public class Configuration {
	private static final String[] EXPECTED_DBMS = {"postgresql"};
	private static final String DEFAULT_REPORT_FILE = "report.html";
	private static final String ABORT_MESSAGE = "Cannot continue";
	private static final String DEFAULT_HOST = "127.0.0.1";

	private static final String PARAM_HELP     = "help";
	private static final String PARAM_HOST     = "host";
	private static final String PARAM_PORT     = "port";
	private static final String PARAM_DBMS     = "dbms";
	private static final String PARAM_DRIVER   = "driver";
	private static final String PARAM_DATABASE = "database";
	private static final String PARAM_USER     = "user";
	private static final String PARAM_PASSWORD = "password";
	private static final String PARAM_OUTPUT   = "output";
	
	private static final String SECTION_CREDENTIALS = "credentials";

	private static final String POSTGRESQL_TYPE = "postgresql";
	private static final Integer POSTGRESQL_DEFAULT_PORT = 5432;
	
	private static final String LOCAL_PATH = "./";
	static final String DEFAULT_INI = "magnifier.ini";

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
		options.addOption(PARAM_HELP,     false, "print this message");
		options.addOption(PARAM_HOST,     true, "The database host, default is localhost");
		options.addOption(PARAM_PORT,     true, "The database listening port, default is the specified DBMS default port");
		options.addOption(PARAM_DBMS,     true, "The database type");
		options.addOption(PARAM_DRIVER,   true, "The JDBC driver package");
		options.addOption(PARAM_DATABASE, true, "The database name");
		options.addOption(PARAM_USER,     true, "The username");
		options.addOption(PARAM_PASSWORD, true, "The password");
		options.addOption(PARAM_OUTPUT,   true, "The report output path/name, default is ./report.html");
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

		if(params.hasOption(PARAM_USER)) {
			user = params.getOptionValue(PARAM_USER);
		} else {
			if(user == null) {
				throw new ParseException("Please provide a username to connect to the database");
			}
		}

		if(params.hasOption(PARAM_PASSWORD)) {
			password = params.getOptionValue(PARAM_PASSWORD);
		} else {
			if(password == null) {
				throw new ParseException("Please provide a username to connect to the database");
			}
		}

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
	
	/**
	 * parses the ini configuration
	 * 
	 * @return false if no file could be found/read
	 */
	public Boolean parseIni(File iniFile) {
		if(!iniFile.exists() || iniFile.isDirectory()) {
			return false;
		}
		
		try {
			Wini ini = new Wini(iniFile);
			
			String user = ini.get(SECTION_CREDENTIALS, PARAM_USER, String.class);
			String password = ini.get(SECTION_CREDENTIALS, PARAM_PASSWORD, String.class);
			
			if(user != null) {
				this.user = user;
			}
			if(password != null) {
				this.password = password;
			}
			
		} catch (InvalidFileFormatException e) {
			System.err.println("Configuration file couldn't be read");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Configuration file couldn't be read");
			System.exit(1);
		}
		
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
