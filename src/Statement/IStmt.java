package Statement;

import java.io.FileNotFoundException;
import java.io.IOException;

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

public interface IStmt {
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, FileNotFoundException, DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException, InvalidBarrierException, InvalidSemaphoreException;
	
}
