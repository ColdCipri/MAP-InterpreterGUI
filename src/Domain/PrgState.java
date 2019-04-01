package Domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.UUID;

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

public class PrgState implements IPrgState{
	public IStack stack;
	public IOutput output;
	public Stack<ISymbTbl> symbtbl;
	public IFileTbl filetbl;
	public IHeap heap;
	public IBarrierTable barrierTable;
	public ISemaphTbl semaphoreTable;
	public String GUID;
	
	public PrgState() {
		this.stack = new MyStack();
		this.output = new Output();
		this.symbtbl = new Stack<ISymbTbl>();
		this.symbtbl.push(new SymbTbl());
		this.filetbl = new FileTbl();
		this.heap = new Heap();
		this.barrierTable = new BarrierTable();
		this.GUID = UUID.randomUUID().toString();
		this.semaphoreTable = new SemaphTbl();
	}
	
	public PrgState(IStack stack, IOutput output, Stack<ISymbTbl> symbtbl, IFileTbl filetbl, IHeap heap,
			IBarrierTable barrierTable, ISemaphTbl semaphoreTable) {
		this.stack = stack;
		this.output = output;
		this.symbtbl = symbtbl;
		this.filetbl = filetbl;
		this.heap = heap;
		this.barrierTable = barrierTable;
		this.GUID = UUID.randomUUID().toString();
		this.semaphoreTable = semaphoreTable;
	}
	/*
	public PrgState(PrgState pr) {
		this.stack = new MyStack((MyStack) pr.stack);
		this.output = new Output((Output) pr.output);
		this.symbtbl = new SymbTbl((SymbTbl) pr.symbtbl);
		this.filetbl = new FileTbl((FileTbl) pr.filetbl);
		this.heap = new Heap((Heap) pr.heap);
	}*/
	
	@Override
	public void executeNextStep() throws InvalidStateException, ZeroDivisionException, OperandException, 
		FileNotFoundException, DuplicateFileException, InvalidFileException, IOException, 
		InvalidSymbolException, NullAddressException, InvalidAddressException, InvalidSignException, InvalidBarrierException, InvalidSemaphoreException {
		if (!stack.isEmpty()) {
			IStmt s = stack.pop();
			s.execute(this);
		}
		else {
			throw new InvalidStateException("End of stack reached",0);
		}
	}
		
	@Override
	public ArrayList<Integer> getOutput() {
		return (ArrayList<Integer>) this.output.getIterator();
	}
	
	@Override
	public boolean isDone() {
		return this.stack.isEmpty();
	}

	@Override
	public void addStatement(IStmt s) {
		this.stack.push(s);
	}
	
	public String toString() {
		String toPrint = "_________________________________________________________________\n\n";
		toPrint += "PrgState GUID: " + GUID + "	\n\n";
		toPrint += stack.toString() + "\r\n";
		toPrint += symbtbl.toString() + "\r\n";
		toPrint += filetbl.toString() + "\r\n";
		toPrint += heap.toString() + "\r\n";
		toPrint += barrierTable.toString() + "\r\n";
		toPrint += semaphoreTable.toString() + "\r\n";
		toPrint += output.toString() + "\r\n";
		return toPrint;
	}

	@Override
	public ISymbTbl getSymTable() {
		return symbtbl.peek();
	}
	
	@Override
	public IFileTbl getFileTbl() {
		return this.filetbl;
	}

	@Override
	public IHeap getHeap() {
		return this.heap;
	}
	
	@Override
	public IStack getStack() {
		return this.stack;
	}
	
	public IOutput getOutputObj() {
		return output;
	}
	
	@Override
	public boolean isNotDone() {
		return !isDone();
	}
	
	@Override
	public IBarrierTable getBarrierTable() {

		return barrierTable;
	}
	
	@Override
	public ISemaphTbl getSemaphoreTable() {

		return semaphoreTable;
	}
	
	@Override
	public PrgState oneStep() throws FileNotFoundException, ZeroDivisionException, OperandException, 
		DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, NullAddressException, 
		InvalidAddressException, InvalidSignException, InvalidStateException, InvalidBarrierException, InvalidSemaphoreException {
		if (this.isNotDone()) 
			return (PrgState) stack.pop().execute(this);
		else
			throw new InvalidStateException("",0);
	}
}
