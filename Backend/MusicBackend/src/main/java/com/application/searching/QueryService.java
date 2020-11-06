package com.application.searching;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.people.User;
import com.application.people.UserRepository;
import com.application.posts.Post;
import com.application.posts.PostRepository;
import com.application.skill_level.AppliedSkillLevelRepository;
import com.application.skill_level.SkillLevelRepository;
import com.application.tagging.TagRepository;

@Service
public class QueryService 
{
	@Autowired
	private RootService roots;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private AppliedSkillLevelRepository appliedSkillRepo;
	
	@Autowired
	private TagRepository tagRepo;
	
	@Autowired
	private SkillLevelRepository skillRepo;
	
	
	public <T> List<T> executeQuery(MappedQuery<T> query)
	{
		MappingQuery<T> q = query.map();
		List<T> results = q.getHandler().getManager().createQuery(q.generate()).getResultList();
		return results;
	}
	
	public RootHandler<User> getUserHandler()
	{
		return roots.getUserHandler();
	}
	
	public RootHandler<Post> getPostHandler()
	{
		return roots.getPostHandler();
	}
	
	public UserRepository getUserRepo()
	{
		return userRepo;
	}
	
	public PostRepository getPostRepo()
	{
		return postRepo;
	}
	
	public AppliedSkillLevelRepository getAppliedSkillRepo()
	{
		return appliedSkillRepo;
	}

	public TagRepository getTagRepo() {
		return tagRepo;
	}

	public SkillLevelRepository getSkillRepo() {
		return skillRepo;
	}

}
