package Domain;

public class SymbTblItem {
	private String symbol;
	private Integer value;
	
	public SymbTblItem(String symbol, Integer value) {
		this.setSymbol(symbol);
		this.setValue(value);
	}
	
	public String getSymbol() {
		return this.symbol;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}
}
