package validation.validators;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.posts.Post;
import com.application.posts.PostRepository;
import com.application.skill_level.AppliedSkillLevel;
import com.application.tagging.RequestExistentTag;
import com.application.tagging.RequestTagApplication;
import com.application.tagging.Tag;
import com.application.tagging.TagRepository;
import com.application.tagging.TagSkillLevelKey;
import com.application.util.BeanUtil;

import validation.annotations.UpdatedPost;

public class UpdatedPostValidator implements ConstraintValidator<UpdatedPost, Object>
{
	@Autowired
	private PostRepository repo;
	
	private String idfield;
	private String titlefield;
	private String typefield;
	private String textContentField;
	private String searchField;
	
	public void initialize(UpdatedPost constraint)
	{
		repo = BeanUtil.getBean(PostRepository.class);
		idfield = constraint.idfield();
		titlefield = constraint.titlefield();
		typefield = constraint.typefield();
		textContentField = constraint.textContentField();
		searchField = constraint.searchField();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		long idvalue = (long)new BeanWrapperImpl(value).getPropertyValue(idfield);
		String newTitle = (String)new BeanWrapperImpl(value).getPropertyValue(titlefield);
		String newType = (String)new BeanWrapperImpl(value).getPropertyValue(typefield);
		String newText = (String)new BeanWrapperImpl(value).getPropertyValue(textContentField);
		boolean newSearch = (boolean)new BeanWrapperImpl(value).getPropertyValue(searchField);
		Optional<Post> find = repo.findById(idvalue);
		
		Post found = find.get();
		
		boolean notitlechange = newTitle == null || newTitle.equals(found.getTitle());
		boolean notypechange = newType == null || newType.equals(found.getContentType());
		boolean notextchange = newText == null || (newText.equals(found.getTextContent()) && !newText.equals("")) 
				|| (found.getTextContent() == null && newText.equals(""));
		boolean nosearchChange = newSearch == found.getIsSearch();
		
		if(notitlechange && notypechange && notextchange && nosearchChange)
		{
			String msg = "Nothing given to update with.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		return true;
	}

}
