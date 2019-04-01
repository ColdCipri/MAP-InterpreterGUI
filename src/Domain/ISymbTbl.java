package Domain;

import java.util.HashMap;
import java.util.Set;
import Exception.InvalidSymbolException;
import javafx.collections.ObservableList;


public interface ISymbTbl {
	int getValueOf(String s) throws InvalidSymbolException ;
	void addSymbol (String s, int val);
	void setValue(String s , int val) throws InvalidSymbolException;
	Set<String> getKeyset();
	String toString();
	HashMap<String, Integer> getContent();
	boolean containsKey(String varName);
	ObservableList<SymbTblItem> toObservableList(ObservableList<SymbTblItem> list);
}
