package com.inovia.magnifier.reports;


public class ReportEntry {
	private String entityDescription;
	private Integer technicalDebt;
	private Boolean isSuccess;
	
	public ReportEntry(String entityDescription, Boolean isSuccess) {
		this.entityDescription = entityDescription;
		this.isSuccess = isSuccess;
	}

	public Integer getTechnicalDebt() {
		return technicalDebt;
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
