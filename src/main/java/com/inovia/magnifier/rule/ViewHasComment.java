package com.inovia.magnifier.rule;

import java.util.List;

import com.inovia.magnifier.database.Comment;
import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.View;
import com.inovia.magnifier.reports.ReportEntry;
import com.inovia.magnifier.reports.RuleReport;

/**
 * Check if all the views have a comment
 */
public class ViewHasComment implements Rule {
	public static final String RULE_NAME = "ViewHasComment";
	public static final Float DEBT = 0.15F;
	public static final String SUGGESTION = "Each view should have a comment";
	public static final String[] FORMAT = {"schema", "view"};
	public static final String[] LINKS = {"view"};
	
	private RuleReport ruleReport = null;

	public ViewHasComment() { }

	public RuleReport run(Database database) {
		ruleReport = new RuleReport(this, SUGGESTION, DEBT);
		
		for(View v : database.getViews()) {
			Boolean isSuccess = assertion(v, database.getComments());
			String[] dataToDisplay = {v.getSchemaName(), v.getName()};
			ruleReport.addEntry(new ReportEntry(v, dataToDisplay, isSuccess));
		}
		
		return ruleReport;
	}
	
	private Boolean assertion(View view, List<Comment> comments) {
		for(Comment c : comments) {
			if(c.getSchemaName().equals(view.getSchemaName()) && c.getEntityName().equals(view.getName())) {
				return true;
			}
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
		View view = (View) object;
		return "COMMENT ON VIEW " + view.getName() + " IS 'your comment';";
	}
}
