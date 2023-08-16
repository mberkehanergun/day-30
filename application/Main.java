package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import mainpackage.ComputerEngineer;
import mainpackage.Engineer;
import mainpackage.Engineers;
import mainpackage.company.CompanyConfig;
import mainpackage.dao.NamedParamJdbcDaoImpl;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
	
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Engineers Login Application");

            Label usernameLabel = new Label("Username:");
            TextField usernameField = new TextField();

            Label passwordLabel = new Label("Password:");
            PasswordField passwordField = new PasswordField();

            Button loginButton = new Button("Login");
            Label outputLabel = new Label();

            outputLabel.setText("Enter 'exit' for both username and password if you want to exit.");

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 20, 20, 20));

            grid.add(usernameLabel, 0, 0);
            grid.add(usernameField, 1, 0);
            grid.add(passwordLabel, 0, 1);
            grid.add(passwordField, 1, 1);
            grid.add(loginButton, 1, 2);
            grid.add(outputLabel, 0, 3, 2, 1);

            Scene scene = new Scene(grid, 500, 400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("engineersinfo.xml");
            Engineers engineers = ctx.getBean("engineers", Engineers.class);

            AbstractApplicationContext ctx2 = new ClassPathXmlApplicationContext("namedparamjdbcdaoimpl.xml");
            NamedParamJdbcDaoImpl dao = ctx2.getBean("namedParamJdbcDaoImpl", NamedParamJdbcDaoImpl.class);

            loginButton.setOnAction(event -> {

                String inputname = usernameField.getText();
                String inputpw = passwordField.getText();
                boolean ifNotExit = !inputname.equals("exit") || !inputpw.equals("exit");
                boolean loginFail = engineers.login(inputname, inputpw) && ifNotExit;
                if (loginFail == false && ifNotExit == true) {

                    Button companySettingsButton = new Button("Company Settings");
                    Button loadCustomersButton = new Button("Load Customers");
                    Button acceptInternsButton = new Button("Accept Interns");
                    Button processWorkButton = new Button("Process Work");
                    Button processTempCustomersButton = new Button("Process Temp Customers");
                    Button fetchTasksButton = new Button("Fetch Tasks");
                    Button arrangeTasksButton = new Button("Arrange Tasks");
                    Button task1Button = new Button("Task 1");
                    Button task2Button = new Button("Task 2");
                    Button task3Button = new Button("Task 3");
                    Button task4Button = new Button("Task 4");
                    Button backButton = new Button("Back");

                    VBox buttonLayout = new VBox(10);
                    buttonLayout.setAlignment(Pos.CENTER);
                    buttonLayout.getChildren().addAll(companySettingsButton, loadCustomersButton, acceptInternsButton, processWorkButton, processTempCustomersButton, fetchTasksButton, arrangeTasksButton, task1Button, task2Button, task3Button, task4Button, backButton);
                    Scene buttonScene = new Scene(buttonLayout, 600, 500);

                    primaryStage.setScene(buttonScene);

                    backButton.setOnAction(backEvent -> {
                        // Go back to the original login scene
                        primaryStage.setScene(scene);
                        usernameField.clear();
                        passwordField.clear();
                        outputLabel.setText("Enter 'exit' for both username and password if you want to exit."); // Clear the output message
                    });
                    
                    // Handle button actions
                    companySettingsButton.setOnAction(companySettingsEvent -> {
                        // Create a new scene for company settings
                    	Label resultLabel = new Label("Results will show up here.");

                        Button createTableButton1 = new Button("Create Customer Table");
                        Button dropTableButton1 = new Button("Drop Customer Table");
                        Button createTableButton2 = new Button("Create Intern Tables");
                        Button dropTableButton2 = new Button("Drop Intern Tables");
                        Button createTableButton3 = new Button("Create CompanyConfig Table");
                        Button dropTableButton3 = new Button("Drop CompanyConfig Table");
                        Button createTableButton4 = new Button("Create PermCustomer Table");
                        Button dropTableButton4 = new Button("Drop PermCustomer Table");
                        Button fillTableButton = new Button("Fill PermCustomer Table");
                        Button changeNumButton1 = new Button("Change IANUM");
                        Button changeNumButton2 = new Button("Change TCLNUM");
                        Button changeNumButton3 = new Button("Change DAYNUM");
                        Button changeNumButton4 = new Button("Change REPNUM");
                        Button backButtonToButtonScene1 = new Button("Back");

                        VBox settingsLayout = new VBox(10);
                        settingsLayout.setAlignment(Pos.CENTER);
                        settingsLayout.getChildren().addAll(resultLabel, createTableButton1, dropTableButton1, createTableButton2, dropTableButton2, createTableButton3, dropTableButton3, createTableButton4, dropTableButton4, fillTableButton, changeNumButton1, changeNumButton2, changeNumButton3, changeNumButton4, backButtonToButtonScene1);
                        Scene settingsScene = new Scene(settingsLayout, 600, 500);

                        primaryStage.setScene(settingsScene);

                        // Handle "Create Customer Table" button action
                        createTableButton1.setOnAction(createTableEvent -> {
                        	dao.createCustomerTable();
                        	resultLabel.setText("Customer table created successfully.");
                        });

                        // Handle "Drop Customer Table" button action
                        dropTableButton1.setOnAction(dropTableEvent -> {
                        	dao.dropCustomerTable();
                        	resultLabel.setText("Customer table dropped successfully.");
                        });
                        
                        createTableButton2.setOnAction(createTableEvent -> {
                        	dao.createInternTable();
                        	dao.createExInternTable();
                        	resultLabel.setText("Intern tables created successfully.");
                        });

                        dropTableButton2.setOnAction(dropTableEvent -> {
                        	dao.dropInternTable();
                        	dao.dropExInternTable();
                        	resultLabel.setText("Intern tables dropped successfully.");
                        });
                        
                        createTableButton3.setOnAction(createTableEvent -> {
                        	dao.createCompanyConfigTable();
                        	resultLabel.setText("CompanyConfig table created successfully.");
                        });

                        dropTableButton3.setOnAction(dropTableEvent -> {
                        	dao.dropCompanyConfigTable();
                        	resultLabel.setText("CompanyConfig table dropped successfully.");
                        });
                        
                        createTableButton4.setOnAction(createTableEvent -> {
                        	dao.createPermCustomerTable();
                        	resultLabel.setText("Permanent customer table created successfully.");
                        });

                        dropTableButton4.setOnAction(dropTableEvent -> {
                        	dao.dropPermCustomerTable();
                        	resultLabel.setText("Permanent table dropped successfully.");
                        });
                        
                        fillTableButton.setOnAction(changeNumEvent -> {
                            
                            Stage fillTableStage = new Stage();
                            fillTableStage.setTitle("Delete Names");
                            
                            
                            TextArea dataArea = new TextArea();
                            Button fillButton = new Button("Fill");

                            VBox fillTableLayout = new VBox(10);
                            fillTableLayout.setAlignment(Pos.CENTER);
                            fillTableLayout.getChildren().addAll(dataArea, fillButton);

                            Scene fillTableScene = new Scene(fillTableLayout, 400, 300);
                            fillTableStage.setScene(fillTableScene);

                            // Handle the "Change" button action
                            fillButton.setOnAction(changeNumButtonEvent -> {
                            	String text = dataArea.getText();
                            	String[] linesArray = text.split("\n");
                            	dao.fillPermCustomerTable(linesArray);
                                resultLabel.setText("\nPermanent customer table filled succesfully.");
                                fillTableStage.close();
                            });

                            fillTableStage.show();
                        });
                        
                        changeNumButton1.setOnAction(changeNumEvent -> {
                            // Create a new stage for changing IANUM
                            Stage changeNumStage = new Stage();
                            changeNumStage.setTitle("Change IANUM");
                            
                            TextField newNumField = new TextField();
                            Button changeNumButton = new Button("Change");

                            VBox changeNumLayout = new VBox(10);
                            changeNumLayout.setAlignment(Pos.CENTER);
                            changeNumLayout.getChildren().addAll(newNumField, changeNumButton);

                            Scene changeNumScene = new Scene(changeNumLayout, 400, 300);
                            changeNumStage.setScene(changeNumScene);

                            // Handle the "Change" button action
                            changeNumButton.setOnAction(changeNumButtonEvent -> {
                                int newNum = Integer.parseInt(newNumField.getText());
                                dao.changeIANUM(newNum);
                                resultLabel.setText("IANUM changed successfully");
                                changeNumStage.close(); // Close the changeNumStage after changing IANUM
                            });

                            changeNumStage.show();
                        });
                        
                        changeNumButton2.setOnAction(changeNumEvent -> {
                            
                            Stage changeNumStage = new Stage();
                            changeNumStage.setTitle("Change TCLNUM");
                            
                            TextField newNumField = new TextField();
                            Button changeNumButton = new Button("Change");

                            VBox changeNumLayout = new VBox(10);
                            changeNumLayout.setAlignment(Pos.CENTER);
                            changeNumLayout.getChildren().addAll(newNumField, changeNumButton);

                            Scene changeNumScene = new Scene(changeNumLayout, 400, 300);
                            changeNumStage.setScene(changeNumScene);

                            // Handle the "Change" button action
                            changeNumButton.setOnAction(changeNumButtonEvent -> {
                                int newNum = Integer.parseInt(newNumField.getText());
                                dao.changeTCLNUM(newNum);
                                resultLabel.setText("TCLNUM changed successfully");
                                changeNumStage.close(); // Close the changeNumStage after changing IANUM
                            });

                            changeNumStage.show();
                        });
                        
                        changeNumButton3.setOnAction(changeNumEvent -> {
                            
                            Stage changeNumStage = new Stage();
                            changeNumStage.setTitle("Change DAYNUM");
                            
                            TextField newNumField = new TextField();
                            Button changeNumButton = new Button("Change");

                            VBox changeNumLayout = new VBox(10);
                            changeNumLayout.setAlignment(Pos.CENTER);
                            changeNumLayout.getChildren().addAll(newNumField, changeNumButton);

                            Scene changeNumScene = new Scene(changeNumLayout, 400, 300);
                            changeNumStage.setScene(changeNumScene);

                            // Handle the "Change" button action
                            changeNumButton.setOnAction(changeNumButtonEvent -> {
                                int newNum = Integer.parseInt(newNumField.getText());
                                dao.changeDAYNUM(newNum);
                                resultLabel.setText("DAYNUM changed successfully");
                                changeNumStage.close(); // Close the changeNumStage after changing IANUM
                            });

                            changeNumStage.show();
                        });
                        
                        changeNumButton4.setOnAction(changeNumEvent -> {
                            
                            Stage changeNumStage = new Stage();
                            changeNumStage.setTitle("Change REPNUM");
                            
                            TextField newNumField = new TextField();
                            Button changeNumButton = new Button("Change");

                            VBox changeNumLayout = new VBox(10);
                            changeNumLayout.setAlignment(Pos.CENTER);
                            changeNumLayout.getChildren().addAll(newNumField, changeNumButton);

                            Scene changeNumScene = new Scene(changeNumLayout, 400, 300);
                            changeNumStage.setScene(changeNumScene);

                            // Handle the "Change" button action
                            changeNumButton.setOnAction(changeNumButtonEvent -> {
                                int newNum = Integer.parseInt(newNumField.getText());
                                dao.changeREPNUM(newNum);
                                resultLabel.setText("REPNUM changed successfully");
                                changeNumStage.close(); 
                            });

                            changeNumStage.show();
                        });
                        
                        backButtonToButtonScene1.setOnAction(backToButtonEvent -> {
                            primaryStage.setScene(buttonScene); // Switch to the "Button" scene
                        });
                    });

                    loadCustomersButton.setOnAction(loadCustomersEvent -> {
                    	
                    	// Original terminal-like interface scene
                        TextArea terminalTextArea = new TextArea();
                        terminalTextArea.setEditable(true);
                        terminalTextArea.setPrefRowCount(10);

                        Button backButtonToButtonScene2 = new Button("Back");
                        Button insertButton = new Button("Insert");
                        Button deleteButton = new Button("Delete");

                        HBox buttonBox = new HBox(10);
                        buttonBox.setAlignment(Pos.CENTER);
                        buttonBox.getChildren().addAll(backButtonToButtonScene2, insertButton, deleteButton);

                        VBox terminalLayout = new VBox(10);
                        terminalLayout.setAlignment(Pos.CENTER);
                        terminalLayout.getChildren().addAll(terminalTextArea, buttonBox);
                        Scene terminalScene = new Scene(terminalLayout, 600, 500);
                        
                        insertButton.setDisable(false);
                        deleteButton.setDisable(true);
                        
                        // Handle terminal button actions
                        insertButton.setOnAction(insertEvent -> {
                            dao.fillTableFromCsv("C:\\Users\\berkehan\\Downloads\\CustomerData.csv");
                            List<String> NandS = dao.retrieveNamesAndSurnames();
                            for (String item : NandS) {
                                terminalTextArea.appendText("\n" + item);
                            }
                            insertButton.setDisable(true);
                            deleteButton.setDisable(false);
                        });

                        deleteButton.setOnAction(deleteEvent -> {
                            dao.deleteCustomersUsingCsv("C:\\Users\\berkehan\\Downloads\\CustomerData.csv");
                            List<String> NandS = dao.retrieveNamesAndSurnames();
                            for (String item : NandS) {
                                terminalTextArea.appendText("\n" + item);
                            }
                            terminalTextArea.appendText("\nData from the CSV file has been successfully deleted from the table.");
                            insertButton.setDisable(false);
                            deleteButton.setDisable(true);
                        });

                        backButtonToButtonScene2.setOnAction(backToButtonEvent -> {
                            primaryStage.setScene(buttonScene); // Switch to the "Button" scene
                        });
                    	
                        // Reuse the existing terminal-like interface scene
                        primaryStage.setScene(terminalScene);
                    });
                    
                    acceptInternsButton.setOnAction(loadCustomersEvent -> {
                    	
                    	// Original terminal-like interface scene
                        TextArea terminalTextArea = new TextArea();
                        terminalTextArea.setEditable(true);
                        terminalTextArea.setPrefRowCount(10);

                        Button backButtonToButtonScene2 = new Button("Back");
                        Button fillButton = new Button("Fill");
                        Button deleteButton = new Button("Delete");
                        Button loadAllButton = new Button("LoadAll");
                        

                        HBox buttonBox = new HBox(10);
                        buttonBox.setAlignment(Pos.CENTER);
                        buttonBox.getChildren().addAll(backButtonToButtonScene2, fillButton, deleteButton, loadAllButton);

                        VBox terminalLayout = new VBox(10);
                        terminalLayout.setAlignment(Pos.CENTER);
                        terminalLayout.getChildren().addAll(terminalTextArea, buttonBox);
                        Scene terminalScene = new Scene(terminalLayout, 600, 500);
                        
                        loadAllButton.setDisable(true);
                        deleteButton.setDisable(true);
                        
                        fillButton.setOnAction(insertEvent -> {
                            dao.fillInternTableFromCsv("C:\\Users\\berkehan\\Downloads\\InternData.csv");
                            List<String> NandS = dao.retrieveInternNandS();
                            for (String item : NandS) {
                                terminalTextArea.appendText("\n" + item);
                            }
                            terminalTextArea.appendText("\nIntern table filled with all names from the file succesfully.");
                            fillButton.setDisable(true);
                            deleteButton.setDisable(false);
                        });
                        
                        // Handle terminal button actions
                        loadAllButton.setOnAction(insertEvent -> {
                            dao.loadAll();
                            List<String> NandS = dao.retrieveInternNandS();
                            for (String item : NandS) {
                                terminalTextArea.appendText("\n" + item);
                            }
                            terminalTextArea.appendText("\nAll deleted names loaded succesfully.");
                            loadAllButton.setDisable(true);
                        });

                        deleteButton.setOnAction(changeNumEvent -> {
                            // Create a new stage for changing IANUM
                            Stage deleteNamesStage = new Stage();
                            deleteNamesStage.setTitle("Delete Names");
                            
                            
                            TextArea newNamesArea = new TextArea();
                            Button deleteNamesButton = new Button("Delete");

                            VBox deleteNamesLayout = new VBox(10);
                            deleteNamesLayout.setAlignment(Pos.CENTER);
                            deleteNamesLayout.getChildren().addAll(newNamesArea, deleteNamesButton);

                            Scene deleteNamesScene = new Scene(deleteNamesLayout, 400, 300);
                            deleteNamesStage.setScene(deleteNamesScene);

                            // Handle the "Change" button action
                            deleteNamesButton.setOnAction(changeNumButtonEvent -> {
                            	String text = newNamesArea.getText();
                            	String[] linesArray = text.split("\n");
                            	List<String> l = dao.removeSelectedRows(linesArray);
                            	for (String s : l) {
                            		terminalTextArea.appendText("\n"+s);
                                }
                            	List<String> NandS = dao.retrieveInternNandS();
                                for (String item : NandS) {
                                    terminalTextArea.appendText("\n" + item);
                                }
                                terminalTextArea.appendText("\nSelected names deleted succesfully.");
                                loadAllButton.setDisable(false);
                                deleteNamesStage.close(); // Close the changeNumStage after changing IANUM
                            });

                            deleteNamesStage.show();
                        });

                        backButtonToButtonScene2.setOnAction(backToButtonEvent -> {
                            primaryStage.setScene(buttonScene); // Switch to the "Button" scene
                        });
                    	
                        // Reuse the existing terminal-like interface scene
                        primaryStage.setScene(terminalScene);
                    });
                    
                    processWorkButton.setOnAction(processWorkEvent -> {

                        TextArea processTextArea = new TextArea();
                        processTextArea.setEditable(true);

                        Label resultLabel = new Label("Results will show up here.");
                        
                        Button backButtonToButtonScene3 = new Button("Back");
                        Button processButton = new Button("Process");

                        HBox buttonBox = new HBox(10);
                        buttonBox.setAlignment(Pos.CENTER);
                        buttonBox.getChildren().addAll(backButtonToButtonScene3, processButton);

                        VBox processLayout = new VBox(10);
                        processLayout.setAlignment(Pos.CENTER);
                        processLayout.getChildren().addAll(processTextArea, buttonBox, resultLabel);
                        Scene processScene = new Scene(processLayout, 600, 500);

                        primaryStage.setScene(processScene);

                        // Handle "Process" button action
                        processButton.setOnAction(processButtonEvent -> {
                        	int value;
                        	String inputCsvFile = "C:\\Users\\berkehan\\Downloads\\WorkedHoursForDay.csv";
                			try (BufferedReader br = new BufferedReader(new FileReader(inputCsvFile))) {
                	            String outputCsvFile = "C:\\Users\\berkehan\\Downloads\\TotalSalaryForDay.csv";
                	            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputCsvFile))) {
                	            	value = Integer.parseInt(br.readLine().trim());
                	            	processTextArea.appendText("\nComputer Engineer 1 worked "+value+" hours today and got salary of "+processCEngSalary(engineers.getCe1(), value, bw));
                	            	value = Integer.parseInt(br.readLine().trim());
                	            	processTextArea.appendText("\nComputer Engineer 2 worked "+value+" hours today and got salary of "+processCEngSalary(engineers.getCe2(), value, bw));
                	            	value = Integer.parseInt(br.readLine().trim());
                	            	processTextArea.appendText("\nComputer Engineer 3 worked "+value+" hours today and got salary of "+processCEngSalary(engineers.getCe3(), value, bw));
                	            	value = Integer.parseInt(br.readLine().trim());
                	            	processTextArea.appendText("\nComputer Engineer 4 worked "+value+" hours today and got salary of "+processCEngSalary(engineers.getCe4(), value, bw));
                	            	value = Integer.parseInt(br.readLine().trim());
                	            	processTextArea.appendText("\nComputer Engineer 5 worked "+value+" hours today and got salary of "+processCEngSalary(engineers.getCe5(), value, bw));
                	            	value = Integer.parseInt(br.readLine().trim());
                	            	processTextArea.appendText("\nComputer Engineer 6 worked "+value+" hours today and got salary of "+processCEngSalary(engineers.getCe6(), value, bw));
                	            	value = Integer.parseInt(br.readLine().trim());
                	            	processTextArea.appendText("\nComputer Engineer 7 worked "+value+" hours today and got salary of "+processCEngSalary(engineers.getCe7(), value, bw));
                	            	value = Integer.parseInt(br.readLine().trim());
                	            	processTextArea.appendText("\nEngineer 1 worked "+value+" hours today and got salary of "+processEngSalary(engineers.getE1(), value, bw));
                	            	value = Integer.parseInt(br.readLine().trim());
                	            	processTextArea.appendText("\nEngineer 2 worked "+value+" hours today and got salary of "+processEngSalary(engineers.getE2(), value, bw));
                	            	value = Integer.parseInt(br.readLine().trim());
                	            	processTextArea.appendText("\nEngineer 3 worked "+value+" hours today and got salary of "+processEngSalary(engineers.getE3(), value, bw));
                	            	value = Integer.parseInt(br.readLine().trim());
                	            	processTextArea.appendText("\nEngineer 4 worked "+value+" hours today and got salary of "+processEngSalary(engineers.getE4(), value, bw));
                	                resultLabel.setText("Data has been written to "+outputCsvFile);
                	                processButton.setDisable(true);
                	            } catch (Exception e) {
                	                e.printStackTrace();
                	            }
                	        } catch (Exception e) {
                	            e.printStackTrace();
                	        }
                            String processResult = "\nWork processed successfully.";
                            processTextArea.appendText(processResult);
                        });

                        // Handle "Back" button action
                        backButtonToButtonScene3.setOnAction(backToButtonEvent -> {
                            primaryStage.setScene(buttonScene); // Switch back to the "Button" scene
                        });
                    });
                    
                    processTempCustomersButton.setOnAction(loadCustomersEvent -> {
                    	
                    	// Original terminal-like interface scene
                        TextArea terminalTextArea = new TextArea();
                        terminalTextArea.setEditable(true);
                        terminalTextArea.setPrefRowCount(10);

                        Label resultLabel = new Label("Results will show up here.");
                        
                        Button backButtonToButtonScene2 = new Button("Back");
                        Button processButton = new Button("Process");
                        

                        HBox buttonBox = new HBox(10);
                        buttonBox.setAlignment(Pos.CENTER);
                        buttonBox.getChildren().addAll(backButtonToButtonScene2, processButton);

                        VBox terminalLayout = new VBox(10);
                        terminalLayout.setAlignment(Pos.CENTER);
                        terminalLayout.getChildren().addAll(terminalTextArea, buttonBox, resultLabel);
                        Scene terminalScene = new Scene(terminalLayout, 600, 500);
                        
                        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\berkehan\\Downloads\\allTCHourTaskNandS.csv"))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                // Process the CSV data in 'line'
                                terminalTextArea.appendText("\n"+line);
                            }
                            terminalTextArea.appendText("\nAll temp customers loaded succesfully.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        processButton.setOnAction(changeNumEvent -> {
                            // Create a new stage for changing IANUM
                            Stage deleteByNameStage = new Stage();
                            deleteByNameStage.setTitle("Delete By Name");
                            
                            
                            TextArea newNamesArea = new TextArea();
                            Button deleteNamesButton = new Button("Delete");

                            VBox deleteNamesLayout = new VBox(10);
                            deleteNamesLayout.setAlignment(Pos.CENTER);
                            deleteNamesLayout.getChildren().addAll(newNamesArea, deleteNamesButton);

                            Scene deleteNamesScene = new Scene(deleteNamesLayout, 400, 300);
                            deleteByNameStage.setScene(deleteNamesScene);

                            // Handle the "Change" button action
                            deleteNamesButton.setOnAction(changeNumButtonEvent -> {
                            	String text = newNamesArea.getText();
                            	String[] leavingTempCustomers = text.split("\n");
                            	
                            	try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\berkehan\\Downloads\\allTCHourTaskNandS.csv"))) {
                    			    try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\berkehan\\Downloads\\remainingTCHourTaskNandS.csv"))) {
                    			    	try {
                    			    	    String line;
                    			    	    while ((line = br.readLine()) != null) {
                    			    	        String[] parts = line.split(",");
                    			    	        if (parts.length >= 3) {
                    			    	        	int int1 = Integer.parseInt(parts[0].replaceAll("\"", "").trim());
                    			    	        	String string1 = parts[1].replaceAll("\"", "").trim();
                    			    	            String string2 = parts[2].replaceAll("\"", "").trim();
                    			    	            if(ifContains(leavingTempCustomers, string2)) {
                    			    	            	if(string1.equals("Task1")) {
                    			    	            		terminalTextArea.appendText("\nTemporary customer "+string2+" with temporary customer login number "+CompanyConfig.getTCLNUM()+" has a total charge of "+dao.returnValueUpdatingTCLNUM(int1, CompanyConfig.getCostOfTask1PerHour()));
                    			    	            	}
                    			    	            	else if(string1.equals("Task2")) {
                    			    	            		terminalTextArea.appendText("\nTemporary customer "+string2+" with temporary customer login number "+CompanyConfig.getTCLNUM()+" has a total charge of "+dao.returnValueUpdatingTCLNUM(int1, CompanyConfig.getCostOfTask2PerHour()));
                    			    	            	}
                    			    	            	else if(string1.equals("Task3")) {
                    			    	            		terminalTextArea.appendText("\nTemporary customer "+string2+" with temporary customer login number "+CompanyConfig.getTCLNUM()+" has a total charge of "+dao.returnValueUpdatingTCLNUM(int1, CompanyConfig.getCostOfTask3PerHour()));
                    			    	            	}
                    			    	            	else {
                    			    	            		System.err.println("Error: Invalid task - " + line);
                    			    	            	}
                    			    	            }
                    			    	            else {
                    			    	            	bw.write(int1 + "," + string1 + "," + string2);
                    			    	            }
                    			    	            bw.newLine();
                    			    	        } else {
                    			    	            System.err.println("Error: Invalid line format - " + line);
                    			    	        }
                    			    	    }
                    			    	} catch (IOException e) {
                    			    	    e.printStackTrace();
                    			    	}
                    			        resultLabel.setText("Data has been written to C:\\Users\\berkehan\\Downloads\\remainingTCHourTaskNandS.csv");
                    			    } catch (IOException e) {
                    			        e.printStackTrace();
                    			    }

                    			} catch (IOException e) {
                    			    e.printStackTrace();
                    			}
                            	
                            	terminalTextArea.appendText("\nSelected temp customers deleted succesfully.");
                            	
                            	try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\berkehan\\Downloads\\remainingTCHourTaskNandS.csv"))) {
                                    String line;
                                    while ((line = br.readLine()) != null) {
                                        // Process the CSV data in 'line'
                                        terminalTextArea.appendText("\n"+line);
                                    }
                                    terminalTextArea.appendText("\nRemaining temp customers loaded succesfully.");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                
                                processButton.setDisable(true);
                                deleteByNameStage.close(); // Close the changeNumStage after changing IANUM
                            });

                            deleteByNameStage.show();
                        });

                        backButtonToButtonScene2.setOnAction(backToButtonEvent -> {
                            primaryStage.setScene(buttonScene); // Switch to the "Button" scene
                        });
                    	
                        // Reuse the existing terminal-like interface scene
                        primaryStage.setScene(terminalScene);
                    });
                    
                    fetchTasksButton.setOnAction(loadCustomersEvent -> {
                    	
                    	// Original terminal-like interface scene
                        TextArea terminalTextArea = new TextArea();
                        terminalTextArea.setEditable(true);
                        terminalTextArea.setPrefRowCount(10);

                        Button backButtonToButtonScene2 = new Button("Back");
                        Button fetchButton = new Button("Fetch");

                        HBox buttonBox = new HBox(10);
                        buttonBox.setAlignment(Pos.CENTER);
                        buttonBox.getChildren().addAll(backButtonToButtonScene2, fetchButton);

                        VBox terminalLayout = new VBox(10);
                        terminalLayout.setAlignment(Pos.CENTER);
                        terminalLayout.getChildren().addAll(terminalTextArea, buttonBox);
                        Scene terminalScene = new Scene(terminalLayout, 600, 500);
                        
                        // Handle terminal button actions
                        fetchButton.setOnAction(insertEvent -> {
                            List<String> messages = dao.displayPermanentCustomer();
                            for (String item : messages) {
                                terminalTextArea.appendText("\n" + item);
                            }
                            fetchButton.setDisable(true);
                        });

                        backButtonToButtonScene2.setOnAction(backToButtonEvent -> {
                            primaryStage.setScene(buttonScene); // Switch to the "Button" scene
                        });
                    	
                        // Reuse the existing terminal-like interface scene
                        primaryStage.setScene(terminalScene);
                    });
                    
                    arrangeTasksButton.setOnAction(arrangeTasksEvent -> {
                        Label oldTasksOrderLabel = new Label("Old Tasks Order:");
                        TextField oldTextField = new TextField();
                        Label newTasksOrderLabel = new Label("New Tasks Order:");
                        TextField newTextField = new TextField();
                        Button arrangeButton = new Button("Arrange");
                        Label resultLabel = new Label();
                        Button backButtonToButtonScene3 = new Button("Back");

                        VBox arrangeTasksLayout = new VBox(10);
                        arrangeTasksLayout.setAlignment(Pos.CENTER);
                        arrangeTasksLayout.getChildren().addAll(oldTasksOrderLabel, oldTextField, newTasksOrderLabel, newTextField, arrangeButton, resultLabel, backButtonToButtonScene3);

                        Scene arrangeTasksScene = new Scene(arrangeTasksLayout, 900, 700);

                        primaryStage.setScene(arrangeTasksScene);
                        
                        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\berkehan\\Downloads\\Tasks.csv"))) {
                            String line;
                            if ((line = br.readLine()) != null) {
                                oldTextField.setText(line);
                            }
                            resultLabel.setText("Tasks in old order loaded succesfully.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        
                        // Handle "Add Task" button action
                        arrangeButton.setOnAction(addTaskEvent -> {
                        	String[] oldtasks = null;
                        	try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\berkehan\\Downloads\\Tasks.csv"))) {
                                String line;
                                if ((line = br.readLine()) != null) {
                                	oldtasks = line.split(",");
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        	try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\berkehan\\Downloads\\ArrangedTasks.csv"))) {
                        		List<String> arrangedTasks = arrangeTasks(oldtasks);
            	                for (String x : arrangedTasks) {
            		                bw.write(x);
            		                bw.write(",");
            		            }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        	try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\berkehan\\Downloads\\ArrangedTasks.csv"))) {
                                String line;
                                if ((line = br.readLine()) != null) {
                                    newTextField.setText(line);
                                }
                                resultLabel.setText("Tasks in new order loaded and saved to a .csv file succesfully.");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        	arrangeButton.setDisable(true);
                        });
                        
                        backButtonToButtonScene3.setOnAction(backToButtonEvent -> {
                            primaryStage.setScene(buttonScene); // Switch back to the "Button" scene
                        });
                    });

                    task1Button.setOnAction(task1Event -> {
                        Label resultLabel = new Label("Results will show up here.");
                        TextArea taskTextArea = new TextArea();
                        Button loadReportButton = new Button("Load Report");
                        Button backButtonToButtonScene = new Button("Back");
                        
                        VBox taskLayout = new VBox(10);
                        taskLayout.setAlignment(Pos.CENTER);
                        taskLayout.getChildren().addAll(taskTextArea, loadReportButton, resultLabel, backButtonToButtonScene);

                        Scene taskScene = new Scene(taskLayout, 600, 500);
                        primaryStage.setScene(taskScene);

                        loadReportButton.setOnAction(loadReportEvent -> {
                        	List<String> l = dao.report();
                        	for (String s : l) {
                        		taskTextArea.appendText("\n"+s);
                            }
                        	resultLabel.setText("Report loaded succesfully.");
                        	loadReportButton.setDisable(true);
                        });
                        
                        backButtonToButtonScene.setOnAction(backToButtonEvent -> {
                            primaryStage.setScene(buttonScene); // Switch to the "Button" scene
                        });
                    });
                    
                    task2Button.setOnAction(task1Event -> {
                        Label resultLabel = new Label("Results will show up here.");
                        TextField taskTextField = new TextField();
                        Button loadPredictionButton = new Button("Load Prediction");
                        Button backButtonToButtonScene = new Button("Back");
                        
                        VBox taskLayout = new VBox(10);
                        taskLayout.setAlignment(Pos.CENTER);
                        taskLayout.getChildren().addAll(taskTextField, loadPredictionButton, resultLabel, backButtonToButtonScene);

                        Scene taskScene = new Scene(taskLayout, 600, 500);
                        primaryStage.setScene(taskScene);

                        loadPredictionButton.setOnAction(loadReportEvent -> {
                        	dao.prediction(taskTextField);
                        	resultLabel.setText("Prediction loaded succesfully.");
                        	loadPredictionButton.setDisable(true);
                        });
                        
                        backButtonToButtonScene.setOnAction(backToButtonEvent -> {
                            primaryStage.setScene(buttonScene); // Switch to the "Button" scene
                        });
                    });
                    
                    task3Button.setOnAction(task1Event -> {
                        Label resultLabel = new Label("Results will show up here.");
                        TextArea taskTextArea = new TextArea();
                        Button displayUnualitiesButton = new Button("Display Unusualities");
                        Button backButtonToButtonScene = new Button("Back");
                        
                        VBox taskLayout = new VBox(10);
                        taskLayout.setAlignment(Pos.CENTER);
                        taskLayout.getChildren().addAll(taskTextArea, displayUnualitiesButton, resultLabel, backButtonToButtonScene);

                        Scene taskScene = new Scene(taskLayout, 600, 500);
                        primaryStage.setScene(taskScene);

                        displayUnualitiesButton.setOnAction(loadReportEvent -> {
                        	dao.task3Display(taskTextArea);
                        	resultLabel.setText("Unusualities displayed succesfully.");
                        	displayUnualitiesButton.setDisable(true);
                        });
                        
                        backButtonToButtonScene.setOnAction(backToButtonEvent -> {
                            primaryStage.setScene(buttonScene); // Switch to the "Button" scene
                        });
                    });
                    
                    task4Button.setOnAction(task1Event -> {
                        Label resultLabel = new Label("Results will show up here.");
                        TextArea taskTextArea = new TextArea();
                        Button displayUnualitiesButton = new Button("Display Unusualities");
                        Button backButtonToButtonScene = new Button("Back");
                        
                        VBox taskLayout = new VBox(10);
                        taskLayout.setAlignment(Pos.CENTER);
                        taskLayout.getChildren().addAll(taskTextArea, displayUnualitiesButton, resultLabel, backButtonToButtonScene);

                        Scene taskScene = new Scene(taskLayout, 600, 500);
                        primaryStage.setScene(taskScene);

                        displayUnualitiesButton.setOnAction(loadReportEvent -> {
                        	dao.task4Display(taskTextArea);
                        	resultLabel.setText("Unusualities displayed succesfully.");
                        	displayUnualitiesButton.setDisable(true);
                        });
                        
                        backButtonToButtonScene.setOnAction(backToButtonEvent -> {
                            primaryStage.setScene(buttonScene); // Switch to the "Button" scene
                        });
                    });
                    
                } else if (loginFail == false && ifNotExit == false) {
                    primaryStage.close();
                } else {
                    outputLabel.setText("Login failed. Please check your credentials.");
                }

            });

            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setOnCloseRequest(event -> {
                // Close both Spring application contexts when the application is shutting down
                ctx.close();
                ctx2.close();
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int processCEngSalary(ComputerEngineer compeng, int hour, BufferedWriter bw) {
    	int value = hour*compeng.getSalaryPerHour()+ComputerEngineer.getDailySalary();
    	try {
	        bw.write(String.valueOf(value));
	        bw.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return value;
	}
	
	public static int processEngSalary(Engineer eng, int hour, BufferedWriter bw) {
		int value = hour*eng.getSalaryPerHour()+Engineer.getDailySalary();
		try {
	        bw.write(String.valueOf(value));
	        bw.newLine();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	public static boolean ifContains(String[] stringarray, String string) {
		for (String item : stringarray) {
            if(item.equals(string)) {
            	return true;
            }
        }
		return false;
	}
    
	private static ArrayList<String> arrangeTasks(String[] tasks) {
		ArrayList<String> task1List = new ArrayList<>();
        ArrayList<String> task2List = new ArrayList<>();
        ArrayList<String> task3List = new ArrayList<>();
        for (String task : tasks) {
            if (task.equals("Task1")) {
                task1List.add(task);
            } else if (task.equals("Task2")) {
                task2List.add(task);
            } else if (task.equals("Task3")) {
                task3List.add(task);
            }
        }
        ArrayList<String> result = new ArrayList<>();
        int totalHours = 0;
        while (!task1List.isEmpty() || !task2List.isEmpty() || !task3List.isEmpty()) {
            ArrayList<String> sequence = new ArrayList<>();
            while (!task3List.isEmpty() && totalHours + CompanyConfig.getHourOfTask3() <= 10) {
            	if (totalHours + CompanyConfig.getHourOfTask3() == 10) {
            		totalHours += CompanyConfig.getHourOfTask3();
            		sequence.add(task3List.remove(0));
            		break;
            	} else {
            		if (totalHours + 2*CompanyConfig.getHourOfTask3()-1 > 10) {
            			totalHours += CompanyConfig.getHourOfTask3();
            		} else {
            			if (task3List.size() == 1) {
            				totalHours += CompanyConfig.getHourOfTask3();
            			} else {
            				totalHours += CompanyConfig.getHourOfTask3()-1;
            			}
            		}
            	}
                sequence.add(task3List.remove(0));
            }
            while (!task2List.isEmpty() && totalHours + CompanyConfig.getHourOfTask2() <= 10) {
            	if (totalHours + CompanyConfig.getHourOfTask2() == 10) {
            		totalHours += CompanyConfig.getHourOfTask2();
            		sequence.add(task2List.remove(0));
            		break;
            	} else {
            		if (totalHours + 2*CompanyConfig.getHourOfTask2()-1 > 10) {
            			totalHours += CompanyConfig.getHourOfTask2();
            		} else {
            			if (task2List.size() == 1) {
            				totalHours += CompanyConfig.getHourOfTask2();
            			} else {
            				totalHours += CompanyConfig.getHourOfTask2()-1;
            			}
            		}
            	}
                sequence.add(task2List.remove(0));
            }
            while (!task1List.isEmpty() && totalHours + CompanyConfig.getHourOfTask1()-1 <= 10) {
            	if (totalHours + CompanyConfig.getHourOfTask1() == 10) {
            		totalHours += CompanyConfig.getHourOfTask1();
            		sequence.add(task1List.remove(0));
            		break;
            	} else {
            		totalHours += CompanyConfig.getHourOfTask1()-1;
            	}
                sequence.add(task1List.remove(0));
            }
            result.addAll(sequence);
            if (totalHours == 10) {
                totalHours = 0;
            }
        }
        return result;
	}
	
    public static void main(String[] args) {

        launch(args);

    }
}