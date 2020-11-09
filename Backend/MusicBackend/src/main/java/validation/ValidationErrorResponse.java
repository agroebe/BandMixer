package validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Class containing a list of constraint violations that occurred with the input.
 * Returned to requester if there were any violations.
 * @author Tim Schommer
 *
 */
public class ValidationErrorResponse 
{
	private List<Violation> violations;
	
	/**
	 * Default constructor.
	 */
	public ValidationErrorResponse()
	{
		violations = new ArrayList<Violation>();
	}
	
	/**
	 * Getter for the list of violations.
	 * @return
	 */
	public List<Violation> getViolations()
	{
		return violations;
	}
}
