package Statement;

import java.io.FileNotFoundException;
import java.io.IOException;

import Domain.IPrgState;
import Domain.MyStack;
import Domain.PrgState;
import Domain.SymbTbl;
import Exception.DuplicateFileException;
import Exception.InvalidAddressException;
import Exception.InvalidFileException;
import Exception.InvalidSignException;
import Exception.InvalidSymbolException;
import Exception.NullAddressException;
import Exception.OperandException;
import Exception.ZeroDivisionException;

public class ForkStmt implements IStmt {
	public IStmt stmt;
	
	public ForkStmt(IStmt stmt) {
		this.stmt = stmt;
	}

	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, FileNotFoundException,
			DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException,
			InvalidAddressException, InvalidSignException {
		synchronized (this){
			IPrgState newState = new PrgState(
												new MyStack(),
												state.output,
												SymbTbl.cloneStack(state.symbtbl),
												state.filetbl,
												state.heap,
												state.barrierTable,
												state.semaphoreTable
											 );
			newState.addStatement(this.stmt);
			return newState;
		}
	}

	public String toString() {
		return "fork (" + this.stmt.toString() + ") ";
	}
}
