package Domain;

public class SemaphoreTableTuple {
	private Integer key;
	private Integer permits;
	private String guids;
	private Integer permits2;

	public SemaphoreTableTuple(Integer key, Integer permits, String guids, Integer permits2) {
		this.setKey(key);
		this.setPermits(permits);
		this.setGuids(guids);
		this.setPermits2(permits2);
	}
	
	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public Integer getPermits() {
		return permits;
	}

	public void setPermits(Integer permits) {
		this.permits = permits;
	}

	public String getGuids() {
		return guids;
	}

	public void setGuids(String guids) {
		this.guids = guids;
	}
	
	public Integer getPermits2() {
		return permits2;
	}

	public void setPermits2(Integer permits2) {
		this.permits2 = permits2;
	}

}