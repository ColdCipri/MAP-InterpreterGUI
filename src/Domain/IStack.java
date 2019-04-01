package Domain;

import java.util.Stack;

import Statement.IStmt;
import javafx.collections.ObservableList;


public interface IStack{
	IStmt pop();
	void push(IStmt stmt);
	boolean isEmpty();
	Stack<IStmt> getIterator();
	ObservableList<String> toObservableList(ObservableList<String> list);
	
}
