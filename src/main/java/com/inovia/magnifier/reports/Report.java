package com.inovia.magnifier.reports;

import com.inovia.magnifier.*;
import com.inovia.magnifier.database.*;
import com.inovia.magnifier.rule.Rule;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * it is a report on a specific database.
 * It includes a set of rule reports
 */
public class Report {
	private final static String ENCODING = "UTF-8";

	private final static Integer HOURS_PER_DAY           = 24;
	private final static Integer MINUTES_PER_HOUR        = 60;
	private final static Integer SECONDS_PER_MINUTE      = 60;
	private final static Integer MILLISECONDS_PER_SECOND = 1000;
	private final static String  DEFAULT_DIRECTORY = "magnifier_report/";
	private final static String  DEFAULT_REPORT = "index.html";

	private String databaseName;
	private List<RuleReport> ruleReports;

	public Report(String databaseName) {
		ruleReports = new Vector<RuleReport>();
		this.databaseName = databaseName;
	}

	/**
	 * generates an Html report
	 * 
	 * @param reportFilePath the report file to generate
	 * @param startTime      the time when the database analysis started
	 * @param endTime        the time when the database analysis ended
	 */
	public void generateHtml(String reportFilePath, Date startTime, Date endTime) {
		Collections.sort(ruleReports, new Comparator<RuleReport>() {
			public int compare(RuleReport r1, RuleReport r2) {
				if(r1.getEntries().size() == 0) {
					return 1;
				} else if(r2.getEntries().size() == 0) {
					return -1;
				}
				
				if(r1.getScore() <= r2.getScore()) {
					if(r1.getDebt() >= r2.getDebt()) {
						return -1;
					} else {
						return 1;
					}
				}

				return 1;
			}
		});
		
		Map<String, List<Object[]>> detailPages = new HashMap<String, List<Object[]>>();
		
		PrintWriter writer = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			Long timeDiff = endTime.getTime() - startTime.getTime();
			Long seconds  = timeDiff / MILLISECONDS_PER_SECOND;
			Long minutes  = seconds  / SECONDS_PER_MINUTE;
			Long hours    = minutes  / MINUTES_PER_HOUR;
			Long days     = hours    / HOURS_PER_DAY;

			String html = "";
			html = html + "<html>"
					+ "  <head>"
					+ "    <link rel=\"stylesheet\" type=\"text/css\" href=\"./stylesheets/index.css\">"
					+ "  </head>"
					+ "  <body>"
					+ "    <h1>Magnifier Report on \"" + databaseName + "\"</h1>"
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
				html = html + "<tr class=\"rule-header\" id=\"" + rr.getRule().getName() + "-plus\">";
				if(rr.getEntries().size() > 0 && rr.getScore() < 100F) {
					html = html + "<td><button title=\"fold/unfold\" class=\"fold-button\" id=\"" + rr.getRule().getName() + "\"> +/- </button></td>";
				} else {
					html = html + "<td></td>";
				}
				html = html + "  <td " + ( rr.getScore() == null ? "" : "title=\"" + rr.getScore().intValue() + "% of " + rr.getEntries().size() + " entities\"" ) + " class=\"metric " + (rr.getScore() == null || rr.getScore().intValue() == 100 ? "good-metric" : rr.getScore().intValue() == 0 ? "bad-metric" : "normal-metric") + "\">" + (rr.getScore() == null ? "N/A" : rr.getScore().intValue() + "%" ) + "</td>"
						+ "  <td class=\"debt\" title=\"" + rr.getDebt() + " hours to correct (< " + (new Float(rr.getDebt() / 7).intValue() + 1) + " days)\">" + (rr.getDebt() != 0.0 ? rr.getDebt() : "") + "</td>"
						+ "  <td>" + rr.getRule().getName() + "</td>"
						+ "  <td class=\"description\">" + rr.getSuggestion() + "</td>"
						+ "</tr>";
				if(rr.getEntries().size() > 0 && rr.getScore() < 100F) {
					html = html
							+ "<tr id=\"" + rr.getRule().getName() + "-plus\">"
							+ "  <td colspan=\"5\">"
							+ "    <table id=\"" + rr.getRule().getName() + "-table\" class=\"rule-entries\" style=\"display: none;\">"
							+ "        <thead>"
							+ "          <tr>";

					for(String column : rr.getColumns()) {
						html = html + "<th>" + column + "</th>";
					}

					html = html
							+ "        </tr>"
							+ "      </thead>";

					for(ReportEntry e : rr.getEntries()) {

						html = html
								+ "<tbody>";

						if(!e.isSuccess()) {
							html = html
									+ "<tr>";
							Integer i = 0;
							for(String data : e.getDataToDisplay()) {
								if(Arrays.asList(rr.getRule().getLinks()).contains(rr.getColumns()[i])) {
									html = html
											+ "<td><a href=\"./details/" + data + ".html\">" + data + "</a></td>";
									
									Object[] problem = new Object[2];
									problem[0] = rr.getRule();  // the rule that checked the object
									problem[1] = e.getObject(); // the database object that was checked
									
									if(detailPages.containsKey(data)) { // already in the collection
										List<Object[]> list = detailPages.get(data);
										list.add(problem);
										
										detailPages.put(data, list);
									} else {
										List<Object[]> list = new ArrayList<Object[]>();
										list.add(problem);
										
										detailPages.put(data, list);
									}
									
								} else {
									html = html
											+ "<td>" + data + "</td>";
								}
								
								i++;
							}
							html = html
									+ "</tr>";
						}
						html = html
								+ "</tbody>";
					}

					html = html
							+ "    </table>"
							+ "  </td>"
							+ "</tr>";
				}
			}
			html = html + "      </tbody>"
					+ "    </table>"
					+ "  </body>"
					+ "  <script type=\"text/javascript\" src=\"./scripts/index.js\"></script>"
					+ "<html>";

			String rootDirectory = reportFilePath + DEFAULT_DIRECTORY; // /users_choice/magnifier_report
			String indexFile = rootDirectory + DEFAULT_REPORT;         // /users_choice/magnifier_report/index.html
			
			File f = new File(rootDirectory);
			if(!f.exists()) { f.mkdirs(); }
			if(!f.isDirectory()) { System.err.println("A file already exists with name \"" + rootDirectory + "\""); }

			f = new File(indexFile);
			writer = new PrintWriter(f, ENCODING);
			writer.println(html);

			try {
				File images = new File(rootDirectory + "images");
				File stylesheets = new File(rootDirectory + "stylesheets");
				File scripts = new File(rootDirectory + "scripts");
				File details = new File(rootDirectory + "details");
				
				if(!images.exists()) { images.mkdir(); }
				if(!images.isDirectory()) { System.err.println("Couldn't load assets: " + rootDirectory); }
				
				if(!stylesheets.exists()) { stylesheets.mkdir(); }
				if(!stylesheets.isDirectory()) { System.err.println("Couldn't load assets: " + rootDirectory); }
				
				if(!scripts.exists()) { scripts.mkdir(); }
				if(!scripts.isDirectory()) { System.err.println("Couldn't load assets: " + rootDirectory); }
				
				if(!details.exists()) { details.mkdir(); }
				if(!details.isDirectory()) { System.err.println("Couldn't load details: " + rootDirectory); }
				
				Magnifier.exportResource("/images/minus_plus.ico", rootDirectory + "images/minus_plus.ico");
				Magnifier.exportResource("/stylesheets/index.css", rootDirectory + "stylesheets/index.css");
				Magnifier.exportResource("/scripts/index.js",      rootDirectory + "scripts/index.js");
				
				for(String key : detailPages.keySet()) {
					String detailsDirectory = rootDirectory + "details/";
					File detailFile = new File(detailsDirectory + key + ".html");
					PrintWriter detailWriter = new PrintWriter(detailFile, ENCODING);
					
					generateDetails(detailWriter, detailPages.get(key));
					
					detailWriter.close();
				}
			} catch (Exception e) {
				System.err.println("Couldn't load assets in (2): " + rootDirectory); 
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.err.println("File couldn't be found: " + reportFilePath);
		} catch (UnsupportedEncodingException e) {
			System.err.println("Unsupported Encoding: " + ENCODING);
		} finally {
			if(writer != null) {
				writer.close();
			}
		}
	}
	
	private void generateDetails(Writer writer, List<Object[]> problems) throws IOException {
		if(problems.size() == 0) { return; }
		
		String objectName = "";
		Object object = problems.get(0)[1];
		Class<? extends Object> c = object.getClass();
		
		if(c == Table.class) {
			objectName = ((Table) object).getName();
		} else if(c == Index.class) {
			objectName = ((Index) object).getTableName();
		} else if(c == Index.class) {
			objectName = ((ForeignKey) object).getTable().getName();
		} else if(c == View.class) {
			objectName = ((View) object).getName();
		} else if(c == Trigger.class) {
			objectName = ((Trigger) object).getName();
		} else if(c == Function.class) {
			objectName = ((Function) object).getName();
		}
		
		String html = ""
				+ "<html>"
				+ "  <head>"
				+ "    <link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheets/index.css\">"
				+ "  </head>"
				+ "  <body>"
				+ "    <h1>" + objectName + "</h1>"
				+ "      <div>";
		if(c == Table.class) {
			html = html
					+ ((Table) object).getDetails();
		} else if(c == Function.class) {
			html = html
					+ ((Function) object).getDetails();
		}
		html = html
				+ "      </div>"
				+ "    <table>"
				+ "      <thead>"
				+ "        <tr><th>Rule</th><th>Suggestion</th><th>Solution</th></tr>"
				+ "      </thead>"
				+ "      <tbody>";
		
		for(Object[] problem : problems) {
			Rule rule = (Rule) problem[0];
			object = problem[1];
			
			if(object.getClass() == Table.class) {
				html = html + getTableRow(rule, (Table) object);
			} else if(object.getClass() == View.class) { 
				html = html + getViewRow(rule, (View) object);
			} else if(object.getClass() == Trigger.class) {
				html = html + getTriggerRow(rule, (Trigger) object);
			} else if(object.getClass() == Function.class) {
				html = html + getFunctionRow(rule, (Function) object);
			} else if(object.getClass() == Index.class) {
				html = html + getIndexRow(rule, (Index) object);
			} else if(object.getClass() == ForeignKey.class) {
				html = html + getForeignKeyRow(rule, (ForeignKey) object);
			}
		}
		
		html = html
				+ "      </tbody>"
				+ "    </table>"
				+ "  </body>"
				+ "</html>";
		
		writer.write(html);
	}
	
	private String getTableRow(Rule rule, Table table) {
		return ""
				+ "<tr>"
				+ "  <td>" + rule.getName() + "</td>"
				+ "  <td>" + rule.getSuggestion() + "</td>"
				+ "  <td>" + rule.getSolution(table) + "</td>"
				+ "</tr>";
	}
	
	private String getViewRow(Rule rule, View view) {
		return ""
				+ "<tr>"
				+ "  <td>" + rule.getName() + "</td>"
				+ "  <td>" + rule.getSuggestion() + "</td>"
				+ "  <td>" + rule.getSolution(view) + "</td>"
				+ "</tr>";
	}
	
	private String getTriggerRow(Rule rule, Trigger trigger) {
		return ""
				+ "<tr>"
				+ "  <td>" + rule.getName() + "</td>"
				+ "  <td>" + rule.getSuggestion() + "</td>"
				+ "  <td>" + rule.getSolution(trigger) + "</td>"
				+ "</tr>";
	}
	
	private String getFunctionRow(Rule rule, Function function) {
		return ""
				+ "<tr>"
				+ "  <td>" + rule.getName() + "</td>"
				+ "  <td>" + rule.getSuggestion() + "</td>"
				+ "  <td>" + rule.getSolution(function) + "</td>"
				+ "</tr>";
	}
	
	private String getIndexRow(Rule rule, Index index) {
		return ""
				+ "<tr>"
				+ "  <td>" + rule.getName() + "</td>"
				+ "  <td>" + rule.getSuggestion() + "</td>"
				+ "  <td>" + rule.getSolution(index) + "</td>"
				+ "</tr>";
	}
	
	private String getForeignKeyRow(Rule rule, ForeignKey fk) {
		return ""
				+ "<tr>"
				+ "  <td>" + rule.getName() + "</td>"
				+ "  <td>" + rule.getSuggestion() + "</td>"
				+ "  <td>" + rule.getSolution(fk) + "</td>"
				+ "</tr>";
	}

	/**
	 * add a rule report to the report
	 * 
	 * @param ruleReport
	 */
	public void addRuleReport(RuleReport ruleReport) {
		ruleReports.add(ruleReport);
	}

	public List<RuleReport> getRuleReports() {
		return ruleReports;
	}
}
