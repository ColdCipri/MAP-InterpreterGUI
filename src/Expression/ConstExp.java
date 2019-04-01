package Expression;

import Domain.IHeap;
import Domain.ISymbTbl;
import Exception.OperandException;
import Exception.ZeroDivisionException;

public class ConstExp implements IExp{
	int s;
	
	public ConstExp(int val) {
		this.s = val;
	}
	
	@Override
	public int resolve(ISymbTbl st, IHeap heap) throws ZeroDivisionException, OperandException {
		return s;
	}

	@Override
	public boolean isTrue(ISymbTbl st, IHeap heap) throws ZeroDivisionException, OperandException {
		return this.resolve(st, heap) != 0 ? true : false;
	}
	
	public String toString() {
		return Integer.toString(s);
	}

}
