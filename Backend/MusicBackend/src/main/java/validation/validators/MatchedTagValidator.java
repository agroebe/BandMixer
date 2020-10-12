package validation.validators;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.application.posts.Post;
import com.application.posts.PostRepository;
import com.application.tagging.RequestExistentTag;
import com.application.tagging.RequestTagApplication;
import com.application.tagging.Tag;
import com.application.tagging.TagRepository;
import com.application.tagging.TagSkillLevelKey;
import com.application.util.BeanUtil;

import validation.annotations.MatchedTag;

public class MatchedTagValidator implements ConstraintValidator<MatchedTag, Object>
{
	@Autowired
	private PostRepository repo;
	
	@Autowired
	private TagRepository tagrepo;
	
	private String idfield;
	private String tagfield;
	private int version;
	
	public void initialize(MatchedTag constraint)
	{
		repo = BeanUtil.getBean(PostRepository.class);
		tagrepo = BeanUtil.getBean(TagRepository.class);
		idfield = constraint.idfield();
		tagfield = constraint.tagfield();
		version = constraint.version();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		long idvalue = (long)new BeanWrapperImpl(value).getPropertyValue(idfield);
		Optional<Post> find = repo.findById(idvalue);
		
		RequestExistentTag tag;
		switch(version)
		{
			case 1:
			{
				tag = (RequestExistentTag)new BeanWrapperImpl(value).getPropertyValue(tagfield);
				break;
			}
			case 2:
			{
				RequestTagApplication temp = (RequestTagApplication)new BeanWrapperImpl(value).getPropertyValue(tagfield);
				tag = temp.getTag();
				break;
			}
			default:
			{
				String msg = "Invalid version on input class.";
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
				return false;
			}
		}
		Post found = find.get();
		Optional<Tag> tagfind = tagrepo.findByName(tag.getName());
		Tag tagfound = tagfind.get();
		TagSkillLevelKey key = new TagSkillLevelKey(idvalue, tagfound.getId());
		if(!found.getAppliedTags().containsKey(key))
		{
			String msg = "Post with id '" + idvalue + "' does not have tag '" + tag.getName() +".";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		return true;
	}

}
