package Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomSemaphore {
	public Integer permits;
	public List<String> guids;
	public Integer permits2;
	
	public CustomSemaphore(int permits, int permits2) {
		this.permits = permits;
		guids = new ArrayList<String>();
		this.permits2 = permits2;
	}
	
	public String listToString() {
		return guids.stream().map(i-> i = i + ", ").collect(Collectors.joining());
	}
}
