package Domain;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.management.openmbean.InvalidKeyException;

import javafx.collections.ObservableList;

public class BarrierTable implements IBarrierTable{
	public Map<Integer, CustomCyclicBarrier> barriers;
	
	public BarrierTable() {
		this.barriers = new HashMap<>();
	}

	@Override
	public CustomCyclicBarrier getBarrier(int key) {
		if (contains(key))
			return barriers.get(key);
		throw new InvalidKeyException("Key" + key + "not found in BarrierTable");
	}

	@Override
	public void addBarrier(int key, CustomCyclicBarrier barrier) {
		this.barriers.put(key, barrier);
		
	}

	@Override
	public void removeBarrier(int key) {
		this.barriers.put(key, null);
		
	}

	@Override
	public int count() {
		return this.barriers.size();
	}

	@Override
	public boolean contains(int key) {
		return this.barriers.containsKey(key);
	}

	@Override
	public IBarrierTable sync() {
		this.barriers = Collections.synchronizedMap(barriers);
		return this;
	}

	@Override
	public int getNextIndex() {
		int nextIndex = 0;
		try {
			nextIndex = barriers.keySet().stream().max(Comparator.naturalOrder()).get() +1;
		} catch (Exception e) {
			
		}
		return nextIndex;
	}

	@Override
	public ObservableList<BarrierTablePair> toObservableList(ObservableList<BarrierTablePair> list) {
		list.clear();
		for (Integer key : barriers.keySet()) {
			CustomCyclicBarrier barrier = this.barriers.get(key);
			list.add(new BarrierTablePair(key, barrier.parties, barrier.listToString()));
		}
		return list;
	}

	public String toString() {
		String toPrint = "\r\n Barrier: \r\n";
		boolean empty = true;
		for (Integer k : this.barriers.keySet()) {
			toPrint += k + "-"+ this.barriers.get(k).parties + " parties " + this.barriers.get(k).listToString() + "\n";
			empty = false;
		}
		if (empty)
			toPrint += "\t Empty\r\n";
		return toPrint;
	}
}
