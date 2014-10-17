package com.inovia.magnifier;

import java.util.*;

import com.inovia.magnifier.reports.*;

public abstract class Ruleset {
	protected List<Rule> rules;
	protected Report report;
	protected Configuration configuration;
	
	public Ruleset(Configuration configuration) {
		this.configuration = configuration;
		
		rules = this.getRules();
		report = new Report(configuration.getDatabaseName());
	}
	
	public Report run() {
		Date startDate = new Date();
		
		for(Rule r : this.getRules()) {
			report.addRuleReport(r.run());
		}
		
		Date endDate = new Date();
		report.generateHtml(configuration.getDatabaseName(), startDate, endDate);
		
		return report;
	}
	
	abstract protected List<Rule> getRules();
}
