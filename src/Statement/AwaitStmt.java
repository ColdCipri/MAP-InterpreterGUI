package Statement;

import java.io.FileNotFoundException;
import java.io.IOException;

import Domain.CustomCyclicBarrier;
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

public class AwaitStmt implements IStmt{
	public String varName;
	
	public AwaitStmt(String varName) {
		this.varName = varName;
	}

	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, FileNotFoundException,
			DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException,
			InvalidAddressException, InvalidSignException, InvalidBarrierException {
		// this will throw an exception if not found and will print a message.
		int foundIndex = state.symbtbl.peek().getValueOf(varName);
		
		if (state.barrierTable.contains(foundIndex)) {
			CustomCyclicBarrier barrier = state.barrierTable.getBarrier(foundIndex);
			int barrierLength = barrier.barriers.size();
			if (barrierLength < barrier.parties) {
				if (!barrier.containsGUID(state.GUID))
					barrier.addGUID(state.GUID);
				state.addStatement(new AwaitStmt(varName));
			} 
		} else {
				throw new InvalidBarrierException("Index:" + foundIndex + "not found", 0);
		}

		return null;
	}
	
	public String toString() {
		return "await( " + varName + ")";
	}
}
