// This page is all the valid check functions.

import java.util.regex.Pattern;

public class checkFunctions {
	
	public boolean check(String ch) {
		boolean valid = false;
		if(!ch.equalsIgnoreCase("y") && !ch.equalsIgnoreCase("n")) {
			System.out.println("Please input 'y' or 'n'! Please input again!");
		}else {
			valid = true;
		}
		return valid;
	}
	
	public String checkChoose(String input) {
		String choose = input;
		if(!isNum(choose)) {
			System.out.println("Don't input the Strings! Please input again!");
		}else {
			if(Integer.parseInt(choose) < 0 || Integer.parseInt(choose) > 5) {
				System.out.println("Please input the number between 0 to 5! Please input again!");
			}
		}
		
		return choose;
	}
	
	public boolean checkId(String id) {
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
	
	public String[] validScore(String score) {
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
	
	public boolean isNum(String input) {
		for(int i = 0; i < input.length(); i++) {
			if(!Character.isDigit(input.charAt(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isDouble(String score) {
		if (null == score || "".equals(score)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		
		return pattern.matcher(score).matches();
	}
}
