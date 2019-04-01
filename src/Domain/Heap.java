package Domain;

import java.util.HashMap;
import java.util.Set;

import Exception.InvalidAddressException;
import Exception.InvalidSignException;
import Exception.InvalidSymbolException;
import Exception.NullAddressException;
import Exception.OperandException;
import Exception.ZeroDivisionException;
import Expression.IExp;
import javafx.collections.ObservableList;

public class Heap implements IHeap {
	public HashMap<Integer, Integer> heap;
	private int nextAddress;
	
	public Heap() {
		this.nextAddress = 1;
		this.heap = new HashMap<>();
	}
	
	public Heap(Heap h) {
		this.heap = new HashMap<>(h.heap);
		this.nextAddress = h.nextAddress;
	}
	
	@Override
	public int addItem(int content) {
		this.heap.put(nextAddress, content);
		this.nextAddress++;
		return this.nextAddress - 1;
	}

	@Override
	public Set<Integer> getKeySey() {
		return this.heap.keySet();
	}

	@Override
	public int getContent(IExp address, ISymbTbl st) throws ZeroDivisionException, OperandException, NullAddressException, InvalidAddressException, InvalidSymbolException, InvalidSignException {
		int actualAddress = address.resolve(st, this);
		if (actualAddress == 0)
			throw new NullAddressException("Address is 0",1);
		if (this.heap.get(actualAddress) == null)
			throw new InvalidAddressException("Invalid address: " + actualAddress, 0);
		return this.heap.get(actualAddress);
	}

	@Override
	public void updateValue(int address, int value) throws NullAddressException, InvalidAddressException {
		if (address == 0)
			throw new NullAddressException("Address is 0!",1);
		if (heap.get(address) == null) 
			throw new InvalidAddressException("Invalid address: " + address, 0);
		heap.put(address, value);

	}

	@Override
	public HashMap<Integer, Integer> getContent() {
		return this.heap;
	}

	@Override
	public void setContent(HashMap<Integer, Integer> c) {
		this.heap = c;
	}
	
	public String toString() {
		String toPrint = "\r\n Heap: \r\n";
		boolean empty = true;
		for (Integer k: this.heap.keySet()) {
			toPrint +="\t" + k + " = " + this.heap.get(k) + "\n";
			empty = false;
		}
		if (empty)
			toPrint += "\tEmpty\r\n";
		return toPrint;
	}
	
	@Override
	public ObservableList<HeapItem> toObservableList(ObservableList<HeapItem> list){
		list.clear();
		for (Integer key : heap.keySet())
			list.add(new HeapItem(key, heap.get(key)));
		return list;
	}

}
