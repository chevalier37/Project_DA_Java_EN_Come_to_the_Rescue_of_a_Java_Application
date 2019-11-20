package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Read symptoms from data file and print result to text file
 *
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String readFilePath;
	private String writeFilePath;
	private Map<String, Integer> listSymptoms;
	private List<String> readSymptoms = new ArrayList<String>();

	/**
	 * 
	 * @param readFilePath  a full path to file with symptom strings in it, one per
	 *                      line
	 * @param writeFilePath a full path to file which result can be print
	 * @author Jean-Benoît ROUSSAT
	 * @version 1.0
	 */
	public ReadSymptomDataFromFile(String readFilePath, String writeFilePath) {
		this.readFilePath = readFilePath;
		this.writeFilePath = writeFilePath;
	}

	@Override
	public List<String> GetSymptoms() {
		try {
			readSymptoms();
			mapSymptoms();
			writeSymptoms();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return readSymptoms;
	}

	/**
	 * Read symptoms from data file and put result to a TreeMap
	 * 
	 * @author Jean-Benoît ROUSSAT
	 * @version 1.0
	 * @throws IOException
	 */
	private void readSymptoms() throws IOException {

		if (readFilePath != null) {

			try (BufferedReader reader = new BufferedReader(new FileReader(readFilePath))) {
				String line = reader.readLine();

				while (line != null) {
					readSymptoms.add(line);
					line = reader.readLine();
				}
			}

		}
	}

	/**
	 * Put symptoms into a TreeMap
	 * 
	 * @author Jean-Benoît ROUSSAT
	 * @version 1.0
	 */

	private void mapSymptoms() {
		listSymptoms = new TreeMap<String, Integer>();
		for (int i = 0; i < readSymptoms.size(); i++) {
			int count = 0;
			for (int k = 0; k < readSymptoms.size(); k++) {
				if (readSymptoms.get(i).equals(readSymptoms.get(k))) {
					count++;
					listSymptoms.put(readSymptoms.get(i), count);
				}
			}
		}
	}

	/**
	 * Write symptoms to result.out file
	 * 
	 * @author Jean-Benoît ROUSSAT
	 * @version 1.0
	 * @throws IOException
	 */
	private void writeSymptoms() throws IOException {

		try (FileWriter writer = new FileWriter(this.writeFilePath)) {
			writer.write("Symptoms list : " + "\n");
			for (Map.Entry<String, Integer> element : listSymptoms.entrySet()) {
				writer.write(element.getKey() + " : " + element.getValue() + "\n");
			}
		}
	}

}
