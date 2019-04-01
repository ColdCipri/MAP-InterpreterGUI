package Statement;

import java.io.IOException;

import Domain.IPrgState;
import Domain.PrgState;
import Exception.*;
import Expression.IExp;

public class HeapAllocStmt implements IStmt{
	String varName;
	IExp exp;
	
	public HeapAllocStmt(String varName, IExp exp) {
		this.varName = varName;
		this.exp = exp;
	}
	
	
	@Override
	public IPrgState execute(PrgState state) throws 
			InvalidFileException, IOException, DuplicateFileException, InvalidSymbolException, InvalidAddressException,
			OperandException, ZeroDivisionException, NullAddressException, InvalidSignException {
		int value = exp.resolve(state.symbtbl.peek(), state.heap);
		int address = state.heap.addItem(value);
		state.symbtbl.peek().addSymbol(varName, address);
		
		//return state;
		return null;
	}

	public String toString() {
		return "new( " + this.varName + ", " + this.exp.toString() + ") ";
	}
}
