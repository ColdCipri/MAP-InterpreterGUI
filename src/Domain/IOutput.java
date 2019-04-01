package Domain;

import java.util.List;

import javafx.collections.ObservableList;

public interface IOutput {
	public void addOutput(int s);
	public List<Integer> getIterator();
	public ObservableList<String> toObservableList(ObservableList<String> list);
}
