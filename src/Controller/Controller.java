package Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import Domain.IPrgState;
import Domain.PrgState;
import Domain.PrintCallBack;
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
import Repository.IRepo;
import Repository.Repo;

public class Controller implements IController{
	IRepo repo;
	public PrintCallBack callback;
	private ExecutorService executor = Executors.newFixedThreadPool(2);
	
	public Controller(IRepo r ) {
		repo = r;
	}
	
	@Override
	public void nextStep() throws InvalidStateException, ZeroDivisionException, OperandException, 
	IOException, DuplicateFileException, InvalidFileException, InvalidSymbolException, NullAddressException, 
	InvalidAddressException, InvalidSignException {
	
	}
	
	public void oneStepForAllPrgStates(List<PrgState> prgList) throws InterruptedException {
		List<Callable<PrgState>> callList = prgList.stream()
				.map((PrgState p) -> (Callable<PrgState>) (() -> p.oneStep())).collect(Collectors.toList());
		List<PrgState> newPrgList = ((ExecutorService) executor).invokeAll(callList).stream().map(future -> {
			try {
				return future.get();
			} catch (Exception ex) {
				ex.printStackTrace();
				//String msg = ex.getMessage();
			}
			return null;
		}).filter(p -> p != null).collect(Collectors.toList());
		
		prgList.addAll(newPrgList);
		
		prgList.forEach(prg ->{
			try {
				repo.logPrgState(prg);
			} catch (IOException e) {
				callback.printCallBack(e.getMessage());
			}
		});
		
		repo.setPrgState(new ArrayList<>(prgList));
	}
	
	
	@Override
	public void allSteps(){
		List<PrgState> prgList = removeCompletedPrgStates(repo.getPrgState());
		prgList.forEach(prg -> {
			try {
				repo.logPrgState(prg);
			} catch (IOException e) {
				callback.printCallBack(e.getMessage());
			}
		});
		while (prgList.size() > 0) {
			try {
				oneStepForAllPrgStates(prgList);
			} catch (InterruptedException e) {
				callback.printCallBack("execution interupted");
			}
			prgList = removeCompletedPrgStates(repo.getPrgState());
		}
		((ExecutorService) executor).shutdownNow();
		/*} catch (IOException | InvalidStateException | InvalidSymbolException | NullAddressException | InvalidAddressException |DuplicateFileException | InvalidFileException | InvalidSignException ex) {
			callback.printCallBack(ex.getLocalizedMessage());
		} finally {
			for (int fd : s.getSymTable().getContent().values()) {
				if (s.getFileTbl().contains(fd)) {
					try {
						s.addStatement(new CloseReadFileStmt(new ConstExp(fd)));//auto close file
						try {
							s.executeNextStep();
						} catch (OperandException e) {
								//callback.printCallBack(e.getLocalizedMessage());
							e.printStackTrace();
						} catch (ZeroDivisionException e1) {
							e1.printStackTrace();
						}
						callback.printCallBack("Closed file with file descriptor: " + fd);
					} catch (InvalidStateException | DuplicateFileException | InvalidFileException | IOException | InvalidSymbolException | NullAddressException | InvalidAddressException | InvalidSignException e) {
						callback.printCallBack(e.getLocalizedMessage());
					}
				}
			}
			callback.printCallBack(s.getFileTbl().toString());
		}*/
		repo.setPrgState(prgList);
	}
	
	@Override
	public void oneStepRedirect() throws InterruptedException {
		List<PrgState> prgList = removeCompletedPrgStates(repo.getPrgState());
		oneStepForAllPrgStates(prgList);
	}
				
	
	@Override
	public IPrgState getNextState(IPrgState s) throws InvalidStateException, 
	ZeroDivisionException, OperandException, FileNotFoundException, 
	DuplicateFileException, InvalidFileException, IOException, InvalidSymbolException, 
	NullAddressException, InvalidAddressException, InvalidSignException, InvalidBarrierException, InvalidSemaphoreException {
		PrgState st = (PrgState) s;
		st.executeNextStep();
		return st;
	}

	@Override
	public void conservativeGarbageCollector(List<IPrgState> prgstates) {
 		HashSet<Integer> symbtblvalues = new HashSet<>();
 		HashMap<Integer, Integer> heap = new HashMap<>();
 		for (IPrgState s : prgstates) 
 			symbtblvalues.addAll(s.getSymTable().getContent().values());
 		for (IPrgState s : prgstates)
 			for (Integer val : s.getHeap().getContent().values())
 				if (!symbtblvalues.contains(val))
 					heap.remove(val);
 		if (prgstates.size() > 0) {
 			prgstates.get(0).getHeap().setContent(heap);
 			
 		}
 	} 
	
	@Override
	public List<PrgState> removeCompletedPrgStates(List<PrgState> prgstates){
		return prgstates.stream().filter(i -> i.isNotDone()).collect(Collectors.toList());
	}
	
	@Override
	public Repo getRepo() {
		return (Repo) this.repo;
	}
}
