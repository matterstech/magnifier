package com.inovia.magnifier.rule;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.Trigger;
import com.inovia.magnifier.reports.ReportEntry;
import com.inovia.magnifier.reports.RuleReport;

/**
 * Checks if the trigger names follow the convention
 */
public class TriggerName implements Rule {
	public static final String RULE_NAME = "TriggerName";
	public static final String SUGGESTION = "Each trigger name should match on_when_what_tablename (example: on_before_update_user)";
	public static final Float DEBT = 1F;
	public static final String[] FORMAT = {"schema", "table", "trigger", "timing", "action"};
	public static final String[] LINKS = {"table"};

	public TriggerName() { }

	public RuleReport run(Database database) {
		RuleReport ruleReport = new RuleReport(this, SUGGESTION, DEBT);

		for(Trigger t : database.getTriggers()) {
			Boolean isSuccess = assertion(t);
			String[] dataToDisplay = {t.getSchemaName(), t.getTableName(), t.getName(), t.getTiming(), t.getAction()};
			ruleReport.addEntry(new ReportEntry(t, dataToDisplay, isSuccess));
		}

		return ruleReport;
	}

	private Boolean assertion(Trigger t) {
		return t.getName().equalsIgnoreCase("on_" + t.getTiming() + "_" + t.getAction() + "_" + t.getTableName());
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
}
