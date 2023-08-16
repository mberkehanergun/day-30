package mainpackage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mainpackage.company.CompanyConfig;
import mainpackage.customer.PermCustomer;
import mainpackage.customer.TempCustomer;
import mainpackage.tasks.Task1;
import mainpackage.tasks.Task2;
import mainpackage.tasks.Task3;
import mainpackage.tasks.Task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

@Component
public class NamedParamJdbcDaoImpl extends NamedParameterJdbcDaoSupport {
	
	DataSource dataSource = getDataSource();
	
	@Autowired
	private PermCustomer permCustomer;
	
	public void task4Display(TextArea taskTextArea) {
		List<String> messages = Task4.displayUnusuality(permCustomer);
		for (String s : messages) {
    		taskTextArea.appendText("\n"+s);
        }
	}
	
	public void task3Display(TextArea taskTextArea) {
		List<String> messages = Task3.displayUnusuality(permCustomer);
		for (String s : messages) {
    		taskTextArea.appendText("\n"+s);
        }
	}
	
	public void prediction(TextField taskTextField) {
		ArrayList<Integer> ints = Task2.predictReqs(permCustomer);
		for (int i : ints) {
    		taskTextField.appendText(i+", ");
        }
	}
	
	public LinkedList<String> report() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		LinkedList<String> messages = Task1.displayAnalytics(permCustomer);
		messages.addFirst("Permanent customer "+permCustomer.getNameAndSurname()+" with report number "+CompanyConfig.getREPNUM()+" has this report:");
    	CompanyConfig.incrementAndSaveREPNUMInDatabase(jdbcTemplate);
    	return messages;
    }
	
	public List<String> displayPermanentCustomer() {
		List<String> messages = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String selectQuery = "SELECT * FROM PERMCUSTOMER";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            int i = 1;
            while (resultSet.next()) {
                String nameAndSurname = resultSet.getString("NAMEANDSURNAME");
                PermCustomer permCustomer = createPermCustomerFromResultSet(resultSet, dataSource);
                messages.add(displayCustomerTasks(nameAndSurname, permCustomer, resultSet, i));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    private PermCustomer createPermCustomerFromResultSet(ResultSet resultSet, DataSource dataSource) {
        PermCustomer permCustomer = new PermCustomer();
        try {
			permCustomer.setNameAndSurname(resultSet.getString("NAMEANDSURNAME"));
			permCustomer.setTask1(resultSet.getString("TASK1"));
	        permCustomer.setTask2(resultSet.getString("TASK2"));
	        permCustomer.setTask3(resultSet.getString("TASK3"));
	        permCustomer.setTask4(resultSet.getString("TASK4"));
	        permCustomer.setTask5(resultSet.getString("TASK5"));
	        permCustomer.setTask6(resultSet.getString("TASK6"));
	        permCustomer.setTask7(resultSet.getString("TASK7"));
	        permCustomer.setTask8(resultSet.getString("TASK8"));
	        permCustomer.setTask9(resultSet.getString("TASK9"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return permCustomer;
    }
    
    private PermCustomer createPermCustomerFromDatabase(int i) {
    	PermCustomer permCustomer = new PermCustomer();
    	try (Connection connection = dataSource.getConnection()) {
            String selectQuery = "SELECT * FROM PERMCUSTOMER WHERE NAMEANDSURNAME = ?";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, "NameAndSurname"+String.valueOf(i));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	permCustomer.setNameAndSurname(resultSet.getString("NAMEANDSURNAME"));
    			permCustomer.setTask1(resultSet.getString("TASK1"));
    	        permCustomer.setTask2(resultSet.getString("TASK2"));
    	        permCustomer.setTask3(resultSet.getString("TASK3"));
    	        permCustomer.setTask4(resultSet.getString("TASK4"));
    	        permCustomer.setTask5(resultSet.getString("TASK5"));
    	        permCustomer.setTask6(resultSet.getString("TASK6"));
    	        permCustomer.setTask7(resultSet.getString("TASK7"));
    	        permCustomer.setTask8(resultSet.getString("TASK8"));
    	        permCustomer.setTask9(resultSet.getString("TASK9"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	return permCustomer;
    }

    private String displayCustomerTasks(String nameAndSurname, PermCustomer permCustomer, ResultSet resultSet, int i) {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String result = "Permanent customer " + nameAndSurname + " at day number " + CompanyConfig.getDAYNUM() + " has tasks " + fetchedTasks(permCustomer, resultSet, i);
        CompanyConfig.incrementAndSaveDAYNUMInDatabase(jdbcTemplate);
        return result;
    }
	
    public List<String> fetchedTasks(PermCustomer permCustomer, ResultSet resultSet, int i) {
        int remhour = 10;
        String temp = permCustomer.getTask1();
        List<String> tasks = new ArrayList<>();
        if (temp.equals(permCustomer.getTask2())) {
            if (temp.equals("Task1")) {
                remhour -= CompanyConfig.getHourOfTask1() - 1;
            } else if (temp.equals("Task2")) {
                remhour -= CompanyConfig.getHourOfTask2() - 1;
            } else if (temp.equals("Task3")) {
                remhour -= CompanyConfig.getHourOfTask3() - 1;
            } else {
                System.out.println("Invalid task");
                return tasks;
            }
        } else {
            if (temp.equals("Task1")) {
                remhour -= CompanyConfig.getHourOfTask1();
            } else if (temp.equals("Task2")) {
                remhour -= CompanyConfig.getHourOfTask2();
            } else if (temp.equals("Task3")) {
                remhour -= CompanyConfig.getHourOfTask3();
            } else {
                System.out.println("Invalid task");
                return tasks;
            }
        }
        while (remhour >= 0) {
            tasks.add(temp);
            permCustomer.updateTasksInDatabase(dataSource, i);
            permCustomer = createPermCustomerFromDatabase(i);
            temp = permCustomer.getTask1();
            if (temp != null && temp.equals(permCustomer.getTask2())) {
                if (temp.equals("Task1")) {
                    remhour -= CompanyConfig.getHourOfTask1() - 1;
                } else if (temp.equals("Task2")) {
                    remhour -= CompanyConfig.getHourOfTask2() - 1;
                } else if (temp.equals("Task3")) {
                    remhour -= CompanyConfig.getHourOfTask3() - 1;
                } else {
                    System.out.println("Invalid task");
                    break;
                }
            } else if (temp != null) {
                if (temp.equals("Task1")) {
                    remhour -= CompanyConfig.getHourOfTask1();
                } else if (temp.equals("Task2")) {
                    remhour -= CompanyConfig.getHourOfTask2();
                } else if (temp.equals("Task3")) {
                    remhour -= CompanyConfig.getHourOfTask3();
                } else {
                    System.out.println("Invalid task");
                    break;
                }
            } else {
                break;
            }
        }
        return tasks;
    }
	
	public int returnValueUpdatingTCLNUM(int int1, int int2) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
    	CompanyConfig.incrementAndSaveTCLNUMInDatabase(jdbcTemplate);
    	return int1*int2+TempCustomer.getInitCharge();
    }
	
	public void loadAll() {
	    JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

	    // Step 1: Retrieve all names from the source table
	    List<String> names = jdbcTemplate.queryForList("SELECT NAMEANDSURNAME FROM EXINTERN", String.class);

	    // Step 2: Delete all rows from the source table
	    jdbcTemplate.update("DELETE FROM EXINTERN");

	    // Step 3: Insert the retrieved names into the destination table
	    for (String name : names) {
	        jdbcTemplate.update("INSERT INTO INTERN (NAMEANDSURNAME) VALUES (?)", name);
	    }
	}

    public List<String> removeSelectedRows(String[] nameandsurnamesToRemove) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String deleteQuery = "DELETE FROM INTERN WHERE NAMEANDSURNAME = ?";
        String insertQuery = "INSERT INTO EXINTERN (NAMEANDSURNAME) VALUES (?)";
        List<String> l = new ArrayList<>();
        
        for (String nameandsurname : nameandsurnamesToRemove) {
            int affectedRows = jdbcTemplate.update(deleteQuery, nameandsurname);
            
            if (affectedRows > 0) {
                int IANUM = CompanyConfig.getIANUM();
                l.add("Intern " + nameandsurname + " is removed from table with internship application num " + IANUM);
                CompanyConfig.incrementAndSaveIANUMInDatabase(jdbcTemplate);
                
                jdbcTemplate.update(insertQuery, nameandsurname);
            }
        }
        return l;
    }
    
    public void fillInternTableFromCsv(String csvFilePath) {
        String tableName = "INTERN";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

            while ((line = br.readLine()) != null) {
                
                String fieldValue = line.trim();

                String sql = "INSERT INTO " + tableName + " (NAMEANDSURNAME) VALUES (:fieldValue)";

                MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("fieldValue", fieldValue);

                jdbcTemplate.update(sql, paramSource);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<String> retrieveInternNandS() {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
    	String sql = "SELECT NAMEANDSURNAME FROM INTERN";
        return jdbcTemplate.queryForList(sql, String.class);
    }
    
    public void fillTableFromCsv(String csvFilePath) {
        String tableName = "CUSTOMER";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

            while ((line = br.readLine()) != null) {
                
                String fieldValue = line.trim();

                String sql = "INSERT INTO " + tableName + " (NAMEANDSURNAME) VALUES (:fieldValue)";

                MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("fieldValue", fieldValue);

                jdbcTemplate.update(sql, paramSource);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteCustomersUsingCsv(String csvFilePath) {
        String tableName = "CUSTOMER";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

            while ((line = br.readLine()) != null) {
                String fieldValue = line.trim();

                String sql = "DELETE FROM " + tableName + " WHERE NAMEANDSURNAME = :fieldValue";

                MapSqlParameterSource paramSource = new MapSqlParameterSource();
                paramSource.addValue("fieldValue", fieldValue);

                jdbcTemplate.update(sql, paramSource);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<String> retrieveNamesAndSurnames() {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
    	String sql = "SELECT NAMEANDSURNAME FROM CUSTOMER";
        return jdbcTemplate.queryForList(sql, String.class);
    }
    
    public void dropCustomerTable() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "DROP TABLE CUSTOMER";
        try {
            jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.err.println("Error dropping customer table: " + e.getMessage());
        }
    }
    
    public void createCustomerTable() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "CREATE TABLE CUSTOMER (NAMEANDSURNAME VARCHAR(50))";
        try {
            jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.err.println("Error creating customer table: " + e.getMessage());
        }
    }
    
    public void createInternTable() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "CREATE TABLE INTERN (NAMEANDSURNAME VARCHAR(50))";
        try {
            jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.err.println("Error creating intern table: " + e.getMessage());
        }
    }
    
    public void createExInternTable() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "CREATE TABLE EXINTERN (NAMEANDSURNAME VARCHAR(50))";
        try {
            jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.err.println("Error creating expired intern table: " + e.getMessage());
        }
    }
    
    public void dropInternTable() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "DROP TABLE INTERN";
        try {
            jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.err.println("Error dropping intern table: " + e.getMessage());
        }
    }
    
    public void dropExInternTable() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "DROP TABLE EXINTERN";
        try {
            jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.err.println("Error dropping expired intern table: " + e.getMessage());
        }
    }
    
    public void createCompanyConfigTable() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "CREATE TABLE COMPANYCONFIG (IANUM INTEGER, TCLNUM INTEGER)";
        try {
            jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.err.println("Error creating company configuration table: " + e.getMessage());
        }
    }
    
    public void dropCompanyConfigTable() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "DROP TABLE COMPANYCONFIG";
        try {
            jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.err.println("Error dropping company configuration table: " + e.getMessage());
        }
    }
    
    public void createPermCustomerTable() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "CREATE TABLE PERMCUSTOMER (NAMEANDSURNAME VARCHAR(32), TASK1 VARCHAR(6), TASK2 VARCHAR(6), TASK3 VARCHAR(6), TASK4 VARCHAR(6), TASK5 VARCHAR(6), TASK6 VARCHAR(6), TASK7 VARCHAR(6), TASK8 VARCHAR(6), TASK9 VARCHAR(6))";
        try {
            jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.err.println("Error creating permanent customer table: " + e.getMessage());
        }
    }
    
    public void dropPermCustomerTable() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "DROP TABLE PERMCUSTOMER";
        try {
            jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.err.println("Error dropping permanent customer table: " + e.getMessage());
        }
    }
    
    public void fillPermCustomerTable(String[] s) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        String sql = "INSERT INTO PERMCUSTOMER (NAMEANDSURNAME, TASK1, TASK2, TASK3, TASK4, TASK5, TASK6, TASK7, TASK8, TASK9) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8], s[9]);
        } catch (DataAccessException e) {
            System.err.println("Error filling permanent customer table: " + e.getMessage());
        }
    }
    
    public void changeIANUM(int num) {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
    	CompanyConfig.changeIANUMInDatabase(jdbcTemplate, num);
    }
    
    public void changeTCLNUM(int num) {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
    	CompanyConfig.changeTCLNUMInDatabase(jdbcTemplate, num);
    }
    
    public void changeDAYNUM(int num) {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
    	CompanyConfig.changeDAYNUMInDatabase(jdbcTemplate, num);
    }
    
    public void changeREPNUM(int num) {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
    	CompanyConfig.changeREPNUMInDatabase(jdbcTemplate, num);
    }
}