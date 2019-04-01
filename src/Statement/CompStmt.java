package Statement;

import Domain.IPrgState;
import Domain.PrgState;
import Exception.OperandException;
import Exception.ZeroDivisionException;

public class CompStmt implements IStmt{
	IStmt first;
	IStmt second;
	
	public CompStmt(IStmt first, IStmt second) {
		this.setStatement1(first);
		this.setStatement2(second);
	}
	
	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException {
		state.stack.push(second);
		state.stack.push(first);
		//return state;
		return null;
	}

	public IStmt getStatement1() {
		return first;
	}
		 
	public IStmt getStatement2() {
		return second;
	}
		 
	public void setStatement1(IStmt first) {
		this.first = first;
	}
	
	public void setStatement2(IStmt second) {
		this.second = second;
	}
	
	public String toString() {
    	return ""+ first.toString() + " ; " + second.toString() + "";
    }
}
