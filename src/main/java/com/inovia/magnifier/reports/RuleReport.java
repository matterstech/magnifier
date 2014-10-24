package com.inovia.magnifier.reports;

import java.util.*;

/**
 * it is a report about the execution of a rule on the database.
 * it includes a set of report entries
 */
public class RuleReport {
	private final static Float MAX_SCORE = 100.0F;
	private final static Float MIN_SCORE =   0.0F;
	
	private String ruleName;
	private String suggestion;
	private Float score;
	private List<ReportEntry> entries;
	private Float debt;
	
	public RuleReport(String ruleName, String suggestion, Float debt) {
		entries = new Vector<ReportEntry>();
		this.ruleName = ruleName;
		this.suggestion = suggestion;
		this.debt = debt;
	}
	
	public Float getScore() {
		if(entries.size() == 0) {
			return MAX_SCORE;
		}
		
		Float tmp = MIN_SCORE;
		
		for(ReportEntry e : entries) {
			if(e.isSuccess()) {
				tmp++;
			}
		}
			
		score = tmp * MAX_SCORE / ((float) entries.size());
		
		return score;
	}
	
	public boolean addEntry(ReportEntry entry) {
		return entries.add(entry);
	}
	
	public List<ReportEntry> getEntries() {
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
