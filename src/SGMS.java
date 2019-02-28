// This is the main page of the program.
/*
 * DataBase Table Student
 * CREATE TABLE Student (
    stu_id CHAR(10) PRIMARY KEY,
    stu_name CHAR(20) NOT NULL,
    Java_score DOUBLE(5,2),
    Python_score DOUBLE(5,2),
    C_score DOUBLE(5,2),
    Total_score DOUBLE(5,2)
);
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SGMS {

	Statement statement = null;
	
	public void MainPage() {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String input = "";
		String choose = "";
		
		statement = JDBC_Connection.getconnection();
		
		do {
			System.out.println("*************************************************");
			System.out.println("Welcome to Student Grades Management System:");
			System.out.println("1. Search Student Infomation by input Student ID;");
			System.out.println("2. Show all the Infomation;");
			System.out.println("3. Add Information;");
			System.out.println("4. Delete Information;");
			System.out.println("0. Quit.");
			System.out.println("*************************************************");
			System.out.print("Please choose the steps by input 0 to 4: ");
			input = in.nextLine();
			try {
				choose = checkChoose(input);
				if(choose.equals("1")) {
					boolean valid = false;
					while(!valid) {
						System.out.print("Please input the student ID: ");
						String id = in.nextLine();
						try {
							if(checkId(id)) {
								String result = search(id);
								if(result.equals("")) {
									System.out.println("Cannot find the student information for id: " + id);
									valid = true;
								}else {
									System.out.println("The student " + id + "'s information is:");
									System.out.println(result);
									valid = true;
								}
							}
						}catch(Exception e) {
							System.out.println(e.getMessage());
							System.out.println();
						}
						
					}
					
				}else if(choose.equals("2")) {
					String result = show();
					if(result.equals("")) {
						System.out.println("There is nothing in the database.");
						System.out.println();
					}else {
						System.out.println("Show all the infomation:");
						System.out.println(result);
						System.out.println();
					}
				}else if(choose.equals("3")) {
					add();
				}else if(choose.equals("4")) {
					delete();
				}else if(choose.equals("0")) {
					System.out.println("Quit! Thank you for using!");
					break;
				}
			}catch (Exception e){
				System.out.println(e.getMessage());
				System.out.println();
			}
			
		}while(!choose.equals("0"));
		
		in.close();
		
		try {
			if(statement != null) {
				statement.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	private String search(String id) {
		StringBuilder result = new StringBuilder();
		String sql = "select * from student where stu_id = \'" + id + "\'";
		ResultSet resultSet = null;
		
		try {
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				result.append("Student ID\t\tName\tJava\tPython\tC\t\tTotalScore");
				result.append("\n" + resultSet.getString("stu_id"));
				result.append("\t" + resultSet.getString("stu_name"));
				result.append("\t" + resultSet.getString("Java_score"));
				result.append("\t" + resultSet.getString("Python_score"));
				result.append("\t" + resultSet.getString("C_score"));
				result.append("\t" + resultSet.getString("Total_score") + "\n");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		JDBC_Close.close(resultSet);
		
		return result.toString();
	}
	
	private String show() {
		String sql = "select * from student";
		StringBuilder result = new StringBuilder();
		ResultSet resultSet = null;
		
		try {
			resultSet = statement.executeQuery(sql);
			boolean first = true;
			
			while(resultSet.next()) {
				if(first) {
					result.append("Student ID\t\tName\tJava\tPython\tC\t\tTotalScore");
					first = false;
				}
				result.append("\n" + resultSet.getString("stu_id"));
				result.append("\t" + resultSet.getString("stu_name"));
				result.append("\t" + resultSet.getString("Java_score"));
				result.append("\t" + resultSet.getString("Python_score"));
				result.append("\t" + resultSet.getString("C_score"));
				result.append("\t" + resultSet.getString("Total_score") + "\n");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		JDBC_Close.close(resultSet);
		
		return result.toString();
	}
	
	private void add() {
		Scanner in = new Scanner(System.in);
		boolean duplicate = true;
		String id = "";
		while(duplicate) {
			System.out.print("Please input the Student ID(Consists of 10 numbers, for example: 1111111111):");
			id = in.nextLine();
			try {
				if(checkId(id)) {
					String exist = search(id);
					if(exist.equals("")) {
						duplicate = false;
					}else {
						System.out.println("The student ID is exist! Please input another one!");
						System.out.println();
					}
				}
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println();
			}
			
		}
		
		System.out.print("Please input the Student Name: ");
		String name = in.nextLine();
		
		boolean valid = false;
		double Java = 0.0;
		double Python = 0.0;
		double C = 0.0;
		double total = 0.0;
		
		while(!valid) {
			System.out.print("Please input the Java, Python and C Score seperate by space: ");
			String score = in.nextLine();
			try {
				String[] scores = validScore(score);
				Java = Double.parseDouble(scores[0]);
				Python = Double.parseDouble(scores[1]);
				C = Double.parseDouble(scores[2]);
				total = Java + Python + C;
				valid = true;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println();
			}
		}
		
		String sql = "insert into student values(\'" + id + "\', \'" + name + "\', " + Java + "," + Python + "," + C + "," + total + ")";
		try {
			statement.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		in.close();
	}
	
	private void delete() {
		
	}
	
	private String checkChoose(String input) {
		String choose = input;
		if(!isNum(choose)) {
			System.out.println("Don't input the Strings! Please input again!");
		}else {
			if(Integer.parseInt(choose) < 0 || Integer.parseInt(choose) > 4) {
				System.out.println("Please input the number between 0 to 4! Please input again!");
			}
		}
		return choose;
	}
	
	private boolean checkId(String id) {
		boolean check = false;
		if(id.length() != 10) {
			System.out.println("The length of the Student ID must be 10! Please input again!");
		}else if(!isNum(id)) {
			System.out.println("The Student ID only consists of numbers! Please input again!");
		}else {
			check = true;
		}
		
		return check;
	}
	
	private String[] validScore(String score) {
		String[] input = score.split(" ");
		if(input.length != 3) {
			System.out.println("You should input Java, Python and C Score at the same time and separate them by space! Please input again!");
		}else {
			if(!isDouble(input[0]) || !isDouble(input[1]) || !isDouble(input[2])) {
				System.out.println("The score should be numbers! Please input again!");
			}
		}
		
		return input;
	}
	
	private boolean isNum(String input) {
		for(int i = 0; i < input.length(); i++) {
			if(!Character.isDigit(input.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isDouble(String score) {
		if (null == score || "".equals(score)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(score).matches();
	}
}

