package com.inovia.magnifier;

import java.io.File;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.rule.*;

/**
 * The Magnifier class contains Magnifier's main method.
 */
public class Magnifier {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.overrideWithIni(new File(Configuration.DEFAULT_INI));
		cfg.overrideWithCommandLine(args);

		if(cfg.isHelp()) {
			cfg.printHelp();
			return;
		}
		
		if(!cfg.isValid()) {
			cfg.printHelp();
			return;
		}
		
		//Creating a session to the database
		Database database = DatabaseFactory.getDatabase(cfg.getDatabaseName(), cfg.getDatabaseType());
		if(!database.connect(cfg.getDriverFile(), cfg.getHost(), cfg.getPort(), cfg.getUser(), cfg.getPassword())) {
			System.err.println("Couldn't connect to the database");
			return;
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
