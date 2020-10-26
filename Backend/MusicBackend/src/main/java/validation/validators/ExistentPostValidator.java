package validation.validators;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.posts.Post;
import com.application.posts.PostRepository;
import com.application.util.BeanUtil;

import validation.annotations.ExistentPost;

public class ExistentPostValidator implements ConstraintValidator<ExistentPost, Long>
{
	@Autowired
	private PostRepository repo;
	
	
	public void initialize(ExistentPost constraint)
	{
		repo = BeanUtil.getBean(PostRepository.class);
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
//		System.out.println("Validating");
//		for(Post p : repo.findAll())
//		{
//			System.out.println(p.getId());
//		}
//		repo.findAll();
		Optional<Post> find = repo.findById(value);
		System.out.println(find.isPresent());
		if(!find.isPresent())
		{
			String msg = "Post with id '" + value + "' does not exist.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		return true;
	}

}
