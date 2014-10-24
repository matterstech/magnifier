package com.inovia.magnifier;

import org.apache.commons.cli.*;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.rule.*;

/**
 * The Magnifier class contains Magnifier's main method.
 */
public class Magnifier {
  public static void main(String[] args) {
    Configuration configuration = new Configuration(args);
    try {
      if(!configuration.parseCommandLine()) {
        System.exit(1);
      }
    } catch(ParseException e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
    Database database = DatabaseFactory.getDatabase(
        configuration.getDriverPath(),
        configuration.getDatabaseType(),
        configuration.getHost(),
        configuration.getPort(),
        configuration.getDatabaseName(),
        configuration.getUser(),
        configuration.getPassword());

    // Bootstrap
    database.connect();
    database.load();

    Ruleset inoviaRuleset = new InoviaRuleset(database);
    inoviaRuleset.run();
    inoviaRuleset.generateHtml(configuration.getReportPath());
    
    database.disconnect();
  }
}
