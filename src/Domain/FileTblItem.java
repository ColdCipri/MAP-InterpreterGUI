package Domain;

public class FileTblItem {
	private Integer fileDescriptor;
	private String value;
	
	public FileTblItem(Integer fileDescriptor, String value) {
		this.setFileDescriptor(fileDescriptor);
		this.setValue(value);
	}
	
	public Integer getFileDescriptor() {
		return this.fileDescriptor;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public void setFileDescriptor(Integer fd) {
		this.fileDescriptor = fd;
	}
	
	public void setValue (String value) {
		this.value = value;
	}
}
