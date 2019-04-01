package Statement;

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

public class CloseReadFileStmt implements IStmt {
	IExp exp;
	
	public CloseReadFileStmt(IExp exp) {
		this.exp = exp;
	}
	
	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException {
		int fid = exp.resolve(state.symbtbl.peek(), state.heap);
		
		state.filetbl.getFile(fid).close();
		state.filetbl.removeFile(fid);
		
		return null;
	}

	public String toString() {
		return "closeReadFileStmt( " + this.exp.toString() + ")";
	}
}
