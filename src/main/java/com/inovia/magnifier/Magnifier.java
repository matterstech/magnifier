package com.inovia.magnifier;

import org.apache.commons.cli.*;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.reports.Report;
import com.inovia.magnifier.rule.*;

/**
 * The Magnifier class contains Magnifier's main method.
 */
public class Magnifier {
	public static void main(String[] args) {
		Configuration cfg = new Configuration(args);

		try {
			if(!cfg.parseCommandLine()) {
				System.exit(1);
			}
		} catch(ParseException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

		Database database = DatabaseFactory.getDatabase(cfg.getDatabaseName(), cfg.getDatabaseType());

		// Creating a session to the database
		if(!database.connect(cfg.getDriverPath(), cfg.getHost(), cfg.getPort(), cfg.getUser(), cfg.getPassword())) {
			System.exit(1);
		}

		// Load all the entities from the database since Magnifier is READ ONLY and don't push or edit data from the database
		database.load();

		Ruleset inoviaRuleset = new InoviaRuleset(database);
		Report report = inoviaRuleset.run();
		
		report.generateHtml(cfg.getReportPath());
		
		report.printSummary();

		// Closing the database session
		database.disconnect();
	}
	
	private Magnifier() { }
}
