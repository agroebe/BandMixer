package com.application.searching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

import com.application.people.User;
import com.application.posts.Post;

@Service
public class RootService 
{
	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;
	
	
	public RootService(LocalContainerEntityManagerFactoryBean b)
	{
		entityManagerFactory = b;
	}
	
	public RootHandler<User> getUserHandler()
	{
		return new UserRootHandler(entityManagerFactory.getObject().createEntityManager());
	}
	
	public RootHandler<Post> getPostHandler()
	{
		return new PostRootHandler(entityManagerFactory.getObject().createEntityManager());
	}
}
