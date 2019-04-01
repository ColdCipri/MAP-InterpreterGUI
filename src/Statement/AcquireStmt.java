package Statement;

import java.io.FileNotFoundException;
import java.io.IOException;

import Domain.CustomSemaphore;
import Domain.IPrgState;
import Domain.PrgState;
import Exception.DuplicateFileException;
import Exception.InvalidAddressException;
import Exception.InvalidBarrierException;
import Exception.InvalidFileException;
import Exception.InvalidSemaphoreException;
import Exception.InvalidSignException;
import Exception.InvalidSymbolException;
import Exception.NullAddressException;
import Exception.OperandException;
import Exception.ZeroDivisionException;

public class AcquireStmt implements IStmt{
	private String var;
	
	public AcquireStmt(String var) {
		this.setVar(var);
	}

	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, FileNotFoundException,
			DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException,
			InvalidAddressException, InvalidSignException, InvalidBarrierException, InvalidSemaphoreException {
		int foundIndex = state.symbtbl.peek().getValueOf(var);

		if (state.semaphoreTable.contains(foundIndex)) {
			CustomSemaphore semaphore = state.semaphoreTable.getSemaphore(foundIndex);
			int semaphoreLength = semaphore.guids.size();
			if (semaphoreLength < (semaphore.permits - semaphore.permits2)) {
				if (!semaphore.guids.contains(state.GUID))
					semaphore.guids.add(state.GUID);	
			}
			else 
				state.addStatement(new AcquireStmt(var));
		} else 
			
			throw new InvalidSemaphoreException("Index " + var + "not found", 0);
		
		return null;
	}


	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public String toString() {
		return "acquireStmt( " + var + ")";
	}
	
}
