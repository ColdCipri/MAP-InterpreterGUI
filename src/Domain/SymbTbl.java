package Domain;

import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

import Exception.InvalidSymbolException;
import javafx.collections.ObservableList;

public class SymbTbl implements ISymbTbl{
	HashMap<String,Integer> symbols;
	
	public SymbTbl() {
		this.symbols = new HashMap<>();
	}
	
	public SymbTbl(SymbTbl s) {
		this.symbols = new HashMap<>(s.symbols);
	}
	
	public SymbTbl(HashMap<String, Integer> values) {
		this.symbols = new HashMap<String, Integer>(values);
	}
	
	@Override
	public int getValueOf(String s) throws InvalidSymbolException {
		int val = 0;
		try {
			val = symbols.get(s);
		} catch (Exception e) {
			System.out.println(symbols);
		}
		if (symbols.get(s) == null)
			throw new InvalidSymbolException("Symbol does not exist!",1);
		return val;
	}

	@Override
	public void addSymbol(String s, int val) {
		this.symbols.put(s, val);
		
	}

	@Override
	public void setValue(String s, int val) throws InvalidSymbolException {
		this.getValueOf(s);
		symbols.put(s, val);
		
	}

	@Override
	public Set<String> getKeyset() {
		return this.symbols.keySet();
	}

	@Override
	public HashMap<String, Integer> getContent() {
		return this.symbols;
	}
		
	public String toString() {
		String toPrint= "\r\n SymbolTabel: \r\n";
		boolean empty = true;
		
		for (String k: this.symbols.keySet()) {
			toPrint +="\t" + k + " = " + symbols.get(k) + "\n";
			empty = false;
		}
		if (empty)
			toPrint += "\tEmpty\r\n";
		return toPrint;
	}
	
	@Override 
	public boolean containsKey(String varName) {
		return this.symbols.containsKey(varName);
	}
	
	public static Stack<ISymbTbl> cloneStack(Stack<ISymbTbl> symbtblstack){
		Stack<ISymbTbl> stack = new Stack<>();
		for (ISymbTbl st : symbtblstack){
			stack.push(new SymbTbl((SymbTbl) st));
		}
		return stack;
	}
	@Override
	public ObservableList<SymbTblItem> toObservableList(ObservableList<SymbTblItem> list){
		list.clear();
		for (String k : this.symbols.keySet()) {
			list.add(new SymbTblItem(k, this.symbols.get(k)));
		}
		return list;
	}

}
