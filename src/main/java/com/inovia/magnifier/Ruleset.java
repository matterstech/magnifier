package com.inovia.magnifier;

import java.util.*;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.reports.*;

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
	
	public Report run() {
		startDate = new Date();
		
		for(Rule r : this.getRules()) {
			report.addRuleReport(r.run());
		}
		
		endDate = new Date();
		return report;
	}
	
	public void generateHtml(String path) {
		report.generateHtml(path, startDate, endDate);
	}
	
	abstract protected List<Rule> getRules();
}
