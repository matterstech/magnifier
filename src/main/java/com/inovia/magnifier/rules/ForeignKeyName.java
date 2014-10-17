package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

/**
 * The ForeignKeyName class aims at checking if the provided
 * foreign key names have the right format
 */
public abstract class ForeignKeyName {
	public static final String RULE_NAME = "ForeignKeyName";
	public static final String SUGGESTION = "Each foreign key should have name representing the table and column it references";
	public static final Float DEBT = 1F;

	public static RuleReport runOn(Vector<Table> tables) {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);

		for(Table t : tables) {
			for(ForeignKey fk : t.getForeignKeys()) {
				Boolean isSuccess = assertion(fk);
				ruleReport.addEntry(new ReportEntry(fk.getEntityDescription(), isSuccess));
			}
		}

		return ruleReport;
	}

	private static Boolean assertion(ForeignKey fk) {
		// TODO: What if different schema ?
		if(fk.getColumnName().equals(fk.getForeignTableName() + "_" + fk.getForeignColumnName())) {
			return true;
		} else if(fk.getColumnName().endsWith("_" + fk.getForeignTableName() + "_" + fk.getForeignColumnName())) {
			return true;
		}

		return false;
	}
}
