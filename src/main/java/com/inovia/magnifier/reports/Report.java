package com.inovia.magnifier.reports;

import java.io.*;
import java.util.*;

public class Report {
	private Vector<RuleReport> ruleReports;

	public Report() {
		ruleReports = new Vector<RuleReport>();
	}

	public void generateHtml(String path) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(path, "UTF-8");

			writer.println("<html>");
			writer.println("  <body>");
			writer.println("    <table width=\"100%\" border=\"1\" style=\"text-align: left;\">");
			writer.println("      <thead>");
			writer.println("        <tr>");
			writer.println("          <th>Rule</th>");
			writer.println("          <th>Score</th>");
			writer.println("          <th>Entity</th>");
			writer.println("          <th>Suggestions</th>");
			writer.println("          <th>Technical debt</th>");
			writer.println("        </tr>");
			writer.println("      </thead>");
			writer.println("      <tbody>");
			for(RuleReport rr : ruleReports) {
				for(ReportEntry e : rr.getEntries()) {
					if(!e.isSuccess()) {
						writer.println("        <tr>");
						writer.println("          <td>" + rr.getRuleName() + "</td>");
						writer.println("          <td>" + rr.getScore().intValue() + "%</td>");
						writer.println("          <td>" + e.getEntityDescription() + "</td>");
						writer.println("          <td>" + rr.getSuggestion() + "</td>");
						writer.println("          <td>" + rr.getDebt() + "</td>");
						writer.println("        </tr>");
					}
				}
			}
			writer.println("      </tbody>");
			writer.println("    </table>");
			writer.println("  </body>");
			writer.println("</html>");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				writer.close();
			}
		}
	}
	
	public void addRuleReport(RuleReport ruleReport) {
		ruleReports.add(ruleReport);
	}
}
