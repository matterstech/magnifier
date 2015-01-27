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
	public static final String SUGGESTION = "Each table should have a comment";
	public static final Float DEBT = 1F;
	public static final String[] FORMAT = {"schema", "table"};
	public static final String[] LINKS = {"table"};
	
	private RuleReport ruleReport = null;

	public TableHasComment() { }

	public RuleReport run(Database database) {
		ruleReport = new RuleReport(this, SUGGESTION, DEBT);
		
		for(Table t : database.getTables()) {
			Boolean isSuccess = assertion(t, database.getComments());
			String[] dataToDisplay = {t.getSchemaName(), t.getName()};
			ruleReport.addEntry(new ReportEntry((Object) t, dataToDisplay, isSuccess));
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
	
	public String[] getRuleReportFormat() {
		return FORMAT;
	}
	
	public String[] getLinks() {
		return LINKS;
	}

	public String getName() {
		return RULE_NAME;
	}
	
	public String getSuggestion() {
		return SUGGESTION;
	}
	
	public RuleReport getRuleReport() {
		return ruleReport;
	}

	public String getSolution(Object object) {
		Table table = (Table) object;
		return "COMMENT ON TABLE " + table.getName() + " IS 'your comment';";
	}
}
