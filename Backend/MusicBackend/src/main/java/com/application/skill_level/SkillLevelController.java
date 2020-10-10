package com.application.skill_level;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;

import javax.validation.Valid;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 
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

	@GetMapping(path="/get")
	@CrossOrigin
	public @ResponseBody SkillLevel get(@RequestBody @Valid RequestSkillLevel level)
	{
		return repo.findByName(level.getName()).get();
	}

	@GetMapping(path="/all")
	@CrossOrigin
	public @ResponseBody Iterable<SkillLevel> getAllSkillLevels(){
		return repo.findAll();
	}
}
