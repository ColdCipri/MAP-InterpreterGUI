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

public class IfStmt implements IStmt{
	private IExp exp;
	private IStmt thenS;
	private IStmt elseS;
	
	public IfStmt(IExp exp, IStmt thenS, IStmt elseS) {
		this.setExp(exp);
		this.setTrue(thenS);
		this.setFalse(elseS);
	}
	
	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException {
		if(getExp().isTrue(state.symbtbl.peek(), state.heap)) {
			state.stack.push(thenS);
		}
		else {
			state.stack.push(elseS);
		}
		//return state;
		return null;
	}
	
	public IExp getExp() {
        return exp;
    }

    public void setExp(IExp exp) {
        this.exp = exp;
    }

    public IStmt getTrue() {
        return thenS;
    }

    public void setTrue(IStmt thenS) {
        this.thenS = thenS;
    }

    public IStmt getFalse() {
        return elseS;
    }

    public void setFalse(IStmt elseS) {
        this.elseS = elseS;
    }
    
    public String toString() {
        return "if (" + exp.toString() +") { " + thenS.toString() +" } " + "else { " + elseS.toString() +" }";
    }
	
}
