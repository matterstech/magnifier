package com.inovia.magnifier.reports;

/**
 * it is the result of the execution of a rule on a single database entity.
 * for example: performing rule "TableHasPrimaryKey" on table "User" is summarized in a report entry
 */
public class ReportEntry {
	private String[] dataToDisplay;
	private Boolean isSuccess;
	private Object object;
	
	public ReportEntry(Object o, String[] dataToDisplay, Boolean isSuccess) {
		this.dataToDisplay = dataToDisplay.clone();
		this.isSuccess = isSuccess;
		this.object = o;
	}
	
	public Boolean isSuccess() {
		return isSuccess;
	}

	public String[] getDataToDisplay() {
		return dataToDisplay;
	}
	
	public Object getObject() {
		return object;
	}
}
