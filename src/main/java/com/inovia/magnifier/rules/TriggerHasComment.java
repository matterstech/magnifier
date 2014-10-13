package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

public class TriggerHasComment {
	public static final String RULE_NAME = "TriggerHasComment";
	public static final String SUGGESTION = "Each table should have a comment explaining what it contains";
	public static final Float DEBT = 1F;
	
	public static RuleReport runOn(Vector<Trigger> triggers, Vector<Comment> comments) {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);
		
		for(Trigger t : triggers) {
			Boolean isSuccess = assertion(t, comments);
			ruleReport.addEntry(new ReportEntry(t.getEntityDescription(), isSuccess));
		}
		
		return ruleReport;
	}
	
	private static Boolean assertion(Trigger trigger, Vector<Comment> comments) {
		for(Comment c : comments) {
			if(c.getEntityType().equals("trigger") && c.getSchemaName().equals(trigger.getSchemaName()) && c.getEntityName().equals(trigger.getName())) {
				return true;
			}
		}
		
		return false;
	}
}
