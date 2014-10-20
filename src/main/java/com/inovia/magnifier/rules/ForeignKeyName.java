package com.inovia.magnifier.rules;

import com.inovia.magnifier.Rule;
import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

/**
 * The ForeignKeyName class aims at checking if the provided
 * foreign key names have the right format
 */
public class ForeignKeyName extends Rule {
	public static final String RULE_NAME = "ForeignKeyName";
	public static final String SUGGESTION = "Each foreign key should have name representing the table and column it references";
	public static final Float DEBT = 1F;

	public ForeignKeyName(Database database) {
		super(database);
	}
	
	public RuleReport run() {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);

		for(Table t : database.getTables()) {
			for(ForeignKey fk : t.getForeignKeys()) {
				Boolean isSuccess = assertion(fk);
				ruleReport.addEntry(new ReportEntry(fk.getEntityDescription(), isSuccess));
			}
		}

		return ruleReport;
	}

	private Boolean assertion(ForeignKey fk) {
		// TODO: What if different schema ?
		if(fk.getColumnName().equals(fk.getForeignTableName() + "_" + fk.getForeignColumnName())) {
			return true;
		} else if(fk.getColumnName().endsWith("_" + fk.getForeignTableName() + "_" + fk.getForeignColumnName())) {
			return true;
		}

		return false;
	}

	public String getHelp() {
		return RULE_NAME + ": " + SUGGESTION;
	}
}
