package com.application.searching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.View;
import com.application.people.User;
import com.application.posts.Post;
import com.application.searching.inputLayer.MappedPostQuery;
import com.application.searching.inputLayer.MappedUserQuery;
import com.fasterxml.jackson.annotation.JsonView;

@Controller
@RequestMapping(path="/search") 
public class SearchController 
{
	@Autowired
	QueryService service;
	
	@JsonView(View.UserView.class)
	@CrossOrigin
	@GetMapping(path="/user")
	public @ResponseBody Iterable<User> searchUsers(MappedUserQuery query)
	{
		return service.executeQuery(query);
	}
	
	@JsonView(View.PostView.class)
	@CrossOrigin
	@GetMapping(path="/post")
	public @ResponseBody Iterable<Post> searchPosts(MappedPostQuery query)
	{
		return service.executeQuery(query);
	}
}
