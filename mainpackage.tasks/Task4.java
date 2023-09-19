package mainpackage.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.math3.special.Erf;

import mainpackage.customer.PermCustomer;

public class Task4 {
	
	public static ArrayList<Double> getBinomDist(PermCustomer permCustomer) {
		ArrayList<Double> binomdist = new ArrayList<Double>();
		double value = 1;
		for(int i = 1; i <= 27; i++) {
			value *= binomialRow(28)[i];
			for(int j = 1; j <= 28; j++) {
				value *= 0.5;
			}
			binomdist.add(value);
			value = 1;
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
		for(int i = 1; i <= 27; i++) {
			message.add("Unusuality of requests for "+i+" days being below or above average is ");
			if(Math.abs((i-14)/Math.sqrt(7)) < 2) {
				message.add("not unusual with z-score of "+(i-14)/Math.sqrt(7));
			} else if(Math.abs((i-14)/Math.sqrt(7)) > 2 && Math.abs((i-14)/Math.sqrt(7)) < 3) {
				message.add("somewhat unusual with z-score of "+(i-14)/Math.sqrt(7));
			} else {
				message.add("very unusual (outlier) with z-score of "+(i-14)/Math.sqrt(7));
			}
		}
		ArrayList<Integer> reqs = Task2.predictReqs(permCustomer);
		int value = 0;
		int occurence = 0;
		for(int i = 0; i <= 27; i++) {
			value += reqs.get(i);
		}
		if(value % 28 == 0) {
			value /= 28;
			for(int i = 0; i <= 27; i++) {
				if(value == reqs.get(i)) {
					return message;
				} else if(value > reqs.get(i)) {
					occurence++;
				}
			}
		} else {
			double newvalue = value/28.0;
			for(int i = 0; i <= 27; i++) {
				if(newvalue > reqs.get(i)) {
					occurence++;
				}
			}
		}
		ArrayList<Double> binomdist = getBinomDist(permCustomer);
		message.add("Your requests have "+occurence+" days below average and "+(28-occurence)+" days above average. ");
		message.add("Probability of that is: "+binomdist.get(occurence-1));
		return message;
	}
	
}
