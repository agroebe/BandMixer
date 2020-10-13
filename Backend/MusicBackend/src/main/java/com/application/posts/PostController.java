package com.application.posts;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.skill_level.AppliedSkillLevelRepository;
import com.application.skill_level.SkillLevel;
import com.application.skill_level.SkillLevelRepository;
import com.application.tagging.RequestTagApplication;
import com.application.tagging.Tag;
import com.application.tagging.TagRepository;


@Controller
@RequestMapping(path="/posts") 
public class PostController 
{
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private SkillLevelRepository skillLevelRepository;
	
	@Autowired
	private AppliedSkillLevelRepository applicationRepository;
	
	@GetMapping(path="/all")
    public @ResponseBody Iterable<Post> getAllPosts(){
        //Returns a JSON or XML document with the users in it
        return postRepository.findAll();
    }
	
	@GetMapping(path="/fetch")
    public @ResponseBody Post getById(@RequestBody @Valid RequestPost post)
    {
		long id = post.getId();
    	Optional<Post> foundpost = postRepository.findById(id);
    	return foundpost.get();
    }
	
	//TODO This should be a function of user, remove later
	@PostMapping(path="/add")
	public @ResponseBody String addPost(@RequestBody @Valid RequestNewPost post)
	{
		Post p = new Post(post.getTitle(), post.getContentType(), post.getIsSearch());
		p.setTextContent(post.getTextContent());
		for(RequestTagApplication tag : post.getApplications())
		{
			Tag tg = tagRepository.findByName(tag.getTag().getName()).get();
			SkillLevel level = skillLevelRepository.findByName(tag.getSkill().getName()).get();
			p.addTag(applicationRepository, tg, level, tag.getBounded(), tag.getLowerBounded());
		}
		postRepository.save(p);
		return "Saved";
	}
	
	@PostMapping(path="/update")
    public @ResponseBody String updatePost(@RequestBody @Valid RequestUpdatePost updatePost)
    {
		
		Optional<Post> find = postRepository.findById(updatePost.getId());
    	String newTitle = updatePost.getTitle();
		String newType = updatePost.getContentType();
		String newText = updatePost.getTextContent();
		boolean newSearch = updatePost.getIsSearch();
		
		
		Post found = find.get();
		
		boolean notitlechange = newTitle == null || !newTitle.equals(found.getTitle());
		boolean notypechange = newType == null || !newType.equals(found.getContentType());
		boolean notextchange = newText == null || !newText.equals(found.getTextContent());
		boolean nosearchChange = newSearch == found.getIsSearch();
		
		if(!notitlechange)
		{
			found.setTitle(newTitle);
		}
		if(!notypechange)
		{
			found.setContentType(newType);
		}
		if(!notextchange)
		{
			if(newText.equals(""))
			{
				found.setTextContent(null);
			}
			else
			{
				found.setTextContent(newText);
			}
		}
		if(!nosearchChange)
		{
			found.setIsSearch(newSearch);
		}
    	postRepository.save(found);
    	return "Updated";
    }
	
	@PostMapping(path="/remove")
	public @ResponseBody String removePost(@RequestBody @Valid RequestPost requestPost)
	{
		Optional<Post> post = postRepository.findById(requestPost.getId());
    	post.get().remove(applicationRepository, postRepository);
    	return "Post removed.";
	}
    
	@PostMapping(path="/removetag")
	public @ResponseBody String removeTag(@RequestBody @Valid RequestExistentPostExistentTag requestPost)
	{
		Optional<Post> post = postRepository.findById(requestPost.getId());
		Tag tg = tagRepository.findByName(requestPost.getTag().getName()).get();
    	post.get().removeTag(applicationRepository, tg);
    	return "Tag removed.";
	}
	
	@PostMapping(path="/addtag")
	public @ResponseBody String addTag(@RequestBody @Valid RequestExistentPostNewTag requestPost)
	{
		Optional<Post> post = postRepository.findById(requestPost.getId());
		RequestTagApplication tag = requestPost.getApplication();
		Tag tg = tagRepository.findByName(tag.getTag().getName()).get();
		SkillLevel level = skillLevelRepository.findByName(tag.getSkill().getName()).get();
		post.get().addTag(applicationRepository, tg, level, tag.getBounded(), tag.getLowerBounded());
    	return "Tag added.";
	}
	
	@PostMapping(path="/updatetag")
	public @ResponseBody String updateTag(@RequestBody @Valid RequestExistentPostUpdateTag requestPost)
	{
		Optional<Post> post = postRepository.findById(requestPost.getId());
		RequestTagApplication tag = requestPost.getApplication();
		Tag tg = tagRepository.findByName(tag.getTag().getName()).get();
		SkillLevel level = skillLevelRepository.findByName(tag.getSkill().getName()).get();
		post.get().updateTag(tg, level, tag.getBounded(), tag.getLowerBounded());
    	return "Tag updated.";
	}
}
