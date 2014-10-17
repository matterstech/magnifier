package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

/**
 * The ViewHasComment class aims at checking if
 * the provided views all have a comment
 */
public abstract class ViewHasComment {
	public static final String RULE_NAME = "ViewHasComment";
	public static final String SUGGESTION = "Each view should have a comment explaining what it does";
	public static final Float DEBT = 1F;
	
	public static RuleReport runOn(Vector<View> views, Vector<Comment> comments) {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);
		
		for(View v : views) {
			Boolean isSuccess = assertion(v, comments);
			ruleReport.addEntry(new ReportEntry(v.getEntityDescription(), isSuccess));
		}
		
		return ruleReport;
	}
	
	private static Boolean assertion(View view, Vector<Comment> comments) {
		for(Comment c : comments) {
			if(c.getSchemaName().equals(view.getSchemaName()) && c.getEntityName().equals(view.getName())) {
				return true;
			}
		}
		
		return false;
	}
}
