package mainpackage.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component
public class PermCustomer {
	
	public PermCustomer() {
		
	}

	private String nameAndSurname;
	private String task1;
	private String task2;
	private String task3;
	private String task4;
	private String task5;
	private String task6;
	private String task7;
	private String task8;
	private String task9;
	private int monReq;
	private int tueReq;
	private int wedReq;
	private int thuReq;
	private int friReq;
	private int satReq;
	private int sunReq;

	public String getNameAndSurname() {
		return nameAndSurname;
	}
	
	public void setNameAndSurname(String nameAndSurname) {
		this.nameAndSurname = nameAndSurname;
	}
	
	public String getTask1() {
		return task1;
	}

	public void setTask1(String task1) {
		this.task1 = task1;
	}

	public String getTask2() {
		return task2;
	}

	public void setTask2(String task2) {
		this.task2 = task2;
	}

	public String getTask3() {
		return task3;
	}

	public void setTask3(String task3) {
		this.task3 = task3;
	}

	public String getTask4() {
		return task4;
	}

	public void setTask4(String task4) {
		this.task4 = task4;
	}

	public String getTask5() {
		return task5;
	}

	public void setTask5(String task5) {
		this.task5 = task5;
	}

	public String getTask6() {
		return task6;
	}

	public void setTask6(String task6) {
		this.task6 = task6;
	}

	public String getTask7() {
		return task7;
	}

	public void setTask7(String task7) {
		this.task7 = task7;
	}

	public String getTask8() {
		return task8;
	}

	public void setTask8(String task8) {
		this.task8 = task8;
	}

	public String getTask9() {
		return task9;
	}

	public void setTask9(String task9) {
		this.task9 = task9;
	}
	
	public PreparedStatement setStatement(Connection connection, int index) {
		String updateQuery = "UPDATE PERMCUSTOMER SET TASK"+String.valueOf(index)+" = ? WHERE NAMEANDSURNAME = ?";
		try {
			return connection.prepareStatement(updateQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String executeStatementForTask(Connection connection, String task, int index, int i) {
		PreparedStatement statement = setStatement(connection, index);
        try {
			statement.setString(1, task);
			statement.setString(2, "NameAndSurname"+String.valueOf(i));
			statement.executeUpdate();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateTasksInDatabase(DataSource dataSource, int i) {
        try (Connection connection = dataSource.getConnection()) {
        	String execution = (task2 == null) ? executeStatementForTask(connection, null, 1, i) : executeStatementForTask(connection, this.task2, 1, i);
        	execution = (task3 == null) ? executeStatementForTask(connection, execution, 2, i) : executeStatementForTask(connection, this.task3, 2, i);
        	execution = (task4 == null) ? executeStatementForTask(connection, execution, 3, i) : executeStatementForTask(connection, this.task4, 3, i);
        	execution = (task5 == null) ? executeStatementForTask(connection, execution, 4, i) : executeStatementForTask(connection, this.task5, 4, i);
        	execution = (task6 == null) ? executeStatementForTask(connection, execution, 5, i) : executeStatementForTask(connection, this.task6, 5, i);
        	execution = (task7 == null) ? executeStatementForTask(connection, execution, 6, i) : executeStatementForTask(connection, this.task7, 6, i);
        	execution = (task8 == null) ? executeStatementForTask(connection, execution, 7, i) : executeStatementForTask(connection, this.task8, 7, i);
        	execution = (task9 == null) ? executeStatementForTask(connection, execution, 8, i) : executeStatementForTask(connection, this.task9, 8, i);
            executeStatementForTask(connection, execution, 9, i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public int getMonReq() {
		return monReq;
	}

	public void setMonReq(int monReq) {
		this.monReq = monReq;
	}

	public int getTueReq() {
		return tueReq;
	}

	public void setTueReq(int tueReq) {
		this.tueReq = tueReq;
	}

	public int getWedReq() {
		return wedReq;
	}

	public void setWedReq(int wedReq) {
		this.wedReq = wedReq;
	}

	public int getThuReq() {
		return thuReq;
	}

	public void setThuReq(int thuReq) {
		this.thuReq = thuReq;
	}

	public int getFriReq() {
		return friReq;
	}

	public void setFriReq(int friReq) {
		this.friReq = friReq;
	}

	public int getSatReq() {
		return satReq;
	}

	public void setSatReq(int satReq) {
		this.satReq = satReq;
	}

	public int getSunReq() {
		return sunReq;
	}

	public void setSunReq(int sunReq) {
		this.sunReq = sunReq;
	}
}
