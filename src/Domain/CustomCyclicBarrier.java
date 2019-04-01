package Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomCyclicBarrier {
	public List<String> barriers;
	public int parties;
	
	public CustomCyclicBarrier(int parties) {
		this.parties = parties;
		barriers = new ArrayList<String>();
	}
	
	public boolean containsGUID(String guid) {
		return barriers.contains(guid);
	}
	
	public boolean addGUID(String guid) {
		return barriers.add(guid);
	}
	
	public String listToString() {
		if (this.barriers.size() == 0)
			return "Empty";
		return this.barriers.stream().map(i -> i + ", ").collect(Collectors.joining());
	}
	
	
	
}
