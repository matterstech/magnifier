package com.inovia.magnifier;

import java.io.File;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * it parses the command line parameters, check if all required ones are provided and make them available via getters 
 */
public class Configuration {
	private static final String[] EXPECTED_DBMS = {"postgresql"};
	private static final String REPORT_DEFAULT_NAME = "report.html";
	private static final String ABORT_MESSAGE = "Cannot continue";
	private static final String DEFAULT_HOST = "127.0.0.1";
	private static final String OPTIONS_TIP = "OptionsTip";

	private String connectionURL;
	private String host;
	private String port;
	private String databaseName;
	private String databaseType;
	private String driverPath;
	private String user;
	private String password;
	private String reportPath;

	/**
	 * 
	 * @param args the arguments provided when Magnifier was run
	 */
	public Configuration(String[] args) {
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
				new HelpFormatter().printHelp(OPTIONS_TIP, options);
				System.exit(1);
			}

			host = commandLine.getOptionValue("h");
			if(host == null) {
				host = DEFAULT_HOST;
			}

			databaseName = commandLine.getOptionValue("d");
			if(databaseName == null) {
				System.out.println(ABORT_MESSAGE + ": you must specify a database name");
				new HelpFormatter().printHelp(OPTIONS_TIP, options);
				System.exit(1);
			}

			databaseType = commandLine.getOptionValue("t");
			if(databaseType == null) {
				System.out.println(ABORT_MESSAGE + ": you must specify a database type");
				new HelpFormatter().printHelp(OPTIONS_TIP, options);
				System.exit(1);
			}

			port = commandLine.getOptionValue("p");
			if(port == null) {
				port = getDefaultPort(databaseType);
				if(port == null) {
					System.out.println(ABORT_MESSAGE + ": you must specify a database");
					new HelpFormatter().printHelp(OPTIONS_TIP, options);
					System.exit(1);
				}
			}

			driverPath = commandLine.getOptionValue("dp");
			if(driverPath == null || driverPath.isEmpty()) {
				System.out.println(ABORT_MESSAGE + ": you must specify a driver");
				new HelpFormatter().printHelp(OPTIONS_TIP, options);
				System.exit(1);
			}

			user = commandLine.getOptionValue("u");
			if(user == null) {
				System.out.println(ABORT_MESSAGE + ": you must specify a user");
				new HelpFormatter().printHelp(OPTIONS_TIP, options);
				System.exit(1);
			}

			password = commandLine.getOptionValue("pw");
			if(password == null) {
				System.out.println(ABORT_MESSAGE + ": you must specify a password for the user");
				new HelpFormatter().printHelp(OPTIONS_TIP, options);
				System.exit(1);
			}

			reportPath = commandLine.getOptionValue("o");
			if(reportPath == null) {
				reportPath = "./";
			}


			// Check if report file already exists
			File f = new File(reportPath);

			// Report path is a directory
			if(f.exists() && f.isDirectory()) {
				reportPath = reportPath + "/" + REPORT_DEFAULT_NAME;
				System.err.println("The report will be called: " + REPORT_DEFAULT_NAME);
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
		} catch (ParseException e) {
			System.err.println(ABORT_MESSAGE + ": The provided parameters cannot be processed");
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

	private static String getDefaultPort(String type) {
		if(type.equals("postgresql")) {
			return "5432";
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
}
