package mainpackage.tasks;

import java.util.ArrayList;
import java.util.Collections;

import mainpackage.customer.PermCustomer;

public class Task2 {
	
	public static ArrayList<Integer> predictReqs(PermCustomer permCustomer) {
		ArrayList<Double> monthlyreqs = new ArrayList<Double>();
		monthlyreqs.add((double) permCustomer.getMonReq());
		monthlyreqs.add((double) permCustomer.getTueReq());
		monthlyreqs.add((double) permCustomer.getWedReq());
		monthlyreqs.add((double) permCustomer.getThuReq());
		monthlyreqs.add((double) permCustomer.getFriReq());
		monthlyreqs.add((double) permCustomer.getSatReq());
		monthlyreqs.add((double) permCustomer.getSunReq());
		ArrayList<Double> weeklyreqs = new ArrayList<Double>();
		weeklyreqs.add((double) permCustomer.getMonReq());
		weeklyreqs.add((double) permCustomer.getTueReq());
		weeklyreqs.add((double) permCustomer.getWedReq());
		weeklyreqs.add((double) permCustomer.getThuReq());
		weeklyreqs.add((double) permCustomer.getFriReq());
		weeklyreqs.add((double) permCustomer.getSatReq());
		weeklyreqs.add((double) permCustomer.getSunReq());
		int pastslopesign = 0;
		for(int i = 7; i <= 27; i++) {
			if((int)Math.round(weeklyreqs.get(6)) == (int)Math.round(calculateAverage(weeklyreqs)+((weeklyreqs.size()+1)/2)*calculateSlope(weeklyreqs))) {
				if(pastslopesign != (int)Math.signum(calculateSlope(weeklyreqs))) {
					if(0.0>calculateAverage(monthlyreqs)+((monthlyreqs.size()+1)/2.0)*calculateSlope(monthlyreqs)) {
						monthlyreqs.add(0.0);
					}else if(2.0*calculateAverage(monthlyreqs)<calculateAverage(monthlyreqs)+((monthlyreqs.size()+1)/2.0)*calculateSlope(monthlyreqs)) {
						monthlyreqs.add(2.0*calculateAverage(monthlyreqs));
					}else {
						if((int)Math.round(((monthlyreqs.size()+1)/2.0)*calculateSlope(monthlyreqs))==0) {
							monthlyreqs.add(calculateValue(monthlyreqs));
						} else {
							monthlyreqs.add(calculateAverage(monthlyreqs)+((monthlyreqs.size()+1)/2.0)*calculateSlope(monthlyreqs));
						}
					}
				} else {
					if(0.0>calculateAverage(monthlyreqs)-((monthlyreqs.size()+1)/2.0)*calculateSlope(monthlyreqs)) {
						monthlyreqs.add(0.0);
					}else if(2.0*calculateAverage(monthlyreqs)<calculateAverage(monthlyreqs)-((monthlyreqs.size()+1)/2.0)*calculateSlope(monthlyreqs)) {
						monthlyreqs.add(2.0*calculateAverage(monthlyreqs));
					}else {
						if((int)Math.round(((monthlyreqs.size()+1)/2.0)*calculateSlope(monthlyreqs))==0) {
							monthlyreqs.add(calculateValue(monthlyreqs));
						} else {
							monthlyreqs.add(calculateAverage(monthlyreqs)-((monthlyreqs.size()+1)/2.0)*calculateSlope(monthlyreqs));
						}
					}
				}
				pastslopesign = (int)Math.signum(calculateSlope(weeklyreqs));
				weeklyreqs.remove(0);
				weeklyreqs.add(monthlyreqs.get(monthlyreqs.size()-1));
			}
			else {
				if(pastslopesign != (int)Math.signum(calculateSlope(weeklyreqs))) {
					if(0.0>calculateAverage(weeklyreqs)+((weeklyreqs.size()+1)/2)*calculateSlope(weeklyreqs) ) {
						monthlyreqs.add(0.0);
					}else if(2.0*calculateAverage(weeklyreqs)<calculateAverage(weeklyreqs)+((weeklyreqs.size()+1)/2)*calculateSlope(weeklyreqs)) {
						monthlyreqs.add(2.0*calculateAverage(weeklyreqs));
					} else {
						if((int)Math.round(((weeklyreqs.size()+1)/2)*calculateSlope(weeklyreqs))==0) {
							monthlyreqs.add(calculateValue(weeklyreqs));
						} else {
							monthlyreqs.add(calculateAverage(weeklyreqs)+((weeklyreqs.size()+1)/2)*calculateSlope(weeklyreqs));
						}
					}
				} else {
					if(0.0>calculateAverage(weeklyreqs)-((weeklyreqs.size()+1)/2)*calculateSlope(weeklyreqs) ) {
						monthlyreqs.add(0.0);
					}else if(2.0*calculateAverage(weeklyreqs)<calculateAverage(weeklyreqs)-((weeklyreqs.size()+1)/2)*calculateSlope(weeklyreqs)) {
						monthlyreqs.add(2.0*calculateAverage(weeklyreqs));
					} else {
						if((int)Math.round(((weeklyreqs.size()+1)/2)*calculateSlope(weeklyreqs))==0) {
							monthlyreqs.add(calculateValue(weeklyreqs));
						} else {
							monthlyreqs.add(calculateAverage(weeklyreqs)-((weeklyreqs.size()+1)/2)*calculateSlope(weeklyreqs));
						}
					}
				}
				pastslopesign = (int)Math.signum(calculateSlope(weeklyreqs));
				weeklyreqs.remove(0);
				weeklyreqs.add(monthlyreqs.get(monthlyreqs.size()-1));
			}
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i <= 27; i++) {
			result.add((int) Math.round(monthlyreqs.get(i)));
		}
		return result;
	}
	public static double calculateMedian(ArrayList<Double> reqs) {
		ArrayList<Double> tempreqs = new ArrayList<Double>();
		for(int i = 0; i < reqs.size(); i++) {
			tempreqs.add(reqs.get(i));
		}
		Collections.sort(tempreqs);
		if(tempreqs.size() % 2 == 0) {
			return (tempreqs.get(tempreqs.size()/2 - 1)+tempreqs.get(tempreqs.size()/2))/2.0;
		}
		return tempreqs.get((tempreqs.size()-1)/2);
	}
	public static double calculateValue(ArrayList<Double> reqs) {
		ArrayList<Double> tempreqs = new ArrayList<Double>();
		for(int i = 0; i < reqs.size(); i++) {
			tempreqs.add(reqs.get(i));
		}
		Collections.sort(tempreqs);
		if(calculateMedian(reqs)-tempreqs.get(0)<tempreqs.get(tempreqs.size()-1)-calculateMedian(reqs)) {
			return tempreqs.get(tempreqs.size()-1);
		}
		return tempreqs.get(0);
	}
	public static double calculateAverage(ArrayList<Double> reqs) {
		int value = 0;
		for (int i = 0; i < reqs.size(); i++) {
			value += reqs.get(i);
		}
		return 1.0*value/reqs.size();
	}
	public static double calculateSlope(ArrayList<Double> reqs) {
		int value = 0;
		if(reqs.size() % 2 == 1) {
			for (int i = 0; i <= (reqs.size()-1)/2-1; i++) {
				value += calculateAverage(reqs)-reqs.get(i);
			}
			for (int i = (reqs.size()-1)/2+1; i <= reqs.size()-1; i++) {
				value -= calculateAverage(reqs)-reqs.get(i);
			}
			return 4.0*value/((reqs.size()-1)*(reqs.size()+1));
		}
		else {
			for (int i = 0; i <= reqs.size()/2-1; i++) {
				value += calculateAverage(reqs)-reqs.get(i);
			}
			for (int i = reqs.size()/2; i <= reqs.size()-1; i++) {
				value -= calculateAverage(reqs)-reqs.get(i);
			}
			return 4.0*value/(reqs.size()*reqs.size());
		}
	}
}
