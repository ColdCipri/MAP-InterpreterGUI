package Commands;

import Controller.Controller;
import Domain.PrintCallBack;

public abstract class Command {
	private String description;
	private String key;
	public PrintCallBack callback;
	
	public Command(String key, String description) {
		this.setDescription(description);
		this.setKey(key);
	}
	
	public void setCallBack(PrintCallBack callback) {
		this.callback = callback;
	}
	public abstract void execute();
	
	public abstract Controller getController();
	public String getDescription() {
		return description;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setDescription(String descr) {
		this.description = descr;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
