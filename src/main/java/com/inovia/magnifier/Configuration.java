package com.inovia.magnifier;

import java.io.File;

import org.apache.commons.cli.*;

/**
 * The Configuration class deals with parsing and
 * providing the command line parameters 
 */
public class Configuration {
	private static final String[] EXPECTED_DBMS = {"postgresql"};
	private static final String REPORT_DEFAULT_NAME = "report.html";
	private static final String ABORT_MESSAGE = "Cannot continue";
	private static final String DEFAULT_HOST = "127.0.0.1";

	private String help;
	
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

		Option helpOption = new Option( "help", "print this message" );
		options.addOption(helpOption);
		
		// We describe the parameters Magnifier can be given
		options.addOption("h", false, "The database host, default is localhost");
		options.addOption("p", true, "The database listening port, default is the specified DBMS default port");
		options.addOption("t", true, "DBMS");
		options.addOption("dp", true, "The JDBC driver package");
		options.addOption("d", true, "The database name");
		options.addOption("u", true, "The username");
		options.addOption("pw", true, "The password");
		options.addOption("o", true, "The report output path/name, default is ./report.html");

		BasicParser parser = new BasicParser();
		try {
			CommandLine commandLine = parser.parse(options, args);
			
			if(commandLine.hasOption("help")) {
				new HelpFormatter().printHelp("OptionsTip", options);
				System.exit(1);
			}
			
			host = commandLine.getOptionValue("h");
			if(host == null) {
				host = DEFAULT_HOST;
			}
			
			databaseName = commandLine.getOptionValue("d");
			if(databaseName == null) {
				System.out.println(ABORT_MESSAGE + ": you must specify a database name");
				new HelpFormatter().printHelp("OptionsTip", options);
				System.exit(1);
			}
			
			databaseType = commandLine.getOptionValue("t");
			if(databaseType == null) {
				System.out.println(ABORT_MESSAGE + ": you must specify a database type");
				new HelpFormatter().printHelp("OptionsTip", options);
				System.exit(1);
			}
			
			port = commandLine.getOptionValue("p");
			if(port == null) {
				port = getDefaultPort(databaseType);
				if(port == null) {
					System.out.println(ABORT_MESSAGE + ": you must specify a database");
					new HelpFormatter().printHelp("OptionsTip", options);
					System.exit(1);
				}
			}
			
			driverPath = commandLine.getOptionValue("dp");
			if(driverPath == null || driverPath.isEmpty()) {
				System.out.println(ABORT_MESSAGE + ": you must specify a driver");
				new HelpFormatter().printHelp("OptionsTip", options);
				System.exit(1);
			}
			
			user = commandLine.getOptionValue("u");
			if(user == null) {
				System.out.println(ABORT_MESSAGE + ": you must specify a user");
				new HelpFormatter().printHelp("OptionsTip", options);
				System.exit(1);
			}
			
			password = commandLine.getOptionValue("pw");
			if(password == null) {
				System.out.println(ABORT_MESSAGE + ": you must specify a password for the user");
				new HelpFormatter().printHelp("OptionsTip", options);
				System.exit(1);
			}
			
			reportPath = commandLine.getOptionValue("o");
			if(reportPath == null) {
				reportPath = "./";
			}
			
			
			// Check if report file already exists
			File f = new File(reportPath);
			
			// Report path is a directory
			if(f.exists()) {
				 if(f.isDirectory()) {
					 reportPath = reportPath + "/" + REPORT_DEFAULT_NAME;
					 System.err.println("The report will be called: " + REPORT_DEFAULT_NAME);
				 }
			}
			
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

	private String getDefaultPort(String type) {
		if(type.equals("postgresql")) {
			return "5432";
		}
		return null;
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
