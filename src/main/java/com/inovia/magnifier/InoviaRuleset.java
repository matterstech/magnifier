package com.inovia.magnifier;

import java.util.*;

import com.inovia.magnifier.reports.*;

public class InoviaRuleset extends Ruleset {
	public InoviaRuleset(Configuration configuration) {
		super(configuration);
		
		rules = new Vector<Rule>();
		report = new Report(configuration.getDatabaseName());
	}
	
	protected List<Rule> getRules() {
		List<Rule> rules = new Vector<Rule>();
		
		// new TableHasComment();
		
		return rules;
	}
}
