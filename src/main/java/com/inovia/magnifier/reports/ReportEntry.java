package com.inovia.magnifier.reports;

import com.inovia.magnifier.rule.RuleResult;

/**
 * it is the result of the execution of a rule on a single database entity.
 * for example: performing rule "TableHasPrimaryKey" on table "User" is summarized in a report entry
 */
public class ReportEntry {
	private String[] dataToDisplay;
	private Boolean isSuccess;
	private String details;
	
	public ReportEntry(String[] dataToDisplay, RuleResult result) {
		this.dataToDisplay = dataToDisplay.clone();
		this.isSuccess = result.isSuccess();
		this.details = result.getDetails();
	}
	
	public Boolean isSuccess() {
		return this.isSuccess;
	}

	public String getDetails() {
		return this.details;
	}

	public String[] getDataToDisplay() {
		return dataToDisplay;
	}
}
