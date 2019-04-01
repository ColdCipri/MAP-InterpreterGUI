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
import Expression.ArithExp;
import Expression.IExp;

public class CondAssignStmt implements IStmt{
	private String s;
	private IExp exp0;
	private IExp exp1;
	private IExp exp2;
	private IExp exp3;
	
	public CondAssignStmt(String s, IExp exp0, IExp exp1, IExp exp2, IExp exp3) {
		this.setStmt(s);
		this.setExp0(exp0);
		this.setExp1(exp1);
		this.setExp2(exp2);
		this.setExp3(exp3);
	}


	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, FileNotFoundException,
			DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException,
			InvalidAddressException, InvalidSignException {
		IfStmt if1 = new IfStmt(new ArithExp('-',
											this.exp1,
											this.exp0
								),
								new AssignStmt(s, this.exp2),
								new AssignStmt(s, this.exp3)									
				);
		state.stack.push(if1);
		return null;
	}
	
	public IExp getExp0() {
		return exp0;
	}

	public void setExp0(IExp exp0) {
		this.exp0 = exp0;
	}
		
	public IExp getExp1() {
		return exp1;
	}

	public void setExp1(IExp exp1) {
		this.exp1 = exp1;
	}

	public IExp getExp2() {
		return exp2;
	}

	public void setExp2(IExp exp2) {
		this.exp2 = exp2;
	}

	public IExp getExp3() {
		return exp3;
	}

	public void setExp3(IExp exp3) {
		this.exp3 = exp3;
	}

	public String getStmt() {
		return s;
	}

	public void setStmt(String s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return s + "=(" + exp0 + "," + exp1 + ")?" + exp2 + ":" + exp3 + "\n";
	}

}
