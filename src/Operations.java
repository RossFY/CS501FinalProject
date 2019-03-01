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
			System.out.print("Please input the Student ID(Consists of 10 numbers, for example: 1111111111): ");
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
		boolean modified = false;
		do {
			System.out.print("Please input the Student ID which you want to modify: ");
			String id = in.nextLine();
			try {
				if(cf.checkId(id)) {
					String result = search(id);
					if(result.equals("")) {
						System.out.println("Cannot modify! Because the Student ID is not exist!");
						System.out.println();
						break;
					}else {
						System.out.println("This is the information you want to modify:");
						System.out.println(result);
						
						
						ResultSet resultSet1 = null;
						ResultSet resultSet2 = null;
						ResultSet resultSet3 = null;
						ResultSet resultSet4= null;
						ResultSet resultSet5 = null;
						
						String name = "";
						double Java = 0.0;
						double Python = 0.0;
						double C = 0.0;
						double Total = 0.0;
						
					
						System.out.print("Are you sure to modify? ('y' / 'n'): ");
						String ch = in.nextLine();
						if(cf.check(ch)) {
							if(ch.equalsIgnoreCase("y")) {
								
								String sql_name = "select stu_name from student where stu_id = \'" + id + "\'";
								resultSet1 = statement.executeQuery(sql_name);
								if(resultSet1.next()) {
									name = resultSet1.getString("stu_name");
								}
								
								String sql_Java = "select Java_score from student where stu_id = \'" + id + "\'";
								resultSet2 = statement.executeQuery(sql_Java);
								if(resultSet2.next()) {
									Java = Double.parseDouble(resultSet2.getString("Java_score"));
								}
								
								String sql_Python = "select Python_score from student where stu_id = \'" + id + "\'";
								resultSet3 = statement.executeQuery(sql_Python);
								if(resultSet3.next()) {
									Python = Double.parseDouble(resultSet3.getString("Python_score"));
								}
								
								String sql_C = "select C_score from student where stu_id = \'" + id + "\'";
								resultSet4 = statement.executeQuery(sql_C);
								if(resultSet4.next()) {
									C = Double.parseDouble(resultSet4.getString("C_score"));
								}
								
								String sql_Total = "select Total_score from student where stu_id = \'" + id + "\'";
								resultSet5 = statement.executeQuery(sql_Total);
								if(resultSet5.next()) {
									Total = Double.parseDouble(resultSet5.getString("Total_score"));
								}
								
								boolean name_changed = false;
								do {
									System.out.print("Do you want to change the name? ('y' / 'n'): ");
									ch = in.nextLine();
									try {
										if(cf.check(ch)) {
											if(ch.equalsIgnoreCase("y")) {
												System.out.println("Please input the name: ");
												name = in.nextLine();
												System.out.println("Name has been changed!");
												System.out.println();
												name_changed = true;
											}else if(ch.equalsIgnoreCase("n")) {
												System.out.println("Name is unchanged!");
												System.out.println();
												break;
											}
										}
									}catch(Exception e) {
										System.out.println(e.getMessage());
										System.out.println();
									}
									
								}while(!name_changed);
								
								boolean Java_changed = false;
								String Js = "";
								do {
									System.out.print("Do you want to change the Java Score? ('y' / 'n'): ");
									ch = in.nextLine();
									try {
										if(cf.check(ch)) {
											if(ch.equalsIgnoreCase("y")) {
												boolean isValid = false;
												do {
													System.out.println("Please input the Java Score: ");
													Js = in.nextLine();
													if(cf.isDouble(Js)) {
														Java = Double.parseDouble(Js);
														System.out.println("Java score has been changed!");
														System.out.println();
														Java_changed = true;
														isValid = true;
													}
													
												}while(!isValid);
												
											}else if(ch.equalsIgnoreCase("n")) {
												System.out.println("Java score is unchanged!");
												System.out.println();
												break;
											}
										}
									}catch(Exception e) {
										System.out.println(e.getMessage());
										System.out.println();
									}
									
								}while(!Java_changed);
								
								boolean Python_changed = false;
								String Ps = "";
								do {
									System.out.print("Do you want to change the Python Score? ('y' / 'n'): ");
									ch = in.nextLine();
									try {
										if(cf.check(ch)) {
											if(ch.equalsIgnoreCase("y")) {
												boolean isValid = false;
												do {
													System.out.println("Please input the Python Score: ");
													Ps = in.nextLine();
													if(cf.isDouble(Ps)) {
														Python = Double.parseDouble(Ps);
														System.out.println("Python score has been changed!");
														System.out.println();
														Python_changed = true;
														isValid = true;
													}
													
												}while(!isValid);
												
											}else if(ch.equalsIgnoreCase("n")) {
												System.out.println("Python score is unchanged!");
												System.out.println();
												break;
											}
										}
									}catch(Exception e) {
										System.out.println(e.getMessage());
										System.out.println();
									}
									
								}while(!Python_changed);
								
								boolean C_changed = false;
								String Cs = "";
								do {
									System.out.print("Do you want to change the C Score? ('y' / 'n'): ");
									ch = in.nextLine();
									try {
										if(cf.check(ch)) {
											if(ch.equalsIgnoreCase("y")) {
												boolean isValid = false;
												do {
													System.out.println("Please input the C Score: ");
													Cs = in.nextLine();
													if(cf.isDouble(Cs)) {
														C = Double.parseDouble(Cs);
														System.out.println("C score has been changed!");
														System.out.println();
														C_changed = true;
														isValid = true;
													}
													
												}while(!isValid);
												
											}else if(ch.equalsIgnoreCase("n")) {
												System.out.println("C score is unchanged!");
												System.out.println();
												break;
											}
										}
									}catch(Exception e) {
										System.out.println(e.getMessage());
										System.out.println();
									}
									
								}while(!C_changed);
								
								Total = Java + Python + C;
								
								String sql1 = "update student set stu_name = \'" + name + "\' where stu_id = \'" + id + "\'";
								String sql2 = "update student set Java_score = " + Java + " where stu_id = \'" + id + "\'";
								String sql3 = "update student set Python_score = " + Python + " where stu_id = \'" + id + "\'";
								String sql4 = "update student set C_score = " + C + " where stu_id = \'" + id + "\'";
								String sql5 = "update student set Total_score = " + Total + " where stu_id = \'" + id + "\'";
								System.out.println("Modifying the information...");
								try {
									statement.executeUpdate(sql1);
									statement.executeUpdate(sql2);
									statement.executeUpdate(sql3);
									statement.executeUpdate(sql4);
									statement.executeUpdate(sql5);
								}catch(SQLException e) {
									e.printStackTrace();
								}
								System.out.println("The " + id + "'s information has been modified!");
								System.out.println();
								modified = true;
								
							}else if(ch.equalsIgnoreCase("n")) {
								System.out.println("The " + id + "'s information doesn't modify!");
								System.out.println();
								break;
							}
						}
						
					}
				}
				
			}catch(Exception e){
				System.out.println(e.getMessage());
				System.out.println();
			}
		}while(!modified);
		
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
								System.out.println();
								deleted = true;
							}else if(ch.equalsIgnoreCase("n")) {
								System.out.println("The " + id + "'s information doesn't delete!");
								System.out.println();
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
