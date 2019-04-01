package Domain;

import java.util.Stack;
import Statement.IStmt;
import javafx.collections.ObservableList;

public class MyStack implements IStack{
	private Stack<IStmt> stack;
	
	public MyStack() {
		stack = new Stack<>();
	}
	
	public MyStack(MyStack s) {
		stack = new Stack<>();
		for (IStmt st: s.stack)
			stack.push(st);
	}
	
	public Stack<IStmt> getStack() {
		return stack;
	}
	
	public void setStack(Stack<IStmt> stack) {
		this.stack = stack;
	}
	
	@Override
	public IStmt pop() {
		return this.stack.pop();
	}

	@Override
	public void push(IStmt stmt) {
		this.stack.push(stmt);
		
	}

	@Override
	public boolean isEmpty() {
		return this.stack.isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Stack<IStmt> getIterator() {
		return (Stack<IStmt>) this.stack.clone();
	}
	
	public String toString() {
		String toPrint = " ExecStack:\r\n";
		boolean empty = true;
		for(int i = stack.size() - 1; i>=0 ; i--) {
			empty = false;
			toPrint += "\t" + stack.get(i).toString() + "\r\n";//afiseaza valoarile de pe stack
		}
		
		if (empty)
			toPrint += "\tEmpty\n";
		
		return toPrint;
	}
	
	@Override
	public ObservableList<String> toObservableList(ObservableList<String> list){
		list.clear();
		
		for (int i = stack.size() - 1 ; i >= 0 ; i--) {
			list.add(stack.get(i).toString());
		}
		return list;
	}
}




