package Repository;

import java.io.IOException;
import java.util.List;

import Domain.IPrgState;
import Domain.PrgState;
import javafx.collections.ObservableList;

public interface IRepo {
	public void addPrgState(IPrgState state);
	public List<PrgState> getPrgState();
	void logPrgState(IPrgState st) throws IOException;
	public int getSize();
	public void clean();
	void setPrgState(List<PrgState> prgList);
	IPrgState getFirstPrgState();
	ObservableList<String> toObservableList(ObservableList<String> list);
}
