package Statement;

import java.io.BufferedReader;
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

public class ReadFileStmt implements IStmt {
	IExp exp;
	String symbol;
	
	public ReadFileStmt(IExp exp, String symbol) {
		this.exp = exp;
		this.symbol = symbol;
	}
	
	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, FileNotFoundException, DuplicateFileException, InvalidFileException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException {
		int fid = exp.resolve(state.symbtbl.peek(), state.heap);
		BufferedReader r = state.filetbl.getFile(fid);
		int val = 0;
		try {
			String l = r.readLine();
			val = Integer.parseInt(l);//if the line is not empty read
		} catch (IOException | NumberFormatException e) {	
			val = 0;				//else return 0
		}
		state.symbtbl.peek().addSymbol(symbol, val);//add value to symbol to symtbl :)
		//return state;
		return null;
	}

	public String toString() {
		return "readFileStmt(" + this.exp.toString() + ", \"" + this.symbol + "\" )";
	}
}
