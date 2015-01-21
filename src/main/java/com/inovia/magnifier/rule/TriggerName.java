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

	public TriggerName() { }

	public Boolean hasSuggestions() { return false; }
	
	public RuleReport run(Database database) {
		RuleReport ruleReport = new RuleReport(this, SUGGESTION, DEBT);

		for(Trigger t : database.getTriggers()) {
			RuleResult result = assertion(t);
			String[] dataToDisplay = {t.getSchemaName(), t.getTableName(), t.getName(), t.getTiming(), t.getAction()};
			ruleReport.addEntry(new ReportEntry(dataToDisplay, result));
		}

		return ruleReport;
	}

	private RuleResult assertion(Trigger t) {
		Boolean isSuccess = t.getName().equalsIgnoreCase("on_" + t.getTiming() + "_" + t.getAction() + "_" + t.getTableName());
		
		return new RuleResult(isSuccess, null);
	}
	
	public String[] getRuleReportFormat() {
		return FORMAT;
	}

	public String getName() {
		return RULE_NAME;
	}
}
