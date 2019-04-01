package Statement;

import Domain.IPrgState;
import Domain.PrgState;
import Exception.InvalidAddressException;
import Exception.InvalidSignException;
import Exception.InvalidSymbolException;
import Exception.NullAddressException;
import Exception.OperandException;
import Exception.ZeroDivisionException;
import Expression.IExp;

public class AssignStmt implements IStmt{
	String id;
	IExp exp;
	
	public AssignStmt(String id, IExp exp) {
		this.id = id;
		this.setExp(exp);
	}
	
	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException {
		state.symbtbl.peek().addSymbol(id, this.exp.resolve(state.symbtbl.peek(), state.heap));
		//return state;
		return null;
	}

	public IExp getExp() {
		return this.exp;
	}
	
	public void setExp(IExp exp) {
		this.exp = exp;		
	}

	 public String toString() {
	   	return id + " = " + exp.toString();
	 }
	
}
