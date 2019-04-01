package Commands;

import Controller.Controller;
import Controller.IController;
import Domain.PrintCallBack;

public class RunExample extends Command{
	IController c;
	
	public RunExample(String key, String description, Controller c) {
		super(key, description);
		
		this.c = c;
	}

	@Override
	public void execute() {
		c.allSteps();
		
	}
	
	public void setCallBack (PrintCallBack callback){
		super.setCallBack(callback);
		((Controller) c).callback = callback;
	}

	@Override
	public Controller getController() {
		return (Controller) c;
	}

}
