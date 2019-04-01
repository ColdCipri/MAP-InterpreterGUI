package Expression;

import Domain.IHeap;
import Domain.ISymbTbl;
import Exception.InvalidAddressException;
import Exception.InvalidSignException;
import Exception.InvalidSymbolException;
import Exception.NullAddressException;
import Exception.OperandException;
import Exception.ZeroDivisionException;

public interface IExp {
	public int resolve(ISymbTbl st, IHeap heap) throws ZeroDivisionException, OperandException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException ;
	public boolean isTrue(ISymbTbl st, IHeap heap) throws ZeroDivisionException, OperandException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException ;

}
