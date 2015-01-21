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
	public static final String SUGGESTION = "Each view should have a comment explaining what it does";
	public static final Float DEBT = 1F;
	public static final String[] FORMAT = {"schema", "view"};

	public ViewHasComment() { }

	public Boolean hasSuggestions() { return false; }
	
	public RuleReport run(Database database) {
		RuleReport ruleReport = new RuleReport(this, SUGGESTION, DEBT);
		
		for(View v : database.getViews()) {
			RuleResult result = assertion(v, database.getComments());
			String[] dataToDisplay = {v.getSchemaName(), v.getName()};
			ruleReport.addEntry(new ReportEntry(dataToDisplay, result));
		}
		
		return ruleReport;
	}
	
	private RuleResult assertion(View view, List<Comment> comments) {
		for(Comment c : comments) {
			if(c.getSchemaName().equals(view.getSchemaName()) && c.getEntityName().equals(view.getName())) {
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
