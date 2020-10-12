package validation.validators;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.people.User;
import com.application.people.UserRepository;
import com.application.util.BeanUtil;

import validation.annotations.ExistentOwner;

public class ExistentOwnerValidator implements ConstraintValidator<ExistentOwner, Long>
{
	@Autowired
	private UserRepository repo;
	
	
	public void initialize(ExistentOwner constraint)
	{
		repo = BeanUtil.getBean(UserRepository.class);
	}
	
	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		if(value == null)
		{
			String msg = "Cannot pass a null parameter.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		Optional<User> find = repo.findById(value);
		if(!find.isPresent())
		{
			String msg = "User with id '" + value + "' does not exist.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		return true;
	}

}
