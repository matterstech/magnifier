package com.inovia.magnifier.reports;

public class ReportEntry {
	private Object entity;
	private Boolean isSuccess;
	
	public ReportEntry(Object entity, Boolean isSuccess) {
		this.entity = entity;
		this.isSuccess = isSuccess;
	}
	
	public Boolean isSuccess() { 
		return isSuccess;
	}
	
	public Object getEntity() {
		return entity;
	}
	
	
	
	public String toString() {
		return "{ entity: {" + entity.toString() + "}, success: " + isSuccess + "}";
	}
}
