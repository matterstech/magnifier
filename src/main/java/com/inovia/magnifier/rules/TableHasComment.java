package com.inovia.magnifier.rules;

import java.util.List;

import com.inovia.magnifier.*;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

/**
 * The TableHasComment class aims at checking if
 * the provided tables all have a comment
 */
public class TableHasComment extends Rule {
	public static final String RULE_NAME = "TableHasComment";
	public static final String SUGGESTION = "Each table should have a comment explaining what it contains";
	public static final Float DEBT = 1F;

	public TableHasComment(Ruleset ruleset) {
		super(ruleset);
	}
	
	public RuleReport run() {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);
		
		for(Table t : getDatabase().getTables()) {
			Boolean isSuccess = assertion(t, getDatabase().getComments());
			ruleReport.addEntry(new ReportEntry(t.getEntityDescription(), isSuccess));
		}
		
		return ruleReport;
	}
	
	private Boolean assertion(Table table, List<Comment> comments) {
		for(Comment c : comments) {
			if(c.getEntityType().equals("table") && c.getSchemaName().equals(table.getSchemaName()) && c.getEntityName().equals(table.getName())) {
				return true;
			}
		}
		
		return false;
	}
}
