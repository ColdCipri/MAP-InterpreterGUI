package Exception;

public class OperandException extends Exception {
	 // Parameterless Constructor
    public OperandException() {}

    // Constructor that accepts a message
    public OperandException(String message)
    {
       super(message);
    }
}
