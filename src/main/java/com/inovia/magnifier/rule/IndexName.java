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
	public static final String[] EXAMPLE = {
		"an index name should end with _idx",
		"\"my_index\" is not valid",
		"\"user_id_idx\" is valid"
	};
	
	public IndexName() { }

	public RuleReport run(Database database) {
		RuleReport ruleReport = new RuleReport(this, SUGGESTION, DEBT);

		for(Index i : database.getIndexes()) {
			Boolean isSuccess = assertion(i);
			String[] dataToDisplay = {i.getSchemaName(), i.getTableName(), i.getName()};
			ruleReport.addEntry(new ReportEntry(dataToDisplay, isSuccess));
		}

		return ruleReport;
	}

	private Boolean assertion(Index i) {
		if(i.getName().endsWith("_idx")) {
			// Inovia convention
			return true;
		} else if(i.getName().endsWith("_pkey") || i.getName().endsWith("_key")) {
			// Postgres Convention
			return true;
		}
		return false;
	}
	
	public String[] getRuleReportFormat() {
		return FORMAT;
	}

	public String getName() {
		return RULE_NAME;
	}
	
	public String[] getExample() {
		return EXAMPLE;
	}
}
