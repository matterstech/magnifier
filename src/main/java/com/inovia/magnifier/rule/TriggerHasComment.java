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
	public static final Float DEBT = 0.15F;
	public static final String SUGGESTION = "Each trigger should have a comment";
	public static final String[] FORMAT = {"schema", "trigger"};
	public static final String[] LINKS = {};
	
	private RuleReport ruleReport = null;

	public TriggerHasComment() { }

	public RuleReport run(Database database) {
		ruleReport = new RuleReport(this, SUGGESTION, DEBT);
		
		for(Trigger t : database.getTriggers()) {
			Boolean isSuccess = assertion(t, database.getComments());
			String[] dataToDisplay = {t.getSchemaName(), t.getName()};
			ruleReport.addEntry(new ReportEntry(t, dataToDisplay, isSuccess));
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
		Trigger trigger = (Trigger) object;
		return "COMMENT ON TRIGGER " + trigger.getName() + " IS 'your comment';";
	}
}
