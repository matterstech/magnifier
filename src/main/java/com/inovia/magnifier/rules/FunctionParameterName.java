package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

/**
 * The FunctionParameterName class aims at checking if the provided
 * function parameter names have the right format
 */
public abstract class FunctionParameterName {
	public static final String RULE_NAME = "FunctionParameterName";
	public static final String SUGGESTION = "Each parameters should have its name ending with \"_IN\", or \"_OUT\", or whatever mode it is";
	public static final Float DEBT = 1F;
	
	public static RuleReport runOn(Vector<Function> functions) {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);
		
		for(Function f : functions) {
			for(FunctionParameter p : f.getParameters()) {
				Boolean isSuccess = assertion(p);
				ruleReport.addEntry(new ReportEntry(p.getEntityDescription(), isSuccess));
			}
		}
		
		return ruleReport;
	}
	
	private static Boolean assertion(FunctionParameter p) {
		return p.getName() != null && !p.getName().isEmpty() && p.getName().endsWith("_" + p.getMode().toLowerCase());
	}
}
