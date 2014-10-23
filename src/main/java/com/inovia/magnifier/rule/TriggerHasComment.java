package com.inovia.magnifier.rule;

import java.util.List;

import com.inovia.magnifier.database.Comment;
import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.Trigger;
import com.inovia.magnifier.reports.ReportEntry;
import com.inovia.magnifier.reports.RuleReport;

/**
 * Checks if triggers all have a comment
 */
public class TriggerHasComment implements Rule {
	public static final String RULE_NAME = "TriggerHasComment";
	public static final String SUGGESTION = "Each trigger should have a comment explaining what it contains";
	public static final Float DEBT = 1F;

	public TriggerHasComment() { }
	
	public RuleReport run(Database database) {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);
		
		for(Trigger t : database.getTriggers()) {
			Boolean isSuccess = assertion(t, database.getComments());
			ruleReport.addEntry(new ReportEntry(t.getEntityDescription(), isSuccess));
		}
		
		return ruleReport;
	}
	
	private Boolean assertion(Trigger trigger, List<Comment> comments) {
		for(Comment c : comments) {
			if(c.getEntityType().equals("trigger") && c.getSchemaName().equals(trigger.getSchemaName()) && c.getEntityName().equals(trigger.getName())) {
				return true;
			}
		}
		
		return false;
	}
}
