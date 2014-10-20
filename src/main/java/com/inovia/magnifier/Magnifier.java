package com.inovia.magnifier;

import com.inovia.magnifier.database.*;


public class Magnifier {
	public static void main(String[] args) {
		Configuration configuration = null;
		com.inovia.magnifier.database.Database database = null;
		configuration = new Configuration(args);
		database = DatabaseFactory.getDatabase(
				configuration.getDriverPath(),
				configuration.getDatabaseType(),
				configuration.getHost(),
				configuration.getPort(),
				configuration.getDatabaseName(),
				configuration.getUser(),
				configuration.getPassword());

		// Bootstrap
		database.connect();
		database.load();

		Ruleset inoviaRuleset = new InoviaRuleset(database);
		inoviaRuleset.run();
		inoviaRuleset.generateHtml(configuration.getReportPath());
		
		database.disconnect();
	}
}
