// This page is the operations page. search, show, add, modify and delete.

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Operations {
	
	Statement statement = JDBC_Connection.getconnection();
	Scanner in = new Scanner(System.in);
	checkFunctions cf = new checkFunctions();
	
	public String search(String id) {
		StringBuilder result = new StringBuilder();
		String sql = "select * from student where stu_id = \'" + id + "\'";
		ResultSet resultSet = null;
		
		try {
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				result.append("Student ID\tName\tJava\tPython\tC\tTotalScore");
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
	
	public String show() {
		String sql = "select * from student";
		StringBuilder result = new StringBuilder();
		ResultSet resultSet = null;
		
		try {
			resultSet = statement.executeQuery(sql);
			boolean first = true;
			
			while(resultSet.next()) {
				if(first) {
					result.append("Student ID\tName\tJava\tPython\tC\tTotalScore");
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
	
	public void add() {
		//Scanner input = new Scanner(System.in);
		boolean duplicate = true;
		String id = "";
		while(duplicate) {
			System.out.print("Please input the Student ID(Consists of 10 numbers, for example: 1111111111):");
			id = in.nextLine();
			try {
				if(cf.checkId(id)) {
					String exist = search(id);
					if(exist.equals("")) {
						duplicate = false;
					}else {
						System.out.println("The Student ID is exist! Please input another one!");
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
				String[] scores = cf.validScore(score);
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
		System.out.println("Adding information to the database...");
		
		String sql = "insert into student values(\'" + id + "\', \'" + name + "\', " + Java + "," + Python + "," + C + "," + total + ")";
		try {
			statement.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Adding complete!");
		System.out.println();
		
	}
	
	public void modify() {
		
	}
	
	public void delete() {
		//Scanner input = new Scanner(System.in);
		System.out.print("Please input the Student ID which you want to delete: ");
		String id = in.nextLine();
		try {
			if(cf.checkId(id)) {
				String result = search(id);
				if(result.equals("")) {
					System.out.println("Cannot delete! Because the Student Id is not exist!");
					System.out.println();
				}else {
					System.out.println("This is the information you want to delete:");
					System.out.println(result);
					boolean deleted = false;
					do {
						System.out.print("Are you sure to delete? ('y' / 'n'): ");
						String ch = in.nextLine();
						if(cf.check(ch)) {
							if(ch.equalsIgnoreCase("y")){
								String sql = "delete from student where stu_id = \'" + id + "\'";
								System.out.println("Deleting the information...");
								try {
									statement.executeUpdate(sql);
								}catch(SQLException e) {
									e.printStackTrace();
								}
								System.out.println("The " + id + "'s information has been removed!");
								deleted = true;
							}else if(ch.equalsIgnoreCase("n")) {
								System.out.println("The " + id + "'s information doesn't delete!");
								break;
							}
						}
					}while(!deleted);
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println();
		}
		
	
	}
}
