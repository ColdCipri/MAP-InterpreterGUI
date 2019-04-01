package Domain;

public class BarrierTablePair {
	private Integer key;
	private Integer parties;
	private String guids;
	
	public BarrierTablePair(Integer key, Integer parties, String guids) {
		this.setKey(key);
		this.setParties(parties);
		this.setGuids(guids);
	}
	
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public Integer getParties() {
		return parties;
	}
	public void setParties(Integer parties) {
		this.parties = parties;
	}
	public String getGuids() {
		return guids;
	}
	public void setGuids(String guids) {
		this.guids = guids;
	}
		
}
