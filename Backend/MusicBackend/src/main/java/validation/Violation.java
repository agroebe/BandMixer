package validation;

/**
 * A class containing information on a constraint violation for an input.
 * Sent back within a ValidationErrorResponse to the requester.
 * @author Tim Schommer
 *
 */
public class Violation 
{
	private final String fieldName;
	private final String message;
	
	/**
	 * Constructs a Violation with the given name for the field and message.
	 * @param name
	 * @param message
	 */
	public Violation(String name, String message)
	{
		this.fieldName = name;
		this.message = message;
	}
	
	/**
	 * Getter for the field name.
	 * @return
	 */
	public String getField()
	{
		return fieldName;
	}
	
	/**
	 * Getter for the message.
	 * @return
	 */
	public String getMessage()
	{
		return message;
	}
	
	@Override
	public String toString()
	{
		return "Error@" + fieldName + ": " + message;
	}
}
