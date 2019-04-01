package Domain;

import java.util.HashMap;
import java.util.Set;

import Exception.InvalidAddressException;
import Exception.InvalidSignException;
import Exception.InvalidSymbolException;
import Exception.NullAddressException;
import Exception.OperandException;
import Exception.ZeroDivisionException;
import Expression.IExp;
import javafx.collections.ObservableList;

public interface IHeap {
	public int addItem(int content);
	public Set<Integer> getKeySey();
	public int getContent(IExp address, ISymbTbl st) throws ZeroDivisionException, OperandException, NullAddressException, InvalidAddressException, InvalidSymbolException, InvalidSignException;
	public void updateValue(int address, int value) throws NullAddressException, InvalidAddressException;
	public HashMap<Integer,Integer> getContent();
	public void setContent(HashMap<Integer,Integer> c);
	public ObservableList<HeapItem> toObservableList(ObservableList<HeapItem> list);
}
