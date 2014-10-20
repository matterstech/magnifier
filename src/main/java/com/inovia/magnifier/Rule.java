package com.inovia.magnifier;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.reports.*;

/**
 * The Rule abstract class deals with
 * the expected behaviour of rules.
 */
public abstract class Rule {
	protected Ruleset ruleset;
	
	public Rule(Ruleset ruleset) {
		this.ruleset = ruleset;
	}
	
	final protected Database getDatabase() {
		return ruleset.getDatabase();
	}
	
	abstract public RuleReport run();
}
