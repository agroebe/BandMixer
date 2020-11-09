package com.application.tagging;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.View;
import com.application.skill_level.AppliedSkillLevelRepository;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Controller for performing operations on tags
 * @author Tim Schommer
 *
 */
@Controller
@RequestMapping(path="/tags") 
public class TagController 
{
	@Autowired
    private TagRepository tagRepository;
	
	@Autowired 
	private AppliedSkillLevelRepository applicationsRepository;

    /**
     * Adds a new Tag to the database.
     * @param newTag
     * 		An object wrapping the information needed to create a new Tag
     * @return
     * 		Returns a confirmation message.
     */
    @PostMapping(path="/add") //Map only POST requests
    public @ResponseBody String addNewTag(@RequestBody @Valid RequestNewTag newTag)
    {
    	Tag n = new Tag(newTag.getName(), newTag.getAcceptsSkill());
		tagRepository.save(n);
		return "Saved";
    }
    
    /**
     * Updates an existing tag.
     * @param updateTag
     * 		An object wrapping the information needed to determine which Tag to update and how to update it.
     * @return
     * 		Returns a confirmation message.
     */
    @PostMapping(path="/update")
    public @ResponseBody String updateTag(@RequestBody @Valid RequestUpdateTag updateTag)
    {
    	
    	Optional<Tag> member = tagRepository.findByName(updateTag.getName());
    	Tag toUpdate = member.get();
    	String newName = updateTag.getNewName();
    	String name = updateTag.getName();
    	boolean allowSkill = updateTag.getAllowsSkill();
    	boolean noNameChange = (newName == null || newName.equals("") || newName.equals(name));
		boolean noAllowChange = allowSkill == toUpdate.getAllowskill();
    	if(!noNameChange)
    	{
    		toUpdate.setName(newName);
    	}
    	if(!noAllowChange)
    	{
    		toUpdate.setAllowsSkill(allowSkill);
    	}
    	tagRepository.save(toUpdate);
    	return "Updated";
    }
    
    /**
     * Removes the indicated Tag from the database.
     * @param tag
     * 		An object wrapping the information on which tag to remove.
     * @return
     * 		Returns a confirmation message.
     */
    @PostMapping(path="/remove")
    public @ResponseBody String removeTag(@RequestBody @Valid RequestExistentTag tag)
    {
    	
    	tagRepository.findByName(tag.getName()).get().remove(applicationsRepository, tagRepository);
    	return "Tag: " + tag.getName() + " removed.";
    }
    
    /**
     * Returns a Tag with the given name.
     * @param name
     * 		The name of the Tag to get.
     * @return
     * 		Returns the Tag with the given name.
     */
    @JsonView(View.TagView.class)
    @GetMapping(path="/fetch")
    public @ResponseBody Tag getByName(@RequestParam @Valid String name)
    {
    	Optional<Tag> tag = tagRepository.findByName(name);
    	return tag.get();
    }

    /**
     * Returns a list of all Tags in the database.
     * @return
     */
    @JsonView(View.TagView.class)
    @GetMapping(path="/all")
    @CrossOrigin
    public @ResponseBody Iterable<Tag> getAllTags(){
        //Returns a JSON or XML document with the users in it
        return tagRepository.findAll();
    }
    

    /**
     * Returns whether or not the indicated Tag will work with a SkillLevel
     * @param tagChoice
     * 		An object wrapping the data indicating which Tag to check
     * @return
     * 		A boolean indicating whether or not the indicated Tag will work with a SkillLevel.
     */
    @GetMapping(path="/hasSkill")
    public @ResponseBody boolean allowsSkill(@RequestBody @Valid RequestExistentTag tagChoice)
    {
    	Optional<Tag> tag = tagRepository.findByName(tagChoice.getName());
    	return tag.get().getAllowskill();
    }
    
    
}
