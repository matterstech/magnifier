package com.inovia.magnifier.rule;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.reports.RuleReport;

/**
 * Checks if the convention is followed
 */
public interface Rule {
	/**
	 * 
	 * @param database the database to analyze
	 * @return a report about the execution of the rule on the database
	 */
	public RuleReport run(Database database);
	public String[] getRuleReportFormat();
	public String getName();
	public String[] getLinks();
	public String getSuggestion();
}
