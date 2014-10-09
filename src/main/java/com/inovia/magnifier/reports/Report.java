package com.inovia.magnifier.reports;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

public class Report {
	private Vector<ReportEntry> entries;
	private Integer score;
	
	public Report() {
		entries = new Vector<ReportEntry>();
	}
	
	public void addEntry(ReportEntry entry) {
		entries.add(entry);
	}
	
	public Integer getScore() {
		if(score == null) {
			Integer successCount = 0;
			for(ReportEntry entry : entries) {
				if(entry.isSuccess()) {
					successCount++;
				}
			}
			
			score = successCount * 100 / entries.size(); 
		}
		
		return score;
	}
	
	public String toString() {
		return getScore() + "%";
	}
	
	public Vector<ReportEntry> getEntries() {
		return entries;
	}
	
	public void generateHtml() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("/tmp/report.html", "UTF-8");
			
			writer.println("<html>");
			writer.println("  <body>");
			writer.println("    <table>");
			writer.println("      <thead>");
			writer.println("        <tr>");
			writer.println("          <th>Entity name</th>");
			writer.println("          <th>Passed the test</th>");
			writer.println("        </tr>");
			writer.println("      </thead>");
			writer.println("      <tbody>");
			for(ReportEntry e : entries) {
				writer.println("        <tr>");
				writer.println("          <td>" + e.getEntity().getEntityDescription() + "</td>");
				writer.println("          <td>" + e.isSuccess() + "</td>");
				writer.println("        </tr>");
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
}
