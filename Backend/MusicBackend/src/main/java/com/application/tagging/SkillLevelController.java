package com.application.tagging;

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
	public ResponseEntity<String> add(@RequestBody @Valid RequestNewSkillLevelWrapper newLevel)
	{
		String message = "Skill Level " + newLevel.getNewLevel().getName() + " added.";
		Optional<SkillLevel> old = repo.findByValue(newLevel.getNewLevel().getValue());
		if(old.isPresent() == false)
		{
			message = "Skill Level " + newLevel.getNewLevel().getName() + " inserted.";
			List<SkillLevel> above = repo.findWithGreaterOrEqual(newLevel.getNewLevel().getValue());
			Stack<SkillLevel> toAdjust = new Stack<>();
			int base = newLevel.getNewLevel().getValue() - 1;
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
			SkillLevel toAdd = new SkillLevel(newLevel.getNewLevel().getName(), newLevel.getNewLevel().getValue());
			repo.save(toAdd);
		}

		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}

	@PostMapping(path="/remove")
	@CrossOrigin
	public ResponseEntity<String> remove(@RequestBody @Valid RequestSkillLevelWrapper level)
	{
		Optional<SkillLevel> toRemoveTemp = repo.findByName(level.getLevel().getName());
		if(!toRemoveTemp.isPresent())
		{
			return new ResponseEntity<String>("Error. The requested skill level no longer exists.", HttpStatus.CREATED);
		}
		SkillLevel toRemove = toRemoveTemp.get();
		Optional<SkillLevel> below = repo.findFirstLess(toRemove.getValue());
		Optional<SkillLevel> above = repo.findFirstGreater(toRemove.getValue());
		Set<AppliedSkillLevel> apps = toRemove.getApplications();
		if(apps.isEmpty())
		{
			toRemove.remove(applicationRepo, repo);
			return new ResponseEntity<String>("Skill Level " + level.getLevel().getName() + " removed", HttpStatus.CREATED);
		}
		else if(!above.isPresent() && !below.isPresent())
		{
			toRemove.remove(applicationRepo, repo);
			return new ResponseEntity<String>("Skill Level " + level.getLevel().getName() + " removed. No way to refactor containing tags", HttpStatus.CREATED);
		}
		else if(!above.isPresent())
		{
			toRemove.remove(applicationRepo, repo, below.get());
			return new ResponseEntity<String>("Skill Level " + level.getLevel().getName() +
					" removed. All tags paired with it have been refactored downwards.", HttpStatus.CREATED);
		}
		else if(!below.isPresent())
		{
			toRemove.remove(applicationRepo, repo, above.get());
			return new ResponseEntity<String>("Skill Level " + level.getLevel().getName() +
					" removed. All tags paired with it have been refactored upwards.", HttpStatus.CREATED);
		}
		else
		{
			toRemove.remove(applicationRepo, repo, above.get(), below.get());
			return new ResponseEntity<String>("Skill Level " + level.getLevel().getName() +
					" removed. All lower bounded tags paired with it have been refactored upwards."
					+" All other tags paired with it have been refactored downwards.", HttpStatus.CREATED);
		}
	}

	@PostMapping(path="/reorder")
	@CrossOrigin
	public ResponseEntity<String> reorder()
	{
		int base = 0;
		List<SkillLevel> above = repo.findWithGreaterOrEqual(0);
		for(SkillLevel level : above)
		{
			level.setValue(base);
			repo.save(level);
			base++;
		}
		return new ResponseEntity<String>("Successfully reordered.", HttpStatus.CREATED);
	}

	@PostMapping(path="/update")
	@CrossOrigin
	public ResponseEntity<String> update(@RequestBody @Valid RequestSkillLevelUpdateWrapper level)
	{
		Optional<SkillLevel> toUpdateTemp = repo.findByName(level.getLevel().getName());
		if(!toUpdateTemp.isPresent())
		{
			return new ResponseEntity<String>("Error. The requested skill level no longer exists.", HttpStatus.CREATED);
		}
		SkillLevel toUpdate = toUpdateTemp.get();
		if(level.getLevel().getNewName() != null && level.getLevel().getName().trim().equals(level.getLevel().getNewName().trim()))
		{
			toUpdate.setName(level.getLevel().getNewName().trim());
			repo.save(toUpdate);
		}
		if(level.getLevel().getValue() != null && level.getLevel().getValue() != toUpdate.getValue())
		{
			int val = level.getLevel().getValue();
			Optional<SkillLevel> current = repo.findByValue(val);
			if(!current.isPresent())
			{
				toUpdate.setValue(val);
			}
			else
			{
				int oldval = toUpdate.getValue();
				toUpdate.setValue(val);
				List<SkillLevel> items;
				if(oldval < val)
				{
					items = repo.findInIntervalUpperInclusive(oldval, val);
					for(SkillLevel item : items)
					{
						item.setValue(item.getValue() - 1);
					}
					items.add(toUpdate);
					repo.saveAll(items);
				}
				else
				{
					items = repo.findInIntervalLowerInclusive(val, oldval);
					for(SkillLevel item : items)
					{
						item.setValue(item.getValue() + 1);
					}
					items.add(toUpdate);
					repo.saveAll(items);
				}
			}
		}
		return new ResponseEntity<String>("Skill level " + level.getLevel().getName().trim() + " successfully updated.", HttpStatus.CREATED);
	}

	@GetMapping(path="/get")
	@CrossOrigin
	public ResponseEntity<SkillLevel> get(@RequestBody @Valid RequestSkillLevelWrapper level)
	{
		return new ResponseEntity<SkillLevel>(repo.findByName(level.getLevel().getName()).get(), HttpStatus.CREATED);
	}

	@GetMapping(path="/all")
	@CrossOrigin
	public @ResponseBody Iterable<SkillLevel> getAllSkillLevels(){
		return repo.findAll();
	}
}
