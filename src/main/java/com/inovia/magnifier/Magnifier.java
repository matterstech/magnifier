package com.inovia.magnifier;

import java.io.*;

import com.inovia.magnifier.rule.*;
import com.inovia.magnifier.database.*;

/**
 * The Magnifier class contains Magnifier's main method.
 */
public class Magnifier {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.overrideWithIni(new File(Configuration.DEFAULT_INI));
		cfg.overrideWithCommandLine(args);

		if(cfg.isHelp()) {
			cfg.printHelp();
			return;
		}

		if(!cfg.isValid()) {
			cfg.printHelp();
			return;
		}

		//Creating a session to the database
		Database database = DatabaseFactory.getDatabase(cfg.getDatabaseName(), cfg.getDatabaseType());
		if(!database.connect(cfg.getDriverFile(), cfg.getHost(), cfg.getPort(), cfg.getUser(), cfg.getPassword(), cfg.getConnectionURL())) {
			System.err.println("Couldn't connect to the database");
			return;
		}

		// Load all the entities from the database since Magnifier is READ ONLY and don't push or edit data from the database
		database.load();

		Ruleset inoviaRuleset = new InoviaRuleset(database);
		inoviaRuleset.run();
		inoviaRuleset.generateHtml(cfg.getReportPath());

		// Closing the database session
		database.disconnect();
	}

	private Magnifier() { }

	public static String exportResource(String resourceName, String targetPath) throws Exception {
		InputStream stream = null;
		OutputStream resStreamOut = null;
		String jarFolder;
		try {
			stream = Magnifier.class.getResourceAsStream(resourceName);
			if(stream == null) {
				throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
			}

			int readBytes;
			byte[] buffer = new byte[4096];
			jarFolder = new File(Magnifier.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
			resStreamOut = new FileOutputStream(targetPath);
			while ((readBytes = stream.read(buffer)) > 0) {
				resStreamOut.write(buffer, 0, readBytes);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			if(stream != null) { stream.close(); }
			if(resStreamOut != null) { resStreamOut.close(); }
		}

		return jarFolder + resourceName;
	}
}
