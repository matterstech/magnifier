package com.inovia.magnifier.reports;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A report is a report on a specific database.
 * It includes a set of rule reports
 * 
 */
public class Report {
	private final String ENCODING = "UTF-8";

	private String databaseName;
	private List<RuleReport> ruleReports;

	public Report(String databaseName) {
		ruleReports = new Vector<RuleReport>();
		this.databaseName = databaseName;
	}

	public void generateHtml(String path, Date startTime, Date endTime) {
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
			writer = new PrintWriter(path, ENCODING);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			Integer hours_in_day           = 24;
			Integer minutes_in_hour        = 60;
			Integer seconds_in_minute      = 60;
			Integer milliseconds_in_second = 1000;
			
			Long timeDiff = endTime.getTime() - startTime.getTime();
			Long seconds = timeDiff / milliseconds_in_second;
			Long minutes = seconds  / seconds_in_minute;
			Long hours   = minutes  / minutes_in_hour;
			Long days    = hours    / hours_in_day;

			String html = "";
			html = html + "<html>"
					+ "  <head>"
					+ "    <style>"
					+ "      body {"
					+ "        font-family: sans-serif;"
					+ "      }"
					+ "      h1, h4 {"
					+ "        text-align: center;"
					+ "      }"
					+ "      table.rule-table {"
					+ "        width: 98%;"
					+ "        text-align: left;"
					+ "        border: 1px #CCC solid;"
					+ "        padding: 1px;"
					+ "        margin: auto;"
					+ "        border-spacing: 0;"
					+ "      }"
					+ "      table.rule-table tr.rule-header td,"
					+ "      table.rule-table th {"
					+ "        border-bottom: 1px solid #CCC;"
					+ "        padding: 10px 10px 0px 0;"
					+ "      }"
					+ "      table.rule-table th {"
					+ "        background-color: rgb(179, 203, 255);"
					+ "        color: rgb(0, 36, 116);"
					+ "      }"
					+ "      table td {"
					+ "        color: rgb(35, 50, 83)"
					+ "      }"
					+ "      .good-metric {"
					+ "        color: green;"
					+ "      }"
					+ "      .bad-metric {"
					+ "        color: red;"
					+ "      }"
					+ "      .normal-metric {"
					+ "        color: orange;"
					+ "      }"
					+ "      .metric {"
					+ "        font-weight: bold;"
					+ "      }"
					+ "      .fold-button {"
					+ "        background-color: white;"
					+ "        -moz-border-radius: 10px;"
					+ "        -webkit-border-radius: 10px;"
					+ "        border-radius: 10px;"
					+ "      }"
					+ "      .description {"
					+ "        font-style: italic;"
					+ "      }"
					+ "      .debt {"
					+ "        text-align: right;"
					+ "      }"
					+ "      .debt-header {"
					+ "        text-align: right;"
					+ "      }"
					+ "    </style>"
					+ "  </head>"
					+ "  <body>"
					+ "    <h1>Magnifier Report on \"" + databaseName + "</h1>"
					+ "    <h4>Started " + dateFormat.format(startTime) + "</h4>"
					+ "    <h4>Execution time " + (days == 0 ? "" : (days+"d")) + (hours == 0 ? "" : (hours+"h")) + ((minutes == 0 ? "" : (minutes+"m"))) + seconds+"s" + "</h4>"
					+ "    <table class=\"rule-table\">"
					+ "      <thead>"
					+ "        <tr>"
					+ "        <th></th>"
					+ "        <th>Score</th>"
					+ "        <th class=\"debt-header\">Debt</th>"
					+ "        <th>Rule</th>"
					+ "        <th>Suggestions</th>"
					+ "      </tr>"
					+ "    </thead>"
					+ "  <tbody>";

			for(RuleReport rr : ruleReports) {
				html = html + "<tr class=\"rule-header\" id=\"" + rr.getRuleName() + "-plus\">";
				if(rr.getScore() < 100F) {
					html = html + "<td><button title=\"fold/unfold\" class=\"fold-button\" id=\"" + rr.getRuleName() + "\"> +/- </button></td>";
				} else {
					html = html + "<td></td>";
				}
				html = html + "  <td title=\"" + rr.getScore().intValue() + "% of " + rr.getEntries().size() + " entities\" class=\"metric " + (rr.getScore().intValue() == 100 ? "good-metric" : rr.getScore().intValue() == 0 ? "bad-metric" : "normal-metric") + "\">" + rr.getScore().intValue() + "%</td>"
						+ "  <td class=\"debt\" title=\"" + rr.getDebt() + " hours to correct (< " + (new Float(rr.getDebt() / 7).intValue() + 1) + " days)\">" + (rr.getDebt() != 0.0 ? rr.getDebt() : "") + "</td>"
						+ "  <td>" + rr.getRuleName() + "</td>"
						+ "  <td class=\"description\">" + rr.getSuggestion() + "</td>"
						+ "</tr>";
				if(rr.getScore() < 100F) {
					html = html + "<tr id=\"" + rr.getRuleName() + "-plus\">"
							+ "  <td colspan=\"3\">"
							+ "  <td colspan=\"2\">"
							+ "  <table id=\"" + rr.getRuleName() + "-table\" class=\"rule-entries\" style=\"display: none\">"
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
							+ "  </table>"
							+ "  </td>"
							+ "</tr>";
				}
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
			System.err.println("File couldn't be found: " + path);
		} catch (UnsupportedEncodingException e) {
			System.err.println("Unsupported Encoding: " + ENCODING);
		} finally {
			if(writer != null) {
				writer.close();
			}
		}
	}

	public void addRuleReport(RuleReport ruleReport) {
		ruleReports.add(ruleReport);
	}
	
	public List<RuleReport> getRuleReports() {
		return ruleReports;
	}
}
