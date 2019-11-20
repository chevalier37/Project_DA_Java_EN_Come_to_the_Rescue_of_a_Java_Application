package com.hemebiotech.analytics;

public class AnalyticsCounter {

	public static void main(String args[]) {

		ISymptomReader readSymptoms = new ReadSymptomDataFromFile("./Project02Eclipse/symptoms.txt",
				"./Project02Eclipse/result.out");
		readSymptoms.GetSymptoms();

	}
}
