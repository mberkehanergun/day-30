package mainpackage.tasks;

import java.util.ArrayList;
import java.util.List;

import mainpackage.customer.PermCustomer;

public class Task3 {
	
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
	
	public static ArrayList<Double> getBinomDist(PermCustomer permCustomer) {
		ArrayList<Double> binomdist = new ArrayList<Double>();
		for(int i = 1; i <= 6; i++) {
			binomdist.add(binomialRow(7)[i]*Math.pow(0.5, 7));
		}
		return binomdist;
	}
	
	public static int[] binomialRow(int n) {
        int[] row = new int[n + 1];
        row[0] = 1;
        for (int i = 1; i <= n; i++)
            for (int j = i; j >= 0; j--)
                row[j] = (j == 0 ? 0 : row[j - 1]) + row[j];
        return row;
    }
	
	public static List<String> displayUnusuality(PermCustomer permCustomer) {
		List<String> message = new ArrayList<>();
		for(int i = 1; i <= 6; i++) {
			message.add("Unusuality of requests for "+i+" days being below or above average is ");
			if(Math.abs((2*i-7)/Math.sqrt(7)) < 2) {
				message.add("not unusual with z-score of "+(2*i-7)/Math.sqrt(7));
			} else if(Math.abs((2*i-7)/Math.sqrt(7)) > 2 && Math.abs((2*i-7)/Math.sqrt(7)) < 3) {
				message.add("somewhat unusual with z-score of "+(2*i-7)/Math.sqrt(7));
			} else {
				message.add("very unusual (outlier) with z-score of "+(2*i-7)/Math.sqrt(7));
			}
		}
		ArrayList<Integer> reqs = getInts(permCustomer);
		int value = 0;
		int occurence = 0;
		for(int i = 0; i <= 6; i++) {
			value += reqs.get(i);
		}
		if(value % 7 == 0) {
			value /= 7;
			for(int i = 0; i <= 6; i++) {
				if(value == reqs.get(i)) {
					return null;
				} else if(value > reqs.get(i)) {
					occurence++;
				}
			}
		} else {
			double newvalue = value/7.0;
			for(int i = 0; i <= 6; i++) {
				if(newvalue > reqs.get(i)) {
					occurence++;
				}
			}
		}
		ArrayList<Double> binomdist = getBinomDist(permCustomer);
		message.add("Your requests have "+occurence+" days below average and "+(7-occurence)+" days above average. ");
		message.add("Probability of that is: "+binomdist.get(occurence-1));
		return message;
	}
	
	
}
