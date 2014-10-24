package com.inovia.magnifier.reports;

/**
 * it is the result of the execution of a rule on a single database entity.
 * for example: performing rule "TableHasPrimaryKey" on table "User" is summarized in a report entry
 */
public class ReportEntry {
	private String entityDescription;
	private Boolean isSuccess;
	
	public ReportEntry(String entityDescription, Boolean isSuccess) {
		this.entityDescription = entityDescription;
		this.isSuccess = isSuccess;
	}
	
	public Boolean isSuccess() {
		return isSuccess;
	}
	
	public String toString() {
		return "{ " + entityDescription + ", success: " + isSuccess + "}";
	}

	public String getEntityDescription() {
		return entityDescription;
	}
}
