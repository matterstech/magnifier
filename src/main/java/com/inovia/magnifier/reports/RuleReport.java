package com.inovia.magnifier.reports;

import java.util.Vector;


public class RuleReport {
	private String ruleName;
	private String suggestion;
	private Float score;
	private Vector<ReportEntry> entries;
	private Float debt;
	
	public RuleReport(String ruleName, String suggestion, Float debt) {
		entries = new Vector<ReportEntry>();
		this.ruleName = ruleName;
		this.suggestion = suggestion;
		this.debt = debt;
	}
	
	public Float getScore() {
		if(entries.size() == 0) {
			return 100.0F;
		}
		
		Float tmp = 0F;
		
		if(score == null) {
			for(ReportEntry e : entries) {
				if(e.isSuccess()) {
					tmp++;
				}
			}
			
			score = tmp * 100F / ((float) entries.size());
		}
		
		return score;
	}
	
	public boolean addEntry(ReportEntry entry) {
		return entries.add(entry);
	}
	
	public Vector<ReportEntry> getEntries() {
		return entries;
	}
	
	public String getRuleName() {
		return ruleName;
	}
	
	public String getSuggestion() {
		return suggestion;
	}
	
	public Float getDebt() {
		Integer failingEntityCount = 0;
		
		for(ReportEntry e : entries) {
			if(!e.isSuccess()) {
				failingEntityCount++;
			}
		}
		
		return debt * failingEntityCount;
	}
}
