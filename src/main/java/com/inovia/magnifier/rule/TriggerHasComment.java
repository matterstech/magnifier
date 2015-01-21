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
	public static final String[] FORMAT = {"schema", "trigger"};

	public TriggerHasComment() { }

	public RuleReport run(Database database) {
		RuleReport ruleReport = new RuleReport(this, SUGGESTION, DEBT);
		
		for(Trigger t : database.getTriggers()) {
			RuleResult result = assertion(t, database.getComments());
			String[] dataToDisplay = {t.getSchemaName(), t.getName()};
			ruleReport.addEntry(new ReportEntry(dataToDisplay, result));
		}
		
		return ruleReport;
	}
	
	private RuleResult assertion(Trigger trigger, List<Comment> comments) {
		for(Comment c : comments) {
			if(c.getEntityType().equals("trigger") && c.getSchemaName().equals(trigger.getSchemaName()) && c.getEntityName().equals(trigger.getName())) {
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
