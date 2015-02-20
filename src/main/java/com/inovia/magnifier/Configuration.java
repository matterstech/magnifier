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
	private static final String DEFAULT_REPORT_PATH = "./";
	private static final String DEFAULT_HOST = "localhost";
	
	private static final String SECTION_CREDENTIALS = "credentials";
	private static final String SECTION_DATABASE = "database";
	private static final String SECTION_MISC = "misc";
	
	static final String DEFAULT_INI = "./magnifier.ini";
	
	private static final String PARAM_HELP           = "help";
	private static final String PARAM_HOST           = "host";
	private static final String PARAM_PORT           = "port";
	private static final String PARAM_DBMS           = "dbms";
	private static final String PARAM_DRIVER         = "driver";
	private static final String PARAM_DATABASE       = "database";
	private static final String PARAM_USER           = "user";
	private static final String PARAM_PASSWORD       = "password";
	private static final String PARAM_OUTPUT         = "output";

	private String host = DEFAULT_HOST;
	private String port;
	private String databaseName;
	private String databaseType;
	private String driverFile;
	private String user;
	private String password;
	private String reportPath = DEFAULT_REPORT_PATH;
	private Boolean isHelp = false;

	private Options options;
	
	/**
	 * 
	 * @param args the arguments provided when Magnifier was run
	 */
	public Configuration() {
		this.options = new Options();

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
	public void overrideWithCommandLine(String[] args) {
		try {
			BasicParser parser = new BasicParser();
			CommandLine params = parser.parse(options, args);
			
			String tmpHost         = params.getOptionValue(PARAM_HOST);
			String tmpPort         = params.getOptionValue(PARAM_PORT);
			String tmpDatabaseType = params.getOptionValue(PARAM_DBMS);
			String tmpDriverFile   = params.getOptionValue(PARAM_DRIVER);
			String tmpDatabaseName = params.getOptionValue(PARAM_DATABASE);
			String tmpUser         = params.getOptionValue(PARAM_USER);
			String tmpPassword     = params.getOptionValue(PARAM_PASSWORD);
			String tmpReportPath   = params.getOptionValue(PARAM_OUTPUT);
			
			this.isHelp = params.hasOption(PARAM_HELP);
			
			if(tmpHost != null) { this.host = tmpHost; }
			if(tmpPort != null) { this.port = tmpPort; }
			if(tmpDatabaseType != null) { this.databaseType = tmpDatabaseType; }
			if(tmpDriverFile != null) { this.driverFile = tmpDriverFile; }
			if(tmpDatabaseName != null) { this.databaseName = tmpDatabaseName; }
			if(tmpUser != null) { this.user = tmpUser; }
			if(tmpPassword != null) { this.password = tmpPassword; }
			if(tmpReportPath != null) { this.reportPath = tmpReportPath; }
			
			return;
		} catch (ParseException e) {
			return;
		}
	}
	
	public Boolean isHelp() {
		return this.isHelp;
	}
	
	public void printHelp() {
		new HelpFormatter().printHelp("OptionTips", this.getOptions());

		System.err.println("Missing params:");
		if(null == getHost()) { System.out.println(PARAM_HOST); }
		if(null == getPort()) { System.out.println(PARAM_PORT); }
		if(null == getDatabaseType()) { System.out.println(PARAM_DBMS); }
		if(null == getDriverFile()) { System.out.println(PARAM_DRIVER); }
		if(null == getDatabaseName()) { System.out.println(PARAM_DATABASE); }
		if(null == getUser()) { System.out.println(PARAM_USER); }
		if(null == getPassword()) { System.out.println(PARAM_PASSWORD); }
		if(null == getReportPath()) { System.out.println(PARAM_OUTPUT); }
	}
	
	/**
	 * parses the ini configuration and fill the configuration with it
	 * 
	 * @return false if no file could be found/read
	 */
	public Boolean overrideWithIni(File iniFile) {
		if(!iniFile.exists() || iniFile.isDirectory()) { return false; }
		
		try {
			Wini ini = new Wini(iniFile);
			
			String tmpUser         = ini.get(SECTION_CREDENTIALS, PARAM_USER, String.class);
			String tmpPassword     = ini.get(SECTION_CREDENTIALS, PARAM_PASSWORD, String.class);
			String tmpHost         = ini.get(SECTION_DATABASE, PARAM_HOST, String.class);
			String tmpPort         = ini.get(SECTION_DATABASE, PARAM_PORT, String.class);
			String tmpDatabaseName = ini.get(SECTION_DATABASE, PARAM_DATABASE, String.class);
			String tmpDatabaseType = ini.get(SECTION_DATABASE, PARAM_DBMS, String.class);
			String tmpDriverFile   = ini.get(SECTION_DATABASE, PARAM_DRIVER, String.class);
			String tmpReportPath   = ini.get(SECTION_MISC, PARAM_OUTPUT, String.class);
			
			if(tmpUser != null) { this.user = tmpUser; }
			if(tmpPassword != null) { this.password = tmpPassword; }
			if(tmpHost != null) { this.host = tmpHost; }
			if(tmpPort != null) { this.port = tmpPort; }
			if(tmpDatabaseName != null) { this.databaseName = tmpDatabaseName; }
			if(tmpDatabaseType != null) { this.databaseType = tmpDatabaseType; }
			if(tmpDriverFile != null) { this.driverFile = tmpDriverFile; }
			if(tmpReportPath != null) { this.reportPath = tmpReportPath; }
			
		} catch (InvalidFileFormatException e) {
			System.err.println("Configuration file couldn't be read");
			return false;
		} catch (IOException e) {
			System.err.println("Configuration file couldn't be read");
			return false;
		}
		
		return true;
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
	public String getDriverFile() {
		return driverFile;
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
		if(this.reportPath == null) {
			return DEFAULT_REPORT_PATH;
		}
		return this.reportPath;
	}
	
	/**
	 * 
	 * @return the URL JDBC will need to connect to the database
	 */
	public String getConnectionURL() {
		if(this.isValid()) {
			String url = "jdbc:" + this.databaseType + "://" + this.host + ":" + this.port + "/" + this.databaseName;
			return url;
		}
		return null;
	}

	public Options getOptions() {
		return options;
	}
	
	/*
	 * return true when all the necessary informations are provided
	 */
	public Boolean isValid() {
		return     null != getHost()
				&& null != getPort()
				&& null != getDatabaseType()
				&& null != getDriverFile()
				&& null != getDatabaseName()
				&& null != getUser()
				&& null != getPassword()
				&& null != getReportPath();
	}
}
