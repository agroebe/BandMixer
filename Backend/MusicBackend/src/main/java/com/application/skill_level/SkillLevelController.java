package com.application.skill_level;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.application.View;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Controller for operations related to SkillLevels.
 * @author Tim Schommer
 *
 */
@Controller
@CrossOrigin
@RequestMapping(path="/skill-levels") 
public class SkillLevelController 
{
	@Autowired
	private SkillLevelRepository repo;
	
	@Autowired
	private AppliedSkillLevelRepository applicationRepo;

	/**
	 * Adds a new SkillLevel to the database. If another skill level already has the 
	 * same value as the new one, the values in the database 
	 * are shifted up until a gap in the values is found.
	 * @param newLevel
	 * 		An object containing the data to be used for creating a new skill level.
	 * @return
	 * 		Returns a confirmation message indicating the successful addition of the skill level.
	 */
	@PostMapping(path="/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public @ResponseBody String add(@RequestBody @Valid RequestNewSkillLevel newLevel)
	{
		String message = "Skill Level " + newLevel.getName() + " added.";
		Optional<SkillLevel> old = repo.findByValue(newLevel.getValue());
		if(old.isPresent())
		{
			message = "Skill Level " + newLevel.getName() + " inserted.";
			List<SkillLevel> above = repo.findWithGreaterOrEqual(newLevel.getValue());
			Stack<SkillLevel> toAdjust = new Stack<>();
			int base = newLevel.getValue() - 1;
			for(SkillLevel lv : above)
			{
				if(lv.getValue() > base + 1)
				{
					break;
				}
				toAdjust.push(lv);
				base++;
			}
			while(!toAdjust.isEmpty())
			{
				SkillLevel adjustee = toAdjust.pop();
				adjustee.setValue(adjustee.getValue() + 1);
				repo.save(adjustee);
			}
			SkillLevel toAdd = new SkillLevel(newLevel.getName(), newLevel.getValue());
			repo.save(toAdd);
		}
		else
		{
			SkillLevel toAdd = new SkillLevel(newLevel.getName(), newLevel.getValue());
			repo.save(toAdd);
		}

		return message;
	}

	/**
	 * Removes the SkillLevel indicated by the given RequestSkillLevel from the 
	 * Database and performs any necessary refactoring of Tag-Skill applications.
	 * For any given Tag-Skill  application that the skill level to be removed is part of, 
	 * if a SkillLevel with a value higher than the one being removed exists and
	 * either a) no skill level with a value lower than that of the one being removed
	 * exists or b) the application is a bounded application and it is lower bounded,
	 * then that application is assigned the skill level with the closest value above that of the one being removed.
	 * Otherwise, if a skill level with a value below that of the skill level being removed exists, 
	 * then the application is assigned the skill level with the closest value below that of the one being removed.
	 * If no skill levels exist with values either above or below that of the one being removed, then the application is removed altogether.
	 * 
	 * @param level
	 * 		An object containing the information on which skill level to remove.
	 * @return
	 * 		Returns a message confirming the successful removal of the skill level
	 * 		and indicating what type of refactoring was performed.
	 */
	@PostMapping(path="/remove")
	@CrossOrigin
	public @ResponseBody String remove(@RequestBody @Valid RequestSkillLevel level)
	{
		Optional<SkillLevel> toRemoveTemp = repo.findByName(level.getName());
		if(!toRemoveTemp.isPresent())
		{
			return "Error. The requested skill level no longer exists.";
		}
		SkillLevel toRemove = toRemoveTemp.get();
		Optional<SkillLevel> below = repo.findFirstLess(toRemove.getValue());
		Optional<SkillLevel> above = repo.findFirstGreater(toRemove.getValue());
		Set<AppliedSkillLevel> apps = toRemove.getApplications();
		if(apps.isEmpty())
		{
			toRemove.remove(applicationRepo, repo);
			return "Skill Level " + level.getName() + " removed";
		}
		else if(!above.isPresent() && !below.isPresent())
		{
			toRemove.remove(applicationRepo, repo);
			return "Skill Level " + level.getName() + " removed. No way to refactor containing tags";
		}
		else if(!above.isPresent())
		{
			toRemove.remove(applicationRepo, repo, below.get());
			return "Skill Level " + level.getName() +
					" removed. All tags paired with it have been refactored downwards.";
		}
		else if(!below.isPresent())
		{
			toRemove.remove(applicationRepo, repo, above.get());
			return "Skill Level " + level.getName() +
					" removed. All tags paired with it have been refactored upwards.";
		}
		else
		{
			toRemove.remove(applicationRepo, repo, above.get(), below.get());
			return "Skill Level " + level.getName() +
					" removed. All lower bounded tags paired with it have been refactored upwards."
					+" All other tags paired with it have been refactored downwards.";
		}
	}

	/**
	 * Shifts the values of all the skill levels down so that they start from 0
	 * and count up by increments of 1. Maintains original ordering.
	 * @return
	 * 		Returns a confirmation message.
	 */
	@PostMapping(path="/reorder")
	@CrossOrigin
	public @ResponseBody String reorder()
	{
		int base = 0;
		List<SkillLevel> above = repo.findWithGreaterOrEqual(0);
		for(SkillLevel level : above)
		{
			level.setValue(base);
			repo.save(level);
			base++;
		}
		return "Successfully reordered.";
	}

	/**
	 * Updates the skill level indicated by the input object in the way 
	 * indicated by the input object. Shifts other values up or down if necessary.
	 * 
	 * @param level
	 * 		An object containing the information needed to indicate which SkillLevel
	 * 		to update and how to update it.
	 * @return
	 * 		Returns a confirmation message.
	 */
	@PostMapping(path="/update")
	@CrossOrigin
	public @ResponseBody String update(@RequestBody @Valid RequestSkillLevelUpdate level)
	{
		Optional<SkillLevel> toUpdateTemp = repo.findByName(level.getName());
		if(!toUpdateTemp.isPresent())
		{
			return "Error. The requested skill level no longer exists.";
		}
		SkillLevel toUpdate = toUpdateTemp.get();
		if(level.getNewName() != null && !level.getName().equals(level.getNewName()))
		{
			toUpdate.setName(level.getNewName());
			repo.save(toUpdate);
		}
		if(level.getValue() != null && level.getValue() != toUpdate.getValue())
		{
			int val = level.getValue();
			Optional<SkillLevel> current = repo.findByValue(val);
			if(!current.isPresent())
			{
				toUpdate.setValue(val);
				repo.save(toUpdate);
			}
			else
			{
				int oldval = toUpdate.getValue();
				Optional<Integer> maxopt = repo.findMaxValue();
				int max = maxopt.get();
				toUpdate.setValue(max + 1);
				repo.save(toUpdate);
				List<SkillLevel> items;
				if(oldval < val)
				{
					items = repo.findInIntervalUpperInclusive(oldval, val);
					for(SkillLevel item : items)
					{
						item.setValue(item.getValue() - 1);
						repo.save(item);
					}
					toUpdate.setValue(val);
					repo.save(toUpdate);
				}
				else
				{
					items = repo.findInIntervalLowerInclusive(val, oldval);
					for(SkillLevel item : items)
					{
						item.setValue(item.getValue() + 1);
						repo.save(item);
					}
					toUpdate.setValue(val);
					repo.save(toUpdate);
				}
			}
		}
		return "Skill level " + level.getName() + " successfully updated.";
	}

	/**
	 * Returns the SkillLevel with the name indicated by the given input object.
	 * @param level
	 * 		An object containing the name of the SkillLevel to return.
	 * @return
	 * 		The SkillLevel with the name indicated by the given input.
	 */
	@JsonView(View.SkillLevelView.class)
	@GetMapping(path="/get")
	@CrossOrigin
	public @ResponseBody SkillLevel get(@RequestBody @Valid RequestSkillLevel level)
	{
		return repo.findByName(level.getName()).get();
	}

	/**
	 * Returns the list of all SkillLevels.
	 * @return
	 * 		The list of all SkillLevels.
	 */
	@JsonView(View.SkillLevelView.class)
	@GetMapping(path="/all")
	@CrossOrigin
	public @ResponseBody Iterable<SkillLevel> getAllSkillLevels(){
		return repo.findAll();
	}
}
