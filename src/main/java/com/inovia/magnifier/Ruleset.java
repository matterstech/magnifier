package com.inovia.magnifier;

import java.util.*;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.reports.*;

/**
 * The Ruleset abstract class deals with the expected
 * behaviour of rule sets.
 */
public abstract class Ruleset {
	protected List<Rule> rules;
	protected Report report;
	protected Database database;
	protected Date startDate;
    protected Date endDate;
	
	public Ruleset(Database database) {
		this.database = database;
		
		rules = this.getRules();
		report = new Report(database.getName());
	}
	
	final public Report run() {
		startDate = new Date();
		
		for(Rule r : this.getRules()) {
			report.addRuleReport(r.run());
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
