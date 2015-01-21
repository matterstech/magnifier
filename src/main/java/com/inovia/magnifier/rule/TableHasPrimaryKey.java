package com.inovia.magnifier.rule;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.Table;
import com.inovia.magnifier.reports.ReportEntry;
import com.inovia.magnifier.reports.RuleReport;

/**
 * Check if all tables have a primary key
 */
public class TableHasPrimaryKey implements Rule {
	public static final String RULE_NAME = "TableHasPrimaryKey";
	public static final String SUGGESTION = "Each table should have a primary key";
	public static final Float DEBT = 1F;
	public static final String[] FORMAT = {"schema", "table"};

	public TableHasPrimaryKey() { }

	public RuleReport run(Database database) {
		RuleReport ruleReport = new RuleReport(this, SUGGESTION, DEBT);
		
		for(Table t : database.getTables()) {
			RuleResult result = assertion(t);
			String[] dataToDisplay = {t.getSchemaName(), t.getName()};
			ruleReport.addEntry(new ReportEntry(dataToDisplay, result));
		}
		
		return ruleReport;
	}
	
	private RuleResult assertion(Table table) {
		return new RuleResult(table.hasPrimaryKey(), null);
	}
	
	public String[] getRuleReportFormat() {
		return FORMAT;
	}

	public String getName() {
		return RULE_NAME;
	}
}
