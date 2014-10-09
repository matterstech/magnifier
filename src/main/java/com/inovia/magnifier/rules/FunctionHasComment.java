package com.inovia.magnifier.rules;

import java.util.*;

import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

public abstract class FunctionHasComment {
	public static Report runOn(Vector<Function> functions, Vector<Comment> comments) {
		Report report = new Report();
		
		for(Function f : functions) {
			Boolean isSuccess = assertion(f, comments);
			report.addEntry(new ReportEntry(f, isSuccess));
		}
		
		return report;
	}
	
	private static Boolean assertion(Function function, Vector<Comment> comments) {
		for(Comment c : comments) {
			if(c.getSchemaName().equals(function.getSchemaName()) && c.getEntityName().equals(function.getName())) {
				return true;
			}
		}
		
		return false;
	}
}