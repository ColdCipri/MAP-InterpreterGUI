package Domain;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;

public class Output implements IOutput{
	ArrayList<Integer> out;
	
	public Output() {
		out = new ArrayList<>();
	}
	
	public Output(Output o) {
		out = new ArrayList<>(o.out);
	}

	@Override
	public void addOutput(int s) {
		this.out.add(s);		
	}

	@Override
	public List<Integer> getIterator() {
		return this.out;
	}
	
	public String toString() {
		String toPrint = "\r\n Output:\r\n";
		for (int i : out) {
			toPrint +="\t" + i + ", ";
		}
		if (out.size() == 0)
			toPrint += "\tEmpty\r\n";
		return toPrint;
	}
	
	@Override
	public ObservableList<String> toObservableList(ObservableList<String> list){
		list.clear();
		for (Integer i : out ) {
			list.add(i.toString());
		}
		return list;
	}

}
