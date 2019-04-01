package Domain;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.management.openmbean.InvalidKeyException;

import javafx.collections.ObservableList;

public class SemaphTbl implements ISemaphTbl {
	public Map<Integer, CustomSemaphore> semaphores;
	
	public SemaphTbl() {
		this.semaphores = new HashMap<>();
	}

	@Override
	public int getNextIndex() {
		int nextIndex = 0;
		try {
			nextIndex = semaphores.keySet().stream().max(Comparator.naturalOrder()).get() +1;
		} catch (Exception e) {
			
		}
		return nextIndex;
	}
	
	@Override
	public void addSemaphore(int key, CustomSemaphore semaphore) {
		this.semaphores.put(key, semaphore);
	}

	@Override
	public boolean contains(int key) {
		return this.semaphores.containsKey(key);
	}
	
	@Override
	public CustomSemaphore getSemaphore(int key) {
		if (contains(key))
			return semaphores.get(key);
		throw new InvalidKeyException("Key" + key + "not found in SemaphoreTable");
	}
	
	@Override
	public ObservableList<SemaphoreTableTuple> toObservableList(ObservableList<SemaphoreTableTuple> list) {
		list.clear();
		for (Integer key : semaphores.keySet()) {
			CustomSemaphore semaphore = this.semaphores.get(key);
			list.add(new SemaphoreTableTuple(key, semaphore.permits, semaphore.listToString(), semaphore.permits2));
		}
		return list;
	}


}
