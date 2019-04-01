package Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import Domain.IPrgState;
import Domain.PrgState;
import Exception.DuplicateFileException;
import Exception.InvalidAddressException;
import Exception.InvalidBarrierException;
import Exception.InvalidFileException;
import Exception.InvalidSemaphoreException;
import Exception.InvalidSignException;
import Exception.InvalidStateException;
import Exception.InvalidSymbolException;
import Exception.NullAddressException;
import Exception.OperandException;
import Exception.ZeroDivisionException;
import Repository.Repo;

public interface IController {
	
	public void allSteps();
	public void nextStep() throws InvalidStateException, ZeroDivisionException, OperandException, IOException, DuplicateFileException, InvalidFileException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException;
	public IPrgState getNextState(IPrgState s) throws InvalidStateException, ZeroDivisionException, OperandException, FileNotFoundException, DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException, InvalidBarrierException, InvalidSemaphoreException;
	public void conservativeGarbageCollector(List<IPrgState> prgStates);
	List<PrgState> removeCompletedPrgStates(List<PrgState> prgstates);
	public Repo getRepo();
	void oneStepRedirect() throws InterruptedException;
	
}
