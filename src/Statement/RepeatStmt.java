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
import Expression.BoolExp;
import Expression.ConstExp;
import Expression.IExp;

public class RepeatStmt implements IStmt{
	IStmt stmt;
	IExp exp;
	
	public RepeatStmt(IStmt stmt, IExp exp) {
		this.setStmt(stmt);
		this.setExp(exp);
	}

	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, FileNotFoundException,
			DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException,
			InvalidAddressException, InvalidSignException {
		WhileStmt ws1 = new WhileStmt(new BoolExp("!=",
									  this.exp,
									  new ConstExp(1)
									  ), stmt);
		CompStmt cs1 = new CompStmt(stmt,ws1);
		state.stack.push(cs1);
		return null;
	}
	
	public IStmt getStmt() {
		return stmt;
	}

	public void setStmt(IStmt stmt) {
		this.stmt = stmt;
	}

	public IExp getExp() {
		return exp;
	}

	public void setExp(IExp exp) {
		this.exp = exp;
	}
	
	@Override
	public String toString() {
		return "(repeat " + stmt + " until " + exp + ")";
	}


}
