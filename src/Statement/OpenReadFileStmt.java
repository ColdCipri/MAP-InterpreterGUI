package Statement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import Domain.IPrgState;
import Domain.PrgState;
import Exception.DuplicateFileException;
import Exception.OperandException;
import Exception.ZeroDivisionException;

public class OpenReadFileStmt implements IStmt{
	String fileName;
	String symbol;
	int fd; //file descriptor
	
	public OpenReadFileStmt(String fileName, String symbol) {
		this.fileName = fileName;
		this.symbol = symbol;
	}
	
	@Override
	public IPrgState execute(PrgState state) throws ZeroDivisionException, OperandException, FileNotFoundException, DuplicateFileException {
		fd = state.filetbl.count();
		
		state.filetbl.addFile(fd, new BufferedReader(new FileReader(new File(fileName))));
		state.symbtbl.peek().addSymbol(symbol, fd);
		
		//return state;
		return null;
	}

	public String toString() {
		return "openReadFileStmt(" + this.symbol + ", \"" + this.fileName + "\"";
	}
}
