package View;

import java.util.Scanner;

import Domain.IPrgState;

public class View implements IView{
	Scanner sc;

	public View() {
		sc = new Scanner(System.in);
	}
	
	@Override
	public int run() {
		this.displayMenu();
		return getInput();
	}

	@Override
	public void displayMenu() {
		System.out.println("0. Exit\n");
		System.out.println("1. v = 1; print(v)\n");
		System.out.println("2. a = 2 + 3 * 4;\n" + "b = a - 8 / 2 +5\n" + "print(b)\n");
		System.out.println("3. c = 5 - 1\n" + "if (c) { \n\tv = 1 \n}\n else {\n\tv = 2\n" + "print(v)\n");		
	}

	@Override
	public int getInput() {
		try{
			return sc.nextInt();
		}catch (Exception e) {
			System.out.println("Invalid input!");
			return 0;
		}
	}

	@Override
	public void displayResult(int val) {
		System.out.println(val);
	}

	@Override
	public void displayStatus(IPrgState s) {
		System.out.println(s.toString());
	}

	@Override
	public void printMessage(String msg) {
		System.out.println("Message: " + msg);
	}
	
}
