package View;

import Domain.IPrgState;

public interface IView {
	int run();
	void displayMenu();
	int getInput();
	void displayResult(int val);
	void displayStatus(IPrgState s);
	void printMessage(String msg);
	
	}
