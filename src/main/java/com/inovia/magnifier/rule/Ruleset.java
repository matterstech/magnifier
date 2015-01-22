package com.inovia.magnifier.rule;

import java.util.Date;
import java.util.List;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.reports.Report;

/**
 * The Ruleset abstract class deals with the expected
 * behaviour of rule sets.
 */
public abstract class Ruleset {
	private Report report;
	private Database database;
	
	public Ruleset(Database database) {
		this.database = database;
		
		report = new Report(database.getName());
	}
	
	final public Report run() {
		report.setStartTime(new Date());
		
		for(Rule r : this.getRules()) {
			report.addRuleReport(r.run(database));
		}
		
		report.setEndTime(new Date());
		return report;
	}
	
	final public Database getDatabase() {
		return database;
	}
	
	abstract protected List<Rule> getRules();
}
