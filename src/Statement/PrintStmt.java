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

public class PrintStmt implements IStmt{
	IExp exp;
	
	public PrintStmt(IExp exp) {
		this.setExp(exp);
	}
	
	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException {
		state.output.addOutput(getExp().resolve(state.symbtbl.peek(), state.heap));
		//return state;
		return null;
	}

	public IExp getExp() {
        return exp;
    }

    public void setExp(IExp exp) {
        this.exp = exp;
    }
    
    public String toString() {
    	return "print (" + exp.toString() + ")";
    }
	
}
