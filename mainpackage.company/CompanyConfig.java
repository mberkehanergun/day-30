package mainpackage.company;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CompanyConfig {
	
	private static int costOfTask1PerHour = 7;
    private static int costOfTask2PerHour = 9;
    private static int costOfTask3PerHour = 8;
    private static int IANUM = 1;
    private static int TCLNUM = 1;
    private static int DAYNUM = 1;
    private static int REPNUM = 1;
    private static int hourOfTask1 = 2;
    private static int hourOfTask2 = 3;
    private static int hourOfTask3 = 4;

    public static int getIANUM() {
        return IANUM;
    }

    public static void incrementAndSaveIANUMInDatabase(JdbcTemplate jdbcTemplate) {
        try {
            String updateQuery = "UPDATE COMPANYCONFIG SET IANUM = ?";
            jdbcTemplate.update(updateQuery, ++IANUM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeIANUMInDatabase(JdbcTemplate jdbcTemplate, int newValue) {
        try {
        	IANUM = newValue;
            String updateQuery = "UPDATE COMPANYCONFIG SET IANUM = ?";
            jdbcTemplate.update(updateQuery, IANUM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int getTCLNUM() {
        return TCLNUM;
    }
    
    public static void incrementAndSaveTCLNUMInDatabase(JdbcTemplate jdbcTemplate) {
        try {
            String updateQuery = "UPDATE COMPANYCONFIG SET TCLNUM = ?";
            jdbcTemplate.update(updateQuery, ++TCLNUM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeTCLNUMInDatabase(JdbcTemplate jdbcTemplate, int newValue) {
        try {
        	TCLNUM = newValue;
            String updateQuery = "UPDATE COMPANYCONFIG SET TCLNUM = ?";
            jdbcTemplate.update(updateQuery, TCLNUM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int getDAYNUM() {
        return DAYNUM;
    }
    
    public static void incrementAndSaveDAYNUMInDatabase(JdbcTemplate jdbcTemplate) {
        try {
            String updateQuery = "UPDATE COMPANYCONFIG SET DAYNUM = ?";
            jdbcTemplate.update(updateQuery, ++DAYNUM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeDAYNUMInDatabase(JdbcTemplate jdbcTemplate, int newValue) {
        try {
        	DAYNUM = newValue;
            String updateQuery = "UPDATE COMPANYCONFIG SET DAYNUM = ?";
            jdbcTemplate.update(updateQuery, DAYNUM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int getREPNUM() {
        return REPNUM;
    }
    
    public static void incrementAndSaveREPNUMInDatabase(JdbcTemplate jdbcTemplate) {
        try {
            String updateQuery = "UPDATE COMPANYCONFIG SET REPNUM = ?";
            jdbcTemplate.update(updateQuery, ++REPNUM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeREPNUMInDatabase(JdbcTemplate jdbcTemplate, int newValue) {
        try {
        	REPNUM = newValue;
            String updateQuery = "UPDATE COMPANYCONFIG SET REPNUM = ?";
            jdbcTemplate.update(updateQuery, REPNUM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int getCostOfTask1PerHour() {
		return costOfTask1PerHour;
	}

	public static int getCostOfTask2PerHour() {
		return costOfTask2PerHour;
	}

	public static int getCostOfTask3PerHour() {
		return costOfTask3PerHour;
	}
	
	public static int getHourOfTask1() {
		return hourOfTask1;
	}

	public static int getHourOfTask2() {
		return hourOfTask2;
	}

	public static int getHourOfTask3() {
		return hourOfTask3;
	}
}