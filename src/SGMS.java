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

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SGMS {

	Statement statement = null;
	Scanner in = new Scanner(System.in);
	Operations op = new Operations();
	checkFunctions cf = new checkFunctions();
	
	public void MainPage() {
		// TODO Auto-generated method stub
		//Scanner in = new Scanner(System.in);
		String input = "";
		String choose = "";
		
		statement = JDBC_Connection.getconnection();
		
		do {
			System.out.println("*************************************************");
			System.out.println("Welcome to Student Grades Management System:");
			System.out.println("1. Search Student Infomation by input Student ID;");
			System.out.println("2. Show all the Infomation;");
			System.out.println("3. Add Information;");
			System.out.println("4. Modify Information by input Student ID;");
			System.out.println("5. Delete Information;");
			System.out.println("0. Quit.");
			System.out.println("*************************************************");
			System.out.print("Please choose the steps by input 0 to 5: ");
			input = in.nextLine();
			try {
				choose = cf.checkChoose(input);
				if(choose.equals("1")) {
					boolean valid = false;
					while(!valid) {
						System.out.print("Please input the student ID: ");
						String id = in.nextLine();
						try {
							if(cf.checkId(id)) {
								String result = op.search(id);
								if(result.equals("")) {
									System.out.println("Cannot find the student information for id: " + id);
									System.out.println();
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
					String result = op.show();
					if(result.equals("")) {
						System.out.println("There is nothing in the database.");
						System.out.println();
					}else {
						System.out.println("Show all the infomation:");
						System.out.println(result);
					}
				}else if(choose.equals("3")) {
					op.add();
				}else if(choose.equals("4")) {
					op.modify();
				}else if(choose.equals("5")) {
					op.delete();
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
	
	
	
}

