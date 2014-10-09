package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

public class FunctionParameterName {
	public static Report runOn(Vector<Function> functions) {
		Report report = new Report();
		
		for(Function f : functions) {
			for(FunctionParameter p : f.getParameters()) {
				Boolean isSuccess = assertion(p);
				report.addEntry(new ReportEntry(p, isSuccess));
			}
		}
		
		return report;
	}
	
	private static Boolean assertion(FunctionParameter p) {
		return p.getName() != null && !p.getName().isEmpty() && p.getName().endsWith("_" + p.getMode().toLowerCase());
	}
}
