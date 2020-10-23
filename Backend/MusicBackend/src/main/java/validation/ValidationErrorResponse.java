package validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse 
{
	private List<Violation> violations;
	
	public ValidationErrorResponse()
	{
		violations = new ArrayList<Violation>();
	}
	
	public List<Violation> getViolations()
	{
		return violations;
	}
}
