package Exception;

public class InvalidBarrierException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5516220286507543629L;
	
	private final int code;

	public InvalidBarrierException(int code) {
		super();
		this.code = code;
	}

	public InvalidBarrierException(String message, Throwable cause, int code) {
		super(message, cause);
		this.code = code;
	}

	public InvalidBarrierException(String message, int code) {
		super(message);
		this.code = code;
	}

	public InvalidBarrierException(Throwable cause, int code) {
		super(cause);
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
