package com.inovia.magnifier.rule;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.Trigger;
import com.inovia.magnifier.reports.ReportEntry;
import com.inovia.magnifier.reports.RuleReport;

/**
 * The TriggerName class aims at checking if the provided
 * trigger names have the right format
 */
public class TriggerName implements Rule {
	public static final String RULE_NAME = "TriggerName";
	public static final String SUGGESTION = "Each trigger name should match on_when_what_tablename (example: on_before_update_user)";
	public static final Float DEBT = 1F;

	public TriggerName() { }
	
	public RuleReport run(Database database) {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);

		for(Trigger t : database.getTriggers()) {
			Boolean isSuccess = assertion(t);
			ruleReport.addEntry(new ReportEntry(t.getEntityDescription(), isSuccess));
		}

		return ruleReport;
	}

	private Boolean assertion(Trigger t) {
		return t.getName().equalsIgnoreCase("on_" + t.getTiming() + "_" + t.getAction() + "_" + t.getTableName());
	}
}
