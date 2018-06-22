import java.util.Scanner;

public class Testing {

	public static void main(String[] args) {
		char placeholder;
		int a;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please input a string of integers to sort.");
		String data = null;
		data = keyboard.nextLine();
		placeholder = data.charAt(1); 
		a = Character.getNumericValue(placeholder);
		System.out.print(a);

	}

}
