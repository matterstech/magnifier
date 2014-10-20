package com.inovia.magnifier.rules;

import java.util.List;

import com.inovia.magnifier.*;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

/**
 * The ViewHasComment class aims at checking if
 * the provided views all have a comment
 */
public class ViewHasComment extends Rule {
	public static final String RULE_NAME = "ViewHasComment";
	public static final String SUGGESTION = "Each view should have a comment explaining what it does";
	public static final Float DEBT = 1F;

	public ViewHasComment(Ruleset ruleset) {
		super(ruleset);
	}
	
	public RuleReport run() {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);
		
		for(View v : getDatabase().getViews()) {
			Boolean isSuccess = assertion(v, getDatabase().getComments());
			ruleReport.addEntry(new ReportEntry(v.getEntityDescription(), isSuccess));
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
}
