package Statement;

import java.io.FileNotFoundException;
import java.io.IOException;

import Domain.IPrgState;
import Domain.PrgState;
import Exception.DuplicateFileException;
import Exception.InvalidAddressException;
import Exception.InvalidFileException;
import Exception.InvalidSignException;
import Exception.InvalidSymbolException;
import Exception.NullAddressException;
import Exception.OperandException;
import Exception.ZeroDivisionException;
import Expression.IExp;

public class HeapWriteStmt implements IStmt{
	String varName;
	IExp value;
	
	public HeapWriteStmt(String varName, IExp value) {
		this.varName = varName;
		this.value = value;
	}
	
	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, FileNotFoundException,
			DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException {
		int val = this.value.resolve(state.symbtbl.peek(), state.heap);
		int address = state.symbtbl.peek().getValueOf(varName);
		state.heap.updateValue(address, val);
		
		//return state;
		return null;
	}

	public String toString() {
		return "wH( " + this.varName + ", " + this.value.toString() + ")";
	}
}
