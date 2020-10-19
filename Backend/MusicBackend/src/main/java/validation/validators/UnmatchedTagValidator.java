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

import validation.annotations.UnmatchedTag;

public class UnmatchedTagValidator implements ConstraintValidator<UnmatchedTag, Object>
{
	@Autowired
	private PostRepository repo;
	
	@Autowired
	private TagRepository tagrepo;
	
	private String idfield;
	private String tagfield;
	
	public void initialize(UnmatchedTag constraint)
	{
		repo = BeanUtil.getBean(PostRepository.class);
		tagrepo = BeanUtil.getBean(TagRepository.class);
		idfield = constraint.idfield();
		tagfield = constraint.tagfield();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		long idvalue = (long)new BeanWrapperImpl(value).getPropertyValue(idfield);
		Optional<Post> find = repo.findById(idvalue);
		RequestExistentTag tag = ((RequestTagApplication)new BeanWrapperImpl(value).getPropertyValue(tagfield)).getTag();
		Post found = find.get();
		Optional<Tag> tagfind = tagrepo.findByName(tag.getName());
		Tag tagfound = tagfind.get();
		TagSkillLevelKey key = new TagSkillLevelKey(idvalue, tagfound.getId());
		if(found.getAppliedTags().containsKey(key))
		{
			String msg = "Post with id '" + idvalue + "' already has tag '" + tag.getName() +".";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		return true;
	}

}