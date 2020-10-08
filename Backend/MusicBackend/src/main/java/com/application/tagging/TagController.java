package com.application.tagging;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.skill_level.AppliedSkillLevelRepository;


@Controller
@RequestMapping(path="/tags") 
public class TagController 
{
	@Autowired
    private TagRepository tagRepository;
	
	@Autowired 
	private AppliedSkillLevelRepository applicationsRepository;

    
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
    
    @PostMapping(path="/remove")
    public @ResponseBody String removeTag(@RequestParam String tagName)
    {
    	if(tagName == null)
    	{
    		return "No tag name received.";
    	}
    	if(!tagExists(tagName))
    	{
    		return "No such tag present.";
    	}
    	tagRepository.findByName(tagName).get().remove(applicationsRepository, tagRepository);
    	return "Tag: " + tagName + " removed.";
    }
    
    @GetMapping(path="/allexists")
    public @ResponseBody Boolean[] tagsExist(@RequestBody String[] tags)
    {
    	HashMap<String, Boolean> checked = new HashMap<>();
    	Boolean[] ret = new Boolean[tags.length];
    	int i = 0;
    	for(String tagName : tags)
    	{
    		if(checked.containsKey(tagName))
    		{
    			ret[i] = checked.get(tagName);
    		}
    		else
    		{
    			boolean ex = tagExists(tagName);
    			ret[i] = ex;
    			checked.put(tagName, ex);
    		}
    		i++;
    	}
    	return ret;
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
