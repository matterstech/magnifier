package com.inovia.magnifier.reports;

/**
 * it is the result of the execution of a rule on a single database entity.
 * for example: performing rule "TableHasPrimaryKey" on table "User" is summarized in a report entry
 */
public class ReportEntry {
	private String[] dataToDisplay;
	private Boolean isSuccess;
	
	public ReportEntry(String[] dataToDisplay, Boolean isSuccess) {
		this.dataToDisplay = dataToDisplay.clone();
		this.isSuccess = isSuccess;
	}
	
	public Boolean isSuccess() {
		return isSuccess;
	}

	public String[] getDataToDisplay() {
		return dataToDisplay;
	}
}
