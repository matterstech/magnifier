package com.inovia.magnifier.rule;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.Index;
import com.inovia.magnifier.reports.ReportEntry;
import com.inovia.magnifier.reports.RuleReport;

/**
 * The IndexName class aims at checking if the provided
 * index names have the right format
 */
public class IndexName implements Rule {
	public static final String RULE_NAME = "IndexName";
	public static final String SUGGESTION = "Each index should have a name ending with _idx";
	public static final Float DEBT = 1F;
	public static final String[] FORMAT = {"schema", "table", "index"};
	public static final String[] LINKS = {"table"};
	public static final String[] SUFFIXES = {"idx", "pkey", "key", "primary", "pk", "fkey", "fk", "naturalkey", "unique"};
	
	private RuleReport ruleReport = null;

	private static final String SUFFIX_SEPARATOR = "_";
	
	public IndexName() { }

	public RuleReport run(Database database) {
		ruleReport = new RuleReport(this, SUGGESTION, DEBT);

		for(Index i : database.getIndexes()) {
			Boolean isSuccess = assertion(i);
			String[] dataToDisplay = {i.getSchemaName(), i.getTableName(), i.getName()};
			ruleReport.addEntry(new ReportEntry(i, dataToDisplay, isSuccess));
		}

		return ruleReport;
	}

	private Boolean assertion(Index index) {
		for(Integer i = 0 ; i < SUFFIXES.length ; i++) {
			if(index.getName().endsWith(SUFFIX_SEPARATOR + SUFFIXES[i])) {
				return true;
			}
		}
		
		return false;
	}
	
	public String[] getRuleReportFormat() {
		return FORMAT;
	}

	public String getName() {
		return RULE_NAME;
	}
	
	public String[] getLinks() {
		return LINKS;
	}
	
	public String getSuggestion() {
		return SUGGESTION;
	}
	
	public RuleReport getRuleReport() {
		return ruleReport;
	}

	public String getSolution(Object object) {
		Index index = (Index) object;
		if(SUFFIXES.length > 0) {
			return "ALTER INDEX " + index.getName() + " RENAME TO " + index.getName() + SUFFIXES[0] + ";";
		} else {
			return "";
		}
	}
}
