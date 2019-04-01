package View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Commands.Command;
import Controller.Controller;
import Domain.PrintCallBack;
import Exception.InvalidStateException;
import Exception.OperandException;
import Exception.ZeroDivisionException;
import javafx.collections.ObservableList;


public class TextMenu {
	private Map<String, Command> commands;
	
	public TextMenu() {
		commands = new HashMap<>();
	}
	
	public void addCommand(Command c) {
		commands.put(c.getKey(), c);
	}
	
	private void printmenu() {
		for (Command com : commands.values()) {
			String line = String.format("%4s : %s", com.getKey(), com.getDescription());
			System.out.println(line);
		}
	}
	
	PrintCallBack printCallBack = new PrintCallBack() {
		@Override
		public void printCallBack(String print) {
			System.out.println(print);
		}
	};
	
	@SuppressWarnings("resource")
	public void show() throws InvalidStateException, ZeroDivisionException, OperandException {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			printmenu();
			System.out.println("Input the option: ");
			String key = scanner.nextLine();
			Command com = commands.get(key);
			
			if (com.equals(null)) {
				System.out.println("Invalid option");
				continue;
			}
			
			com.setCallBack(this.printCallBack);
			com.execute();
		}
	}
	
	public Controller getController (String key) {
		return commands.get(key).getController();
	}
	
	public ObservableList<String> toObservableList(ObservableList<String> list){
		if (list.size() > 0)
			list.remove(0, list.size() -1);
		for (Command com : commands.values()) 
			list.add(String.format("%4s : %s", com.getKey(), com.getDescription()));
		return list;
	}
}
