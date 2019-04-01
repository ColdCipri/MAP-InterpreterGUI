package Expression;

import Domain.IHeap;
import Domain.ISymbTbl;
import Exception.ZeroDivisionException;
import Exception.InvalidAddressException;
import Exception.InvalidSignException;
import Exception.InvalidSymbolException;
import Exception.NullAddressException;
import Exception.OperandException;

public class ArithExp implements IExp{
	public char operand;
	public IExp exp1;
	public IExp exp2;
	
	public ArithExp(char operand, IExp exp1, IExp exp2) {
		this.operand = operand;
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
		
	@Override
	public int resolve(ISymbTbl st, IHeap heap) throws ZeroDivisionException, OperandException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException {
		int s = 0;
		int operator1 = exp1.resolve(st, heap);
		int operator2 = exp2.resolve(st, heap);
			switch(operand) {
			case '+':
				s = operator1 + operator2;
				break;
			case '-':
				s = operator1 - operator2;
				break;
			case '*':
				s = operator1 * operator2;
				break;
			case '/':
				if (operator2 == 0)
					throw new ZeroDivisionException("Found division by 0", 1);
				s = operator1 / operator2;
				break;
			default:
				if ( operand != '+' & operand != '-' & operand != '*' & operand != '/')
					throw new OperandException("Operand error, found "+ operand  );
				break;
			}
		
		
		return s;
	}

	@Override
	public boolean isTrue(ISymbTbl st, IHeap heap) throws ZeroDivisionException, OperandException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException {
		return this.resolve(st, heap) != 0 ? true : false;
	}
	
	public String toString() {
		return exp1 + " " + operand + " " + exp2;
	}

}
