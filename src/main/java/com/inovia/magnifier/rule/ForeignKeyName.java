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
	public static final String SUGGESTION = "Each foreign key should refer to the foreign column";
	public static final Float DEBT = 1F;
	public static final String[] FORMAT = {"schema", "table", "column", "referenced schema", "referenced table", "referenced column"};
	public static final String[] LINKS = {"table"};
	
	private RuleReport ruleReport = null;
	
	public ForeignKeyName() { }

	public RuleReport run(Database database) {
		ruleReport = new RuleReport(this, SUGGESTION, DEBT);

		for(Table t : database.getTables()) {
			for(ForeignKey fk : t.getForeignKeys()) {
				Boolean isSuccess = assertion(fk);
				String[] dataToDisplay = {t.getSchemaName(), t.getName(), fk.getColumnName(), fk.getForeignSchemaName(), fk.getForeignTableName(), fk.getForeignColumnName()};
				ruleReport.addEntry(new ReportEntry(fk, dataToDisplay, isSuccess));
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
	
	public RuleReport getRuleReport() {
		return ruleReport;
	}

	public String getSolution(Object object) {
		ForeignKey fk = (ForeignKey) object;
		return "ALTER TABLE "
				+ fk.getTable().getName()
				+ " RENAME " + fk.getColumnName() +
				" TO " + fk.getColumnName() + "_" + fk.getForeignTableName() + "_" + fk.getForeignColumnName() + ";";
	}
}
