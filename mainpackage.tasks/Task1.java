package mainpackage.tasks;

import java.util.ArrayList;
import java.util.LinkedList;

import mainpackage.customer.PermCustomer;

public class Task1 {
	
	public static ArrayList<Integer> getInts(PermCustomer permCustomer) {
		ArrayList<Integer> requests = new ArrayList<Integer>();
		requests.add(permCustomer.getMonReq());
		requests.add(permCustomer.getTueReq());
		requests.add(permCustomer.getWedReq());
		requests.add(permCustomer.getThuReq());
		requests.add(permCustomer.getFriReq());
		requests.add(permCustomer.getSatReq());
		requests.add(permCustomer.getSunReq());
		return requests;
	}
	
	public static double calculateAverage(PermCustomer permCustomer) {
		ArrayList<Integer> requests = getInts(permCustomer);
		int value = 0;
		for (int i = 0; i <= 6; i++) {
			value += requests.get(i);
		}
		return value/7.0;
	}
	
	public static double calculateCovariance(PermCustomer permCustomer) {
		ArrayList<Integer> requests = getInts(permCustomer);
		double value = -24.5*calculateAverage(permCustomer);
		for (int i = 0; i <= 6; i++) {
			value += (requests.get(i)*(i+0.5));
		}
		return value/6.0;
	}
	
	public static double calculateReqVariance(PermCustomer permCustomer) {
		ArrayList<Integer> requests = getInts(permCustomer);
		int value = 0;
		for (int i = 0; i <= 6; i++) {
			value += (requests.get(i)-calculateAverage(permCustomer))*(requests.get(i)-calculateAverage(permCustomer));
		}
		return value/6.0;
	}
	
	public static double calculateCorrCoef(PermCustomer permCustomer) {
		return calculateCovariance(permCustomer)/Math.sqrt(14*calculateReqVariance(permCustomer)/3);
	}
	
	public static double calculateSlope(PermCustomer permCustomer) {
		return 3*calculateCovariance(permCustomer)/14;
	}
	
	public static double calculateYIntercept(PermCustomer permCustomer) {
		return calculateAverage(permCustomer)-3.5*calculateSlope(permCustomer);
	}
	
	public static String displayDay(int i) {
		switch(i) {
		case 0:
			return "On Monday";
		case 1:
			return "On Tuesday";
		case 2:
			return "On Wednesday";
		case 3:
			return "On Thursday";
		case 4:
			return "On Friday";
		case 5:
			return "On Saturday";
		case 6:
			return "On Sunday";
		}
		return null;
	}
	
	public static double calculateRelativePercentError(int i, PermCustomer permCustomer) {
		ArrayList<Integer> requests = getInts(permCustomer);
		return 100*Math.abs(requests.get(i)-Math.round((i+0.5)*calculateSlope(permCustomer)+calculateYIntercept(permCustomer)))/requests.get(i);
	}
	
	public static LinkedList<String> displayAnalytics(PermCustomer permCustomer) {
		LinkedList<String> messages = new LinkedList<>();
		ArrayList<Integer> requests = getInts(permCustomer);
		for (int i = 0; i <= 6; i++) {
			StringBuilder stringBuilder = new StringBuilder(displayDay(i));
			stringBuilder.append(" there were "+requests.get(i)+" requests");
			stringBuilder.append(" which are expected to be "+Math.round((i+0.5)*calculateSlope(permCustomer)+calculateYIntercept(permCustomer)));
			stringBuilder.append(" with a relative percent error of "+calculateRelativePercentError(i, permCustomer));
			String result = stringBuilder.toString();
			messages.add(result);
		}
		if(calculateCorrCoef(permCustomer) > 0) {
			messages.add("Relationship is positive with a correlation coefficient of "+calculateCorrCoef(permCustomer));
		} else {
			messages.add("Relationship is negative with a correlation coefficient of "+calculateCorrCoef(permCustomer));
		}
		return messages;
	}
	
}