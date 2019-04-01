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

public class WhileStmt implements IStmt {
	public IExp exp;
	public IStmt stmt;
	
	public WhileStmt(IExp exp, IStmt stmt) {
		this.exp = exp;
		this.stmt = stmt;
	}

	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, FileNotFoundException,
			DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException,
			InvalidAddressException, InvalidSignException {
		if (this.exp.isTrue(state.symbtbl.peek(), state.heap)) {
			state.stack.push(this);
			state.stack.push(stmt);
		}
		//return state;
		return null;
	}

	public String toString() {
		return "while (" + this.exp.toString() + ") {" + this.stmt.toString() + "} ";
	}
}
