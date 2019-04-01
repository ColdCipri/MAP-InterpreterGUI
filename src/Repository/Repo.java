package Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Domain.IPrgState;
import Domain.PrgState;
import javafx.collections.ObservableList;

public class Repo implements IRepo{
	public ArrayList<PrgState> list;
	public String logFilePath;
	
	public Repo(String logFilePath) {
		this.list = new ArrayList<>();
		this.logFilePath = logFilePath;
	}
	
	@Override
	public void addPrgState(IPrgState state) {
		list.add((PrgState) state);
	}

	@Override
	public List<PrgState> getPrgState() {
		return this.list;
	}


	@Override
	public int getSize() {
		return this.list.size();
	}

	@Override
	public void clean() {
		for (int i = 1; i < list.size(); i++)
			this.list.remove(i);
	}

	@Override
	public void logPrgState(IPrgState st) throws IOException {
		//logs the program states to the log file
		PrintWriter printWriter;
		printWriter = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
		printWriter.print(st.toString());
		printWriter.close();		
	}
	
	@Override
	public void setPrgState(List<PrgState> lst) {
		this.list = (ArrayList<PrgState>) lst;
	}
	
	@Override
	public IPrgState getFirstPrgState() {
		return list.get(0);
	}

	@Override
	public ObservableList<String> toObservableList(ObservableList<String> list) {
		list.clear();
		for (PrgState state : this.list)
			list.add(state.GUID);
		
		return list;
	}
	
}
