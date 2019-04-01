package Domain;

import javafx.collections.ObservableList;

public interface IBarrierTable {

	CustomCyclicBarrier getBarrier(int key);
	void addBarrier(int key, CustomCyclicBarrier barrier);
	void removeBarrier(int key);
	String toString();
	int count();
	boolean contains(int key);
	IBarrierTable sync();
	int getNextIndex();
	ObservableList<BarrierTablePair> toObservableList(ObservableList<BarrierTablePair> list);
}
