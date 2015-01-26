package com.inovia.magnifier.rule;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.ForeignKey;
import com.inovia.magnifier.database.Table;
import com.inovia.magnifier.reports.ReportEntry;
import com.inovia.magnifier.reports.RuleReport;

/**
 * Checks if the foreign key names follow the convention.
 * 
 */
public class ForeignKeyName implements Rule {
	public static final String RULE_NAME = "ForeignKeyName";
	public static final String SUGGESTION = "Each foreign key should have name representing the table and column it references";
	public static final Float DEBT = 1F;
	public static final String[] FORMAT = {"schema", "table", "column", "referenced schema", "referenced table", "referenced column"};
	public static final String[] LINKS = {};
	
	public ForeignKeyName() { }

	public RuleReport run(Database database) {
		RuleReport ruleReport = new RuleReport(this, SUGGESTION, DEBT);

		for(Table t : database.getTables()) {
			for(ForeignKey fk : t.getForeignKeys()) {
				Boolean isSuccess = assertion(fk);
				String[] dataToDisplay = {t.getSchemaName(), t.getName(), fk.getColumnName(), fk.getForeignSchemaName(), fk.getForeignTableName(), fk.getForeignColumnName()};
				ruleReport.addEntry(new ReportEntry(t, dataToDisplay, isSuccess));
			}
		}

		return ruleReport;
	}

	private Boolean assertion(ForeignKey fk) {
		if(fk.getColumnName().equals(fk.getForeignTableName() + "_" + fk.getForeignColumnName())) {
			return true;
		} else if(fk.getColumnName().endsWith("_" + fk.getForeignTableName() + "_" + fk.getForeignColumnName())) {
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
	
	public String[] getLinks() {
		return LINKS;
	}
	
	public String getSuggestion() {
		return SUGGESTION;
	}
}
