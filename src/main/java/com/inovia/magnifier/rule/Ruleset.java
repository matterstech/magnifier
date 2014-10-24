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
	private Date startDate;
	private Date endDate;
	
	public Ruleset(Database database) {
		this.database = database;
		
		report = new Report(database.getName());
	}
	
	final public Report run() {
		startDate = new Date();
		
		for(Rule r : this.getRules()) {
			report.addRuleReport(r.run(database));
		}
		
		endDate = new Date();
		return report;
	}
	
	final public void generateHtml(String path) {
		report.generateHtml(path, startDate, endDate);
	}
	
	final public Database getDatabase() {
		return database;
	}
	
	abstract protected List<Rule> getRules();
}
