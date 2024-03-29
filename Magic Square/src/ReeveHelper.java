import BreezySwing.*;
import javax.swing.*;
//helper class
public class ReeveHelper {
	
	public static boolean isInt(double x) {
		if((int)x == x) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isPositive(int x) {
		if(x > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isPositive(double x) {
		if(x > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isPositive(IntegerField field) {
		int x = field.getNumber();
		if(x > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isPositive(DoubleField field) {
		double x = field.getNumber();
		if(x > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isNegative(IntegerField field) {
		int x = field.getNumber();
		if(x < 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isNegative(DoubleField field) {
		double x = field.getNumber();
		if(x < 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isZero(IntegerField field) {
		int x = field.getNumber();
		if(x == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isZero(DoubleField field) {
		double x = field.getNumber();
		if(x == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isValidNumber(IntegerField field) {
		if(field.isValidNumber()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isValidNumber(DoubleField field) {
		if(field.isValidNumber()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isValidNumber(JTextField field) {
		boolean valid = true;
		try {
			Integer.parseInt(field.getText());
		}catch (Exception e) {
			valid = false;
		}
		return valid;
	}
	
	
	public static boolean isBlank(String str) {
		if(str == "" || str.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isBlank(JTextField field) {
		String str = field.getText();
		if(str == "" || str.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static double roundMoney(double x) {
		return Math.round(x * 100.0) / 100.0;	
	}
	
	public static boolean contains(int[][] x, int y) {
		boolean contains = false;
		for(int i = 0; i < x.length; i++) {
			for(int j = 0; j < x.length; j++) {
				if(x[i][j] == y) {
					contains = true;
					break;
				}
			}
		}
		return contains;
	}
	
	public static int sumOfArray(int[] x) {
		int sum = 0;
		for(int i = 0; i < x.length; i++) {
			sum += x[i];
		}
		return sum;
	}
	
	public static int random(int min, int max) {
		int rand = (int)(Math.random() * (max - min + 1)) + min;
		return rand;
	}
	
	public static boolean isEven(int x) {
		if(x % 2 == 0) {
			return true;
		}else {
			return false;
		}
	}
	
}