package mainpackage.customer;

import org.springframework.stereotype.Component;

@Component
public class TempCustomer {
	
	private String nameAndSurname;
	private static int initCharge = 400;
	
	public String getNameAndSurname() {
		return nameAndSurname;
	}
	
	public void setNameAndSurname(String nameAndSurname) {
		this.nameAndSurname = nameAndSurname;
	}

	public static int getInitCharge() {
		return initCharge;
	}

	public TempCustomer() {
		
	}
	
	public TempCustomer(String nameAndSurname) {
		setNameAndSurname(nameAndSurname);
	}
	
}
