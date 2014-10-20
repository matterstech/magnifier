package com.inovia.magnifier.rules;

import com.inovia.magnifier.Rule;
import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

/**
 * The TableHasComment class aims at checking if
 * the provided tables all have a primary key
 */
public class TableHasPrimaryKey extends Rule {
	public static final String RULE_NAME = "TableHasPrimaryKey";
	public static final String SUGGESTION = "Each table should have a primary key";
	public static final Float DEBT = 1F;

	public TableHasPrimaryKey(Database database) {
		super(database);
	}
	
	public RuleReport run() {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);
		
		for(Table t : database.getTables()) {
			Boolean isSuccess = assertion(t);
			ruleReport.addEntry(new ReportEntry(t.getEntityDescription(), isSuccess));
		}
		
		return ruleReport;
	}
	
	private Boolean assertion(Table table) {
		return table.hasPrimaryKey();
	}

	public String getHelp() {
		return RULE_NAME + ": " + SUGGESTION;
	}
}
