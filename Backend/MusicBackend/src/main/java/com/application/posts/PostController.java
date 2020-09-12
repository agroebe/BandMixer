package com.application.posts;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/posts") 
public class PostController 
{
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping(path="/all")
    public @ResponseBody Iterable<Post> getAllPosts(){
        //Returns a JSON or XML document with the users in it
        return postRepository.findAll();
    }
	
	@GetMapping(path="/fetch")
    public @ResponseBody Post getById(@RequestParam Long id)
    {
    	Optional<Post> post = postRepository.findById(id);
    	if(!post.isPresent())
    	{
    		throw new PostNotFoundException("Id-" + id);
    	}
    	return post.get();
    }
	
	//TODO This should be a function of user, remove later
	@PostMapping(path="/add")
	public @ResponseBody String addPost(@RequestParam String title, @RequestParam String type)
	{
		Post p = new Post(title, type);
		postRepository.save(p);
		return "Saved";
	}
	
	@PostMapping(path="/update")
    public @ResponseBody String updatePost(@RequestParam Long id, @RequestParam(required=false) String newTitle, @RequestParam(required=false) String newText)
    {
		if(newTitle == null && newText == null)
		{
			return "Nothing to update";
		}
		Optional<Post> post = postRepository.findById(id);
    	if(!post.isPresent())
    	{
    		return "no such post!";
    	}
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
    
    
	
	private class PostNotFoundException extends IllegalArgumentException
    {
    	public PostNotFoundException() {super();}
    	public PostNotFoundException(String message) {super(message);}
    }
}
