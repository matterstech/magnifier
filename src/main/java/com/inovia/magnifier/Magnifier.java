package com.inovia.magnifier;

import java.io.File;

import org.apache.commons.cli.*;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.rule.*;

/**
 * The Magnifier class contains Magnifier's main method.
 */
public class Magnifier {
	public static void main(String[] args) {
		Configuration cfg = new Configuration(args);

		try {
			File iniFile = new File(Configuration.DEFAULT_INI);
			if(!cfg.parseIni(iniFile) || !cfg.parseCommandLine()) {
				System.exit(1);
			}
		} catch(ParseException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}

		Database database = DatabaseFactory.getDatabase(cfg.getDatabaseName(), cfg.getDatabaseType());

		// Creating a session to the database
		if(cfg.getDriverPath() == null || cfg.getHost() == null || cfg.getPort() == null || cfg.getUser() == null || cfg.getPassword() == null) {
			System.err.println("Not enough params to proceed");
			System.exit(1);
		}
		if(!database.connect(cfg.getDriverPath(), cfg.getHost(), cfg.getPort(), cfg.getUser(), cfg.getPassword())) {
			System.exit(1);
		}

		// Load all the entities from the database since Magnifier is READ ONLY and don't push or edit data from the database
		database.load();

		Ruleset inoviaRuleset = new InoviaRuleset(database);
		inoviaRuleset.run();
		inoviaRuleset.generateHtml(cfg.getReportPath());

		// Closing the database session
		database.disconnect();
	}
	
	private Magnifier() { }
}
