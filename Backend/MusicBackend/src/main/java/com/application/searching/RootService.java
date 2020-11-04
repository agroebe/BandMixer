package com.application.searching;

import java.util.EnumSet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class RootService 
{
	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;
	
	
	public RootService(LocalContainerEntityManagerFactoryBean b)
	{
		entityManagerFactory = b;
	}
	
	public RootHandler getHandler(QueryType type, EnumSet<QueryDependency> dependencies)
	{
		return new RootHandler(entityManagerFactory.getObject().createEntityManager(),type,dependencies);
	}
}
