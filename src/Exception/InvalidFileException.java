package Exception;

public class InvalidFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5516220286507543629L;
	
	private final int code;

	public InvalidFileException(int code) {
		super();
		this.code = code;
	}

	public InvalidFileException(String message, Throwable cause, int code) {
		super(message, cause);
		this.code = code;
	}

	public InvalidFileException(String message, int code) {
		super(message);
		this.code = code;
	}

	public InvalidFileException(Throwable cause, int code) {
		super(cause);
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}