package olaf.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CSVUtils {
	private static final char DEFAULT_SEPERATOR = ',';
	
	public static void writeLine(File file, List<String> values) throws IOException {
		writeLine(file, values, DEFAULT_SEPERATOR, ' ');
	}
	
	public static void writeLine(File file, List<String> values, char seperators) throws IOException {
		writeLine(file, values, seperators, ' ');
	}
	
	public static void writeLine(File file, List<String> values, char seperators, char customQuote) throws IOException {
		if (seperators == ' ') {
			seperators = DEFAULT_SEPERATOR;
		}
		
		boolean first = true;
		StringBuilder sb = new StringBuilder();
		for (String value: values) {
			if (!first) {
				sb.append(seperators);
			}
			
			if (customQuote == ' ') {
				sb.append(followCSVFormat(value));
			} else {
				sb.append(customQuote).append(value).append(customQuote);
			}
			
			first = false;
		}		
		try (FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(sb.toString());
		}
	}
	
	private static String followCSVFormat(String value) {
		String result = value;
		if (result.contains("\'")) {
			result = result.replace("\"", "\"\"");
		}
		return result;
	}
}
