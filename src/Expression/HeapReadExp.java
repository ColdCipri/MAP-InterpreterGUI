package Expression;

import Domain.IHeap;
import Domain.ISymbTbl;
import Exception.InvalidAddressException;
import Exception.InvalidSignException;
import Exception.InvalidSymbolException;
import Exception.NullAddressException;
import Exception.OperandException;
import Exception.ZeroDivisionException;

public class HeapReadExp implements IExp{
	public String value;
	
	public HeapReadExp(String name) {
		this.value = name;
	}
	
	@Override
	public int resolve(ISymbTbl st, IHeap heap) throws ZeroDivisionException, OperandException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException {
		IExp address = new ConstExp(st.getValueOf(value));
		return heap.getContent(address, st);
	}

	@Override
	public boolean isTrue(ISymbTbl st, IHeap heap) throws ZeroDivisionException, OperandException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException {
		return this.resolve(st, heap) != 0 ? true : false;
	}

	public String toString() {
		return "rH (" + this.value + ") ";
	}
}
