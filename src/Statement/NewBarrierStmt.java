package Statement;

import java.io.FileNotFoundException;
import java.io.IOException;

import Domain.CustomCyclicBarrier;
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

public class NewBarrierStmt implements IStmt{
	String varName;
	IExp exp;
	
	public NewBarrierStmt(String varName, IExp exp) {
		this.varName = varName;
		this.exp = exp;
	}

	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, FileNotFoundException,
			DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException,
			InvalidAddressException, InvalidSignException {
		synchronized (this) {
			int number = exp.resolve(state.symbtbl.peek(), state.heap);
			
			int nextFreeLocation = state.barrierTable.getNextIndex();
			if (!state.symbtbl.peek().containsKey(varName)) {
				state.symbtbl.peek().addSymbol(varName, nextFreeLocation);
			} else {
				state.barrierTable.addBarrier(nextFreeLocation, new CustomCyclicBarrier(number));
			}
			return null;
		}		
	}
	
	@Override
	public String toString() {
		return "newBarrier( " + varName + ", " + exp.toString() + ")";
	}

}
