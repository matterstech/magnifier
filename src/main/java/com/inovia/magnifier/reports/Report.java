package com.inovia.magnifier.reports;

import java.util.Vector;

public class Report {
	private Vector<ReportEntry> entries;
	private Integer score;
	
	public Report() {
		entries = new Vector<ReportEntry>();
	}
	
	public void addEntry(ReportEntry entry) {
		entries.add(entry);
	}
	
	public Integer getScore() {
		if(score == null) {
			Integer successCount = 0;
			for(ReportEntry entry : entries) {
				if(entry.isSuccess()) {
					successCount++;
				}
			}
			
			score = successCount * 100 / entries.size(); 
		}
		
		return score;
	}
	
	public String toString() {
		return getScore() + "%";
	}
	
	public Vector<ReportEntry> getEntries() {
		return entries;
	}
}
