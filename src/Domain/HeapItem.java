package Domain;

public class HeapItem {
	private Integer address;
	private Integer value;
	
	public HeapItem(Integer add, Integer val) {
		this.setAddress(add);
		this.setValue(val);
	}
	
	public Integer getAddress() {
		return this.address;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	public void setAddress(Integer add) {
		this.address = add;
	}
	
	public void setValue(Integer val) {
		this.value = val;
	}
}
