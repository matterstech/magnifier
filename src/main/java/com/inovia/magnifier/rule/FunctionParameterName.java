package com.inovia.magnifier.rule;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.Function;
import com.inovia.magnifier.database.FunctionParameter;
import com.inovia.magnifier.reports.ReportEntry;
import com.inovia.magnifier.reports.RuleReport;

/**
 * The FunctionParameterName class aims at checking if the provided
 * function parameter names have the right format
 */
public class FunctionParameterName implements Rule {
	public static final String RULE_NAME = "FunctionParameterName";
	public static final String SUGGESTION = "Each parameter should have its name ending with \"_IN\", or \"_OUT\", or whatever mode it is";
	public static final Float DEBT = 1F;
	public static final String[] FORMAT = {"schema", "function", "parameter", "IN/OUT"};

	public FunctionParameterName() { }

	public RuleReport run(Database database) {
		RuleReport ruleReport = new RuleReport(this, SUGGESTION, DEBT);
		
		for(Function f : database.getFunctions()) {
			for(FunctionParameter p : f.getParameters()) {
				RuleResult result = assertion(p);
				String[] dataToDisplay = {f.getSchemaName(), f.getName(), p.getName() == null ? "<noname>" : p.getName(), p.getMode()};
				ruleReport.addEntry(new ReportEntry(dataToDisplay, result));
			}
		}
		
		return ruleReport;
	}
	
	private RuleResult assertion(FunctionParameter p) {
		Boolean isSuccess = p.getName() != null
						&& !p.getName().isEmpty()
						&& p.getName().endsWith("_" + p.getMode().toLowerCase());
		return new RuleResult(isSuccess, null);
	}
	
	public String[] getRuleReportFormat() {
		return FORMAT;
	}

	public String getName() {
		return RULE_NAME;
	}
}
