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
			String html = "";
			html = html + "<html>"
						+ "  <head>"
						+ "    <style>"
						+ "      table {"
						+ "        width: 100%;"
						+ "        text-align: left;"
						+ "        border: 1px black ridge"
						+ "      }"
						+ "    </style>"
						+ "  </head>"
						+ "  <body>"
						+ "    <h1>" + databaseName + "</h1>"
						+ "    <table>"
						+ "      <thead>"
						+ "        <tr>"
						+ "        <th></th>"
						+ "        <th>Rule</th>"
						+ "        <th>Score</th>"
						+ "        <th>Suggestions</th>"
						+ "        <th>Technical debt</th>"
						+ "        <th>Entity</th>"
						+ "      </tr>"
						+ "    </thead>"
						+ "  <tbody>";
			for(RuleReport rr : ruleReports) {
				html = html + "<tr id=\"" + rr.getRuleName() + "-plus\">";
				if(rr.getScore() < 100F) {
					html = html + "<td><button class=\"fold-button\" id=\"" + rr.getRuleName() + "\"> +/- </button></td>";
				} else {
				    html = html + "<td></td>";
				}
				html = html + "  <td>" + rr.getRuleName() + "</td>"
						    + "  <td>" + rr.getScore().intValue() + "%</td>"
							+ "  <td>" + rr.getSuggestion() + "</td>"
							+ "  <td>" + rr.getDebt() + "</td>"
							+ "  <td>" + "" + "</td>"
							+ "</tr>"
							+ "<tr id=\"" + rr.getRuleName() + "-plus\">"
							+ "  <td colspan=\"5\">";
				if(rr.getScore() < 100F) {
					html = html + "  <table id=\"" + rr.getRuleName() + "-table\" style=\"display: none\">"
							    + "<tbody>";
				}
				for(ReportEntry e : rr.getEntries()) {
					if(!e.isSuccess()) {
						html = html + "<tr data-rule=\"" + rr.getRuleName() + "\">"
								    + "  <td>" + e.getEntityDescription() + "</td>"
								    + "</tr>";
					}
				}
				if(rr.getScore() < 100F) {
					html = html + "</tbody>"
					            + "  </table>";
				}
				html = html + "  </td>"
				        	+ "</tr>";
			}
			html = html + "      </tbody>"
				        + "    </table>"
				        + "    <script>"
				        + "      var t = document.getElementsByClassName('fold-button');"
				        + "      for(var i = t.length-1 ; i >= 0 ; i--) {"
				        + "        t[i].addEventListener('click', function(e) {"
				        + "          element_style = document.getElementById(e.target.id + '-table').style;"
				        + "          if(element_style.display != 'none') {"
				        + "            element_style.display = 'none'"
				        + "          } else if(element_style.display == 'none') {"
				        + "            element_style.display = ''"
				        + "          }"
				        + "        });"
				        + "      }"
				        + "    </script>"
				        + "  </body>"
				        + "<html>";
		
		writer.println(html);
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
