package com.inovia.magnifier.rules;

import com.inovia.magnifier.Rule;
import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;


public class TriggerName extends Rule {
	public static final String RULE_NAME = "TriggerName";
	public static final String SUGGESTION = "Each trigger name should match on_when_what_tablename (example: on_before_update_user)";
	public static final Float DEBT = 1F;

	public TriggerName(Database database) {
		super(database);
	}
	
	public RuleReport run() {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);

		for(Trigger t : database.getTriggers()) {
			Boolean isSuccess = assertion(t);
			ruleReport.addEntry(new ReportEntry(t.getEntityDescription(), isSuccess));
		}

		return ruleReport;
	}

	private Boolean assertion(Trigger t) {
		final String PATTERN = "on_" + t.getTiming() + "_" + t.getAction() + "_" + t.getTableName();
		return t.getName().equalsIgnoreCase(PATTERN);
	}
}
