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

import validation.annotations.UpdatedTag;

/**
 * Class to validate a data wrapper for updating a Tag as it is applied to a Post. 
 * Ensures that the application of Tag to Post is actually being updated. 
 * Assumes that the TagApplication object has already been validated by another validator (MatchedTagValidator).
 * @author Tim Schommer
 *
 */
public class UpdatedTagValidator implements ConstraintValidator<UpdatedTag, Object>
{
	@Autowired
	private PostRepository repo;
	
	@Autowired
	private TagRepository tagrepo;
	
	private String idfield;
	private String tagfield;
	
	public void initialize(UpdatedTag constraint)
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
		RequestTagApplication newApp = (RequestTagApplication)new BeanWrapperImpl(value).getPropertyValue(tagfield);
		RequestExistentTag tag = newApp.getTag();
		
		Post found = find.get();
		Optional<Tag> tagfind = tagrepo.findByName(tag.getName());
		Tag tagfound = tagfind.get();
		TagSkillLevelKey key = new TagSkillLevelKey(idvalue, tagfound.getId());
		AppliedSkillLevel application = found.getAppliedTags().get(key);
		boolean noskillchanged = newApp.getSkill().getName().equals(application.getSkillLevel().getName());
		boolean noboundingchanged = newApp.getBounded() == application.getIsBounded();
		boolean noboundchanged = newApp.getLowerBounded() == application.getIsLowerBound();
		
		if(noskillchanged && noboundingchanged && noboundchanged)
		{
			String msg = "Nothing given to update with.";
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			return false;
		}
		return true;
	}

}
