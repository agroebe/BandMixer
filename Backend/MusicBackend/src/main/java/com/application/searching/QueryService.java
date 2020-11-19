package com.application.searching;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.people.User;
import com.application.people.UserRepository;
import com.application.posts.Post;
import com.application.posts.PostRepository;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.inputLayer.MappedQuery;
import com.application.searching.queryLayer.Query;
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
		Query<T> q = query.firstmap(this);
		EntityGraph<T> graph = q.getGraph();
		EntityManager em = q.getHandler().getManager();
		List<T> results = em.createQuery(q.generate()).setHint("javax.persistence.loadgraph", graph).getResultList();
		em.close();
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
