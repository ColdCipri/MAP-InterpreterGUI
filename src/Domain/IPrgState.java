package Domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
import Statement.IStmt;

public interface IPrgState {
	public void executeNextStep() throws InvalidStateException, ZeroDivisionException, OperandException, FileNotFoundException, DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException, InvalidBarrierException, InvalidSemaphoreException;
	public String toString();
	public ArrayList<Integer> getOutput();
	public ISymbTbl getSymTable();
	public IFileTbl getFileTbl();
	public IHeap getHeap();
	public IStack getStack();
	public IOutput getOutputObj();
	public boolean isDone();
	void addStatement(IStmt s);
	boolean isNotDone();
	PrgState oneStep() throws FileNotFoundException, ZeroDivisionException, OperandException, DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException, InvalidStateException, InvalidBarrierException, InvalidSemaphoreException;
	IBarrierTable getBarrierTable();
	ISemaphTbl getSemaphoreTable();
	
}
