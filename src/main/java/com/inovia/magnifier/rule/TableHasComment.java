package com.inovia.magnifier.rule;

import java.util.List;

import com.inovia.magnifier.database.Comment;
import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.Table;
import com.inovia.magnifier.reports.ReportEntry;
import com.inovia.magnifier.reports.RuleReport;

/**
 * Checks if all tables have a comment
 */
public class TableHasComment implements Rule {
	public static final String RULE_NAME = "TableHasComment";
	public static final String SUGGESTION = "Each table should have a comment explaining what it contains";
	public static final Float DEBT = 0.15F;
	public static final String[] FORMAT = {"schema", "table"};

	public TableHasComment() { }

	public Boolean hasSuggestions() { return false; }
	
	public RuleReport run(Database database) {
		RuleReport ruleReport = new RuleReport(this, SUGGESTION, DEBT);
		
		for(Table t : database.getTables()) {
			RuleResult result = assertion(t, database.getComments());
			String[] dataToDisplay = {t.getSchemaName(), t.getName()};
			ruleReport.addEntry(new ReportEntry(dataToDisplay, result));
		}
		
		return ruleReport;
	}
	
	private RuleResult assertion(Table table, List<Comment> comments) {
		for(Comment c : comments) {
			if(c.getEntityType().equals("table") && c.getSchemaName().equals(table.getSchemaName()) && c.getEntityName().equals(table.getName())) {
				return new RuleResult(true, null);
			}
		}
		
		return new RuleResult(false, null);
	}
	
	public String[] getRuleReportFormat() {
		return FORMAT;
	}

	public String getName() {
		return RULE_NAME;
	}
}
