package Expression;

import Domain.IHeap;
import Domain.ISymbTbl;
import Exception.InvalidAddressException;
import Exception.InvalidSignException;
import Exception.InvalidSymbolException;
import Exception.NullAddressException;
import Exception.OperandException;
import Exception.ZeroDivisionException;

public class BoolExp implements IExp {
	public String operand;
	public IExp exp1;
	public IExp exp2;
	
	public BoolExp(String operand, IExp exp1, IExp exp2) {
		this.operand = operand;
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	public int resolve(ISymbTbl st, IHeap heap) throws ZeroDivisionException, OperandException, InvalidSymbolException,
			NullAddressException, InvalidAddressException, InvalidSignException {
		int operator1 = exp1.resolve(st, heap);
		int operator2 = exp2.resolve(st, heap);
		
		switch (operand) {
		case "<":
			return (operator1 < operator2) ? 1 : 0;
		case "<=":
			return (operator1 <= operator2) ? 1 : 0;
		case "==":
			return (operator1 == operator2) ? 1 : 0;
		case ">":
			return (operator1 > operator2) ? 1 : 0;
		case ">=":
			return (operator1 >= operator2) ? 1 : 0;
		case "!=":
			return (operator1 != operator2) ? 1 : 0;
		default:
			throw new InvalidSignException("Valid sign expected ( <, <=, ==, !=, >, >= ). Sign given: " + operand, 1);
		}
	}

	@Override
	public boolean isTrue(ISymbTbl st, IHeap heap) throws ZeroDivisionException, OperandException,
			InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException {
		return this.resolve(st, heap) != 0 ? true : false;
	}
	
	public String toString() {
		return this.exp1 + " " + this.operand + " " + this.exp2 + " ";
	}
	

}
