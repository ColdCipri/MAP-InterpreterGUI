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
import Expression.ConstExp;
import Expression.IExp;

public class SleepStmt implements IStmt{
	private IExp exp;
	public SleepStmt(IExp exp) {
		this.setExp(exp);
	}
	
	 public IExp getExp() {
        return exp;
    }

    public void setExp(IExp exp) {
        this.exp = exp;
    }

	
	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, FileNotFoundException,
			DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException,
			InvalidAddressException, InvalidSignException {
		if (getExp().isTrue(state.symbtbl.peek(), state.heap)) {
			int number = exp.resolve(state.symbtbl.peek(), state.heap);

			if (number > 0)
				state.addStatement(new SleepStmt(new ConstExp(number - 1)));
			}
		return null;
	}
	
	public String toString() {
        return "sleep ( "+ exp.toString() +" )";
    }


}
