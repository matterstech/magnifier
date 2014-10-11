package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.objects.Comment;
import com.inovia.magnifier.database.objects.Table;
import com.inovia.magnifier.reports.ReportEntry;
import com.inovia.magnifier.reports.RuleReport;

public class TableHasPrimaryKey {
	public static final String RULE_NAME = "TableHasPrimaryKey";
	public static final String SUGGESTION = "Each table should have a primary key";
	public static final Float DEBT = 1F;
	
	public static RuleReport runOn(Vector<Table> tables, Vector<Comment> comments) {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);
		
		for(Table t : tables) {
			Boolean isSuccess = assertion(t);
			ruleReport.addEntry(new ReportEntry(t.getEntityDescription(), isSuccess));
		}
		
		return ruleReport;
	}
	
	private static Boolean assertion(Table table) {
		return table.hasPrimaryKey();
	}
}
