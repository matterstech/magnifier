package com.inovia.magnifier.rule;

import java.util.List;

import com.inovia.magnifier.database.Comment;
import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.Function;
import com.inovia.magnifier.reports.ReportEntry;
import com.inovia.magnifier.reports.RuleReport;

/**
 * The FunctionHasComment class aims at checking if
 * the provided functions all have a comment
 */
public class FunctionHasComment implements Rule {
	public static final String RULE_NAME = "FunctionHasComment";
	public static final Float DEBT = 0.15F;
	public static final String SUGGESTION = "Each function should have a comment";
	public static final String[] FORMAT = {"schema", "function"};
	public static final String[] LINKS = {"function"};
	
	private RuleReport ruleReport = null;

	public FunctionHasComment() {  }
	
	public RuleReport run(Database database) {
		ruleReport = new RuleReport(this, SUGGESTION, DEBT);
		
		for(Function f : database.getFunctions()) {
			Boolean isSuccess = assertion(f, database.getComments());
			String[] dataToDisplay = {f.getSchemaName(), f.getName()};
			ruleReport.addEntry(new ReportEntry(f, dataToDisplay, isSuccess));
		}
		
		return ruleReport;
	}
	
	private Boolean assertion(Function function, List<Comment> comments) {
		for(Comment c : comments) {
			if(c.getEntityType().equals(Comment.FUNCTION_TYPE)
					&& c.getSchemaName().equals(function.getSchemaName())
					&& c.getEntityName().equals(function.getName())) {
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
		Function function = (Function) object;
		return "COMMENT ON FUNCTION " + function.getName() + " IS 'your comment';";
	}
}
