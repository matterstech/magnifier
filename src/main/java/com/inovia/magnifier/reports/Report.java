package com.inovia.magnifier.reports;

import java.io.*;
import java.util.*;

public class Report {
	private String databaseName;
	private Vector<RuleReport> ruleReports;

	public Report(String databaseName) {
		ruleReports = new Vector<RuleReport>();
		this.databaseName = databaseName;
	}

	public void generateHtml(String path) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(path, "UTF-8");

			writer.println("<html>");
			writer.println("  <body>");
			writer.println("  <h1>" + databaseName + "</h1>");
			writer.println("    <table width=\"100%\" style=\"text-align: left; border: 1px black ridge\">");
			writer.println("      <thead>");
			writer.println("        <tr>");
			writer.println("          <th>Rule</th>");
			writer.println("          <th>Score</th>");
			writer.println("          <th>Suggestions</th>");
			writer.println("          <th>Technical debt</th>");
			writer.println("          <th>Entity</th>");
			writer.println("        </tr>");
			writer.println("      </thead>");
			writer.println("      <tbody>");
			for(RuleReport rr : ruleReports) {
				if(rr.getEntries().size() > 0) {
					writer.println("        <tr id=\"" + rr.getRuleName() + "-plus\">");
					writer.println("          <td>" + rr.getRuleName() + "</td>");
					writer.println("          <td>" + rr.getScore().intValue() + "%</td>");
					writer.println("          <td>" + rr.getSuggestion() + "</td>");
					writer.println("          <td>" + rr.getDebt() + "</td>");
					writer.println("          <td>" + "" + "</td>");
					writer.println("        </tr>");
					writer.println("        <tr id=\"" + rr.getRuleName() + "-plus\">");
					writer.println("          <td colspan=\"5\">");
					if(rr.getScore() != 100F) {
						writer.println("            <table width=\"100%\" style=\"text-align: left; border: 1px black ridge\">");
						writer.println("              <tbody>");
					}
				}
				for(ReportEntry e : rr.getEntries()) {
					if(!e.isSuccess()) {
						writer.println("            <tr data-rule=\"" + rr.getRuleName() + "\">");
						writer.println("              <td>" + e.getEntityDescription() + "</td>");
						writer.println("            </tr>");
					}
				}
				if(rr.getEntries().size() > 0 && rr.getScore() != 100F) {
					if(rr.getScore() != 100F) {
						writer.println("              </tbody>");
						writer.println("            </table>");
					}
					writer.println("          </td>");
					writer.println("        </tr>");
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
