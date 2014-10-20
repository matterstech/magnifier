package com.inovia.magnifier;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.reports.*;

/**
 * The Rule abstract class deals with
 * the expected behaviour of rules.
 */
public abstract class Rule {
	abstract public RuleReport run(Database database);
}
