package com.application.tagging;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/tags") 
public class TagController 
{
	@Autowired
    private TagRepository tagRepository;

    
    @PostMapping(path="/add") //Map only POST requests
    public @ResponseBody String addNewTag(@RequestParam String name, @RequestParam(defaultValue="true") boolean allowSkill)
    {
    	if(!tagExists(name))
    	{
    		Tag n = new Tag(name, allowSkill);
    		tagRepository.save(n);
    		return "Saved";
    	}
        return "Already Present";
    }
    
    @PostMapping(path="/update")
    public @ResponseBody String updateTag(@RequestParam String oldName, @RequestParam(required=false) String newName, @RequestParam(required=false) Boolean allowSkill)
    {
    	if(newName == null && allowSkill == null)
    	{
    		return "Nothing to update.";
    	}
    	if(!tagExists(oldName))
    	{
    		return "No such tag present";
    	}
    	Optional<Tag> member = tagRepository.findByName(oldName);
    	Tag toUpdate = member.get();
    	if(newName != null)
    	{
    		toUpdate.setName(newName);
    	}
    	if(allowSkill != null)
    	{
    		toUpdate.setAllowsSkill(allowSkill);
    	}
    	tagRepository.save(toUpdate);
    	return "Updated";
    }
    
    @GetMapping(path="/fetch")
    public @ResponseBody Tag getByName(@RequestParam String name)
    {
    	Optional<Tag> tag = tagRepository.findByName(name);
    	if(!tag.isPresent())
    	{
    		throw new TagNotFoundException("name-" + name);
    	}
    	return tag.get();
    }

    @GetMapping(path="/all")
    @CrossOrigin
    public @ResponseBody Iterable<Tag> getAllTags(){
        //Returns a JSON or XML document with the users in it
        return tagRepository.findAll();
    }
    
    @GetMapping(path="/exists")
    public @ResponseBody boolean exists(String name)
    {
    	return tagExists(name);
    }
    
    @GetMapping(path="/hasSkill")
    public @ResponseBody boolean allowsSkill(String name)
    {
    	Optional<Tag> tag = tagRepository.findByName(name);
    	if(!tag.isPresent())
    	{
    		return false;
    	}
    	return tag.get().getAllowskill();
    }
    
    private boolean tagExists(String name)
    {
    	Optional<Tag> member = tagRepository.findByName(name);
    	return member.isPresent();
    }
    
    private class TagNotFoundException extends IllegalArgumentException
    {
    	public TagNotFoundException() {super();}
    	public TagNotFoundException(String message) {super(message);}
    }
}
