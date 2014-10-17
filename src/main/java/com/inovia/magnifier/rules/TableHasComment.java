package com.inovia.magnifier.rules;

import java.util.*;

import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

public abstract class TableHasComment {
	public static final String RULE_NAME = "TableHasComment";
	public static final String SUGGESTION = "Each table should have a comment explaining what it contains";
	public static final Float DEBT = 1F;
	
	public static RuleReport runOn(Vector<Table> tables, Vector<Comment> comments) {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);
		
		for(Table t : tables) {
			Boolean isSuccess = assertion(t, comments);
			ruleReport.addEntry(new ReportEntry(t.getEntityDescription(), isSuccess));
		}
		
		return ruleReport;
	}
	
	private static Boolean assertion(Table table, Vector<Comment> comments) {
		for(Comment c : comments) {
			if(c.getEntityType().equals("table") && c.getSchemaName().equals(table.getSchemaName()) && c.getEntityName().equals(table.getName())) {
				return true;
			}
		}
		
		return false;
	}
}