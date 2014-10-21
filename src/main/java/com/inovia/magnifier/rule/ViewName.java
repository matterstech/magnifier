package com.inovia.magnifier.rule;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.View;
import com.inovia.magnifier.reports.ReportEntry;
import com.inovia.magnifier.reports.RuleReport;

/**
 * The ViewName class aims at checking if the provided
 * view names have the right format
 */
public class ViewName implements Rule {
	public static final String RULE_NAME = "ViewName";
	public static final String SUGGESTION = "Each view should have a name ending with _view";
	public static final Float DEBT = 1F;
	
	private static final String SUFFIX = "_view";

	public ViewName() { }
	
	public RuleReport run(Database database) {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);

		for(View v : database.getViews()) {
			Boolean isSuccess = assertion(v);
			ruleReport.addEntry(new ReportEntry(v.getEntityDescription(), isSuccess));
		}

		return ruleReport;
	}

	private Boolean assertion(View view) {
		if(view.getName().endsWith(SUFFIX)) {
			return true;
		}

		return false;
	}
}
