package com.inovia.magnifier.reports;

/**
 * A report entry is the result of the execution of a rule
 * on an entity.
 * For example: performing rule "TableHasPrimaryKey" on table "User"
 * will be summarized in a report entry
 *
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
