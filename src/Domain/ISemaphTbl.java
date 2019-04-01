package Domain;

import javafx.collections.ObservableList;

public interface ISemaphTbl {
	ObservableList<SemaphoreTableTuple> toObservableList(ObservableList<SemaphoreTableTuple> list);

	int getNextIndex();

	void addSemaphore(int key, CustomSemaphore customSemaphore);

	boolean contains(int key);

	CustomSemaphore getSemaphore(int key);
}