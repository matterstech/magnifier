package com.inovia.magnifier.reports;

import java.util.*;
import com.inovia.magnifier.rule.Rule;

/**
 * it is a report about the execution of a rule on the database.
 * it includes a set of report entries
 */
public class RuleReport {
	private final static Float MAX_SCORE = 100.0F;
	private final static Float MIN_SCORE =   0.0F;
	
	private Rule rule;
	private String suggestion;
	private List<ReportEntry> entries;
	private Float debt;
	
	public RuleReport(Rule rule, String suggestion, Float debt) {
		entries = new Vector<ReportEntry>();
		this.suggestion = suggestion;
		this.debt = debt;
		this.rule = rule;
	}

	public Float getScore() {
		if(entries.size() == 0) {
			return MAX_SCORE;
		}
		
		Float score = MIN_SCORE;
		
		for(ReportEntry e : entries) {
			if(e.isSuccess()) {
				score++;
			}
		}
		
		return score * MAX_SCORE / ((float) entries.size());
	}
	
	public boolean addEntry(ReportEntry entry) {
		return entries.add(entry);
	}
	
	public List<ReportEntry> getEntries() {
		return entries;
	}
	
	public String getSuggestion() {
		return suggestion;
	}
	
	public Rule getRule() {
		return rule;
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
	
	public String[] getColumns() {
		return rule.getRuleReportFormat();
	}
}
