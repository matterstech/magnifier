package com.inovia.magnifier.rules;

import com.inovia.magnifier.Rule;
import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

/**
 * The FunctionParameterName class aims at checking if the provided
 * function parameter names have the right format
 */
public class FunctionParameterName extends Rule {
	public static final String RULE_NAME = "FunctionParameterName";
	public static final String SUGGESTION = "Each parameter should have its name ending with \"_IN\", or \"_OUT\", or whatever mode it is";
	public static final Float DEBT = 1F;

	public FunctionParameterName(Database database) {
		super(database);
	}
	
	public RuleReport run() {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);
		
		for(Function f : database.getFunctions()) {
			for(FunctionParameter p : f.getParameters()) {
				Boolean isSuccess = assertion(p);
				ruleReport.addEntry(new ReportEntry(p.getEntityDescription(), isSuccess));
			}
		}
		
		return ruleReport;
	}
	
	private Boolean assertion(FunctionParameter p) {
		return p.getName() != null && !p.getName().isEmpty() && p.getName().endsWith("_" + p.getMode().toLowerCase());
	}
}
