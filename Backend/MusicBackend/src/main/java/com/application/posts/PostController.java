package com.application.posts;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.application.ProgramaticValidator;
import com.application.View;
import com.application.posts.files.FileDB;
import com.application.posts.files.FileStorageService;
import com.application.posts.message.ResponseMessage;
import com.application.skill_level.AppliedSkillLevelRepository;
import com.application.skill_level.SkillLevel;
import com.application.skill_level.SkillLevelRepository;
import com.application.tagging.RequestTagApplication;
import com.application.tagging.Tag;
import com.application.tagging.TagRepository;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@Autowired
    private FileStorageService storageService;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private ProgramaticValidator validator;
	
	@JsonView(View.PostView.class)
	@GetMapping(path="/all")
	@CrossOrigin
    public @ResponseBody Iterable<Post> getAllPosts(){
        //Returns a JSON or XML document with the users in it
        return postRepository.findAll();
    }
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(RequestNewPost.class, new RequestNewPostEditor(mapper, validator));
		binder.registerCustomEditor(RequestPostType.class, new RequestPostTypeEditor(mapper, validator));
	}
	
	@JsonView(View.PostView.class)
	@GetMapping(path="/fetch")
    public @ResponseBody Post getById(@RequestBody @Valid RequestPost post)
    {
		long id = post.getId();
    	Optional<Post> foundpost = postRepository.findById(id);
    	return foundpost.get();
    }
	
	
	@PostMapping(path="/update")
    public @ResponseBody String updatePost(@RequestBody @Valid RequestUpdatePost updatePost)
    {
		
		Optional<Post> find = postRepository.findById(updatePost.getId());
    	String newTitle = updatePost.getTitle();
		String newType = updatePost.getContentType();
		String newText = updatePost.getTextContent();
		boolean newSearch = updatePost.getIsSearch();
		String newPath = updatePost.getContentPath();
		
		
		Post found = find.get();
		
		boolean notitlechange = newTitle == null || newTitle.equals(found.getTitle());
		boolean notypechange = newType == null || newType.equals(found.getContentType());
		boolean notextchange = newText == null ||(newText.equals(found.getTextContent()) && !newText.equals("")) 
				|| (found.getTextContent() == null && newText.equals(""));
		boolean nosearchChange = newSearch == found.getIsSearch();
		boolean nopathchange = newPath == null || (newPath.equals(found.getContentPath()) && !newPath.equals("")) 
				|| (found.getContentPath() == null && newPath.equals(""));
		
		if(!notitlechange)
		{
			found.setTitle(newTitle);
		}
		if(!nopathchange)
		{
			if(found.getContentPath() != null && !found.getContentType().contains("External"))
			{
				storageService.removeFile(found.getContentPath());
			}
			found.setContentPath(newPath);
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
	
	@PostMapping(path="/updateContent")
    public @ResponseBody ResponseEntity<ResponseMessage> updateContent(@RequestParam @Valid RequestPostType updatePost, @RequestParam(name="file", required=false) MultipartFile file)
    {
		Post p = postRepository.findById(updatePost.getId()).get();
		if(p.getContentPath() != null && !p.getContentPath().contains("External"));
		{
			storageService.removeFile(p.getContentPath());
		}
		String fpath = null;
		String message = "";
		if(file != null)
		{
	        try {
	           FileDB stored = storageService.store(file);
	           fpath = stored.getId();
	            message = "Uploaded the file successfully: " + file.getOriginalFilename() + "; ";
	            
	        } catch (Exception e) {
	            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	        }
		}
		p.setContentPath(fpath);
		p.setContentType(updatePost.getContentType());
		postRepository.save(p);
		return null;
    }
	
	@PostMapping(path="/remove")
	public @ResponseBody String removePost(@RequestBody @Valid RequestPost requestPost)
	{
		Optional<Post> post = postRepository.findById(requestPost.getId());
    	Post p =post.get();
    	if(p.getContentPath() != null && !p.getContentType().contains("External"))
    	{
    		storageService.removeFile(p.getContentPath());
    	}
    	p.remove(applicationRepository, postRepository);
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
		post.get().updateTag(applicationRepository, tg, level, tag.getBounded(), tag.getLowerBounded());
    	return "Tag updated.";
	}
}
