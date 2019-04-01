package Exception;

public class InvalidStateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5516220286507543629L;
	
	private final int code;

	public InvalidStateException(int code) {
		super();
		this.code = code;
	}

	public InvalidStateException(String message, Throwable cause, int code) {
		super(message, cause);
		this.code = code;
	}

	public InvalidStateException(String message, int code) {
		super(message);
		this.code = code;
	}

	public InvalidStateException(Throwable cause, int code) {
		super(cause);
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
