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
		Collections.sort(ruleReports, new Comparator<RuleReport>() {
			public int compare(RuleReport r1, RuleReport r2) {
				if(r1.getScore() > r2.getScore()) {
					return 1;
				} else if(r1.getScore() < r2.getScore()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(path, "UTF-8");

			writer.println("<html>");
			writer.println("  <head>");
			writer.println("    <style>");
			writer.println("      table {");
			writer.println("        width: 100%;");
			writer.println("        text-align: left;");
			writer.println("        border: 1px black ridge");
			writer.println("      }");
			writer.println("    </style>");
			writer.println("  </head>");
			writer.println("  <body>");
			writer.println("  <h1>" + databaseName + "</h1>");
			writer.println("    <table>");
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
				writer.println("        <tr id=\"" + rr.getRuleName() + "-plus\">");
				if(rr.getScore() < 100F) {
					writer.println("        <td><button class=\"fold-button\" id=\"" + rr.getRuleName() + "\">" + rr.getRuleName() + "</button></td>");
				} else {
				    writer.println("          <td>" + rr.getRuleName() + "</td>");
				}
				writer.println("          <td>" + rr.getScore().intValue() + "%</td>");
				writer.println("          <td>" + rr.getSuggestion() + "</td>");
				writer.println("          <td>" + rr.getDebt() + "</td>");
				writer.println("          <td>" + "" + "</td>");
				writer.println("        </tr>");
				writer.println("        <tr id=\"" + rr.getRuleName() + "-plus\">");
				writer.println("          <td colspan=\"5\">");
				if(rr.getScore() < 100F) {
					writer.println("            <table id=\"" + rr.getRuleName() + "-table\" style=\"display: none\">");
					writer.println("              <tbody>");
				}
				for(ReportEntry e : rr.getEntries()) {
					if(!e.isSuccess()) {
						writer.println("            <tr data-rule=\"" + rr.getRuleName() + "\">");
						writer.println("              <td>" + e.getEntityDescription() + "</td>");
						writer.println("            </tr>");
					}
				}
				if(rr.getScore() < 100F) {
					writer.println("              </tbody>");
					writer.println("            </table>");
				}
				writer.println("          </td>");
				writer.println("        </tr>");
			}
			writer.println("      </tbody>");
			writer.println("    </table>");

			writer.println("    <script>");
			writer.println(       "var t = document.getElementsByClassName('fold-button');");
			writer.println(       "for(var i = t.length-1 ; i >= 0 ; i--) {");
			writer.println("       t[i].addEventListener('click', function(e) {");
			writer.println("       element_style = document.getElementById(e.target.id + '-table').style;");
			writer.println("         if(element_style.display != 'none') {");
			writer.println("           element_style.display = 'none'");
			writer.println("         } else if(element_style.display == 'none') {");
			writer.println("           element_style.display = ''");
			writer.println("         }");
			writer.println("       });");
			writer.println("}");
		writer.println("    </script>");

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
