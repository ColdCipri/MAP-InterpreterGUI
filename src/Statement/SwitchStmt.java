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
import Expression.IExp;

public class SwitchStmt implements IStmt{
	private IExp ifExp;
	private IExp firstExp;
	private IExp secondExp;
	private IStmt case1;
	private IStmt case2;
	private IStmt defaultCase;
	
	public SwitchStmt(IExp ifExp, IExp firstExp, IStmt case1, IExp secondExp,  IStmt case2, IStmt defaultCase) {
		this.setIfExp(ifExp);
		this.setFirstExp(firstExp);
		this.setCase1(case1);
		this.setSecondExp(secondExp);
		this.setCase2(case2);
		this.setDefaultCase(defaultCase);
	}

	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, FileNotFoundException,
			DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException,
			InvalidAddressException, InvalidSignException {
		IfStmt if1 = new IfStmt(new BoolExp("==", this.ifExp, this.firstExp),
				this.case1,
				new IfStmt( new BoolExp("==", this.ifExp, this.secondExp),
						this.case2,
						this.defaultCase
						)
				);
		state.stack.push(if1);
		return null;
	}
	
	public void setIfExp(IExp exp) { this.ifExp = exp; }
	
	public void setFirstExp(IExp exp) { this.firstExp = exp; }
	
	public void setSecondExp(IExp exp) { this.secondExp = exp; }
	
	public IExp getIfExp() { return this.ifExp; }
	
	public IExp getFirstExp() { return this.firstExp; }
	
	public IExp getSecondExp() { return this.secondExp; }
	
	public void setCase1(IStmt stmt) { this.case1 = stmt; }
	
	public void setCase2(IStmt stmt) { this.case2 = stmt; }
	
	public void setDefaultCase(IStmt stmt) { this.defaultCase = stmt; }
	
	public IStmt getCase1() { return this.case1; }
	
	public IStmt getCase2() { return this.case2; }
	
	public IStmt getDefaultCase() { return this.defaultCase; }
	
	@Override
	public String toString() {
		return "switch(" + ifExp + ")\n(case  (" + firstExp + ") " + case1
				+ ")\n(case (" + secondExp + ") " + case2 + ")\n(default" + defaultCase + ") \n";
	}

}
