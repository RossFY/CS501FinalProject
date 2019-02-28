// This is the Main class

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	static Statement statement = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int choose = 0;
		
		statement = JDBC_Connection.getconnection();
		
		do {
			System.out.println("*************************************************");
			System.out.println("Student Grades Management System:");
			System.out.println("1. Search Student Infomation by input Student ID;");
			System.out.println("2. Search all the Infomation;");
			System.out.println("3. Add Information;");
			System.out.println("4. Delete Information;");
			System.out.println("0. Quit.");
			System.out.println("*************************************************");
			System.out.println();
			
			choose = 
		}while(choose != 0);
	}

}
