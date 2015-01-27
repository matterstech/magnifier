package com.inovia.magnifier.rule;

public class RuleResult {
	private Boolean isSuccess;
	private String details;
	
	public RuleResult(Boolean isSuccess, String details) {
		this.isSuccess = isSuccess;
		this.details = details;
	}
	
	public Boolean isSuccess() {
		return this.isSuccess;
	}
	
	public String getDetails() {
		return this.details;
	}
}
