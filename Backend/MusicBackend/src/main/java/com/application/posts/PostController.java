package com.application.posts;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.tagging.AppliedSkillLevelRepository;


@Controller
@RequestMapping(path="/posts") 
public class PostController 
{
	@Autowired
	private PostRepository postRepository;
	
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
	public @ResponseBody String addPost(@RequestParam String title, @RequestParam String type, @RequestBody RequestTag[] tags)
	{
		Post p = new Post(title, type);
		postRepository.save(p);
		return "Saved";
	}
	
	@PostMapping(path="/update")
    public @ResponseBody String updatePost(@RequestBody @Valid RequestPost id, @RequestParam(required=false) String newTitle, @RequestParam(required=false) String newText)
    {
		if(newTitle == null && newText == null)
		{
			return "Nothing to update";
		}
		Optional<Post> post = postRepository.findById(id.getId());
    	Post toUpdate = post.get();
    	if(newTitle != null)
    	{
    		toUpdate.setTitle(newTitle);
    	}
    	if(newText != null)
    	{
    		toUpdate.setTextContent(newText);
    	}
    	postRepository.save(toUpdate);
    	return "Updated";
    }
	
	@PostMapping(path="/remove")
	public @ResponseBody String removePost(@RequestBody @Valid RequestPost id)
	{
		Optional<Post> post = postRepository.findById(id.getId());
    	if(!post.isPresent())
    	{
    		return "no such post!";
    	}
    	post.get().remove(applicationRepository, postRepository);
    	return "Post removed.";
	}
    
    
}
