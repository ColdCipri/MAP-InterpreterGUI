package Statement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

import Domain.CustomSemaphore;
import Domain.IPrgState;
import Domain.PrgState;
import Exception.DuplicateFileException;
import Exception.InvalidAddressException;
import Exception.InvalidBarrierException;
import Exception.InvalidFileException;
import Exception.InvalidSignException;
import Exception.InvalidSymbolException;
import Exception.NullAddressException;
import Exception.OperandException;
import Exception.ZeroDivisionException;
import Expression.IExp;

public class NewSemaphStmt implements IStmt{
	private String var;
	private IExp exp1;
	private IExp exp2;
	
	public NewSemaphStmt(String var, IExp exp1, IExp exp2) {
		this.setVar(var);
		this.setExp1(exp1);
		this.setExp2(exp2);
	}

	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, FileNotFoundException,
			DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException,
			InvalidAddressException, InvalidSignException, InvalidBarrierException {
			ReentrantLock lock = new ReentrantLock();

			lock.lock();
			try {
				int number1 = exp1.resolve(state.symbtbl.peek(), state.heap);
				int number2 = exp2.resolve(state.symbtbl.peek(), state.heap);
				int nextFreeLocation = state.semaphoreTable.getNextIndex();
				if (!state.symbtbl.peek().containsKey(var)) {
					state.symbtbl.peek().addSymbol(var, nextFreeLocation);
				} else {
					state.semaphoreTable.addSemaphore(nextFreeLocation, new CustomSemaphore(number1, number2));
				}
			} finally {
				lock.unlock();
			}
			
		return null;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
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

	@Override
	public String toString() {
		return "newSemaphore (" + var + ", " + exp1 + ", " + exp2 + ")";
	}
	
	

}
