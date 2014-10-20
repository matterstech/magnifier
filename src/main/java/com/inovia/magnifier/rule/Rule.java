package com.inovia.magnifier.rule;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.reports.RuleReport;

/**
 * The Rule abstract class deals with
 * the expected behaviour of rules.
 */
public interface Rule {
	abstract public RuleReport run(Database database);
}
