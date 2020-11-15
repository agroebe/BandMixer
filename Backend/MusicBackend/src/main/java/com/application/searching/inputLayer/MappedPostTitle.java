package com.application.searching.inputLayer;

import com.application.posts.Post;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.PostRootHandler;
import com.application.searching.queryLayer.QueryPart;

public class MappedPostTitle extends MappedPostPart
{
	String title;
	String comparison;
	
	public MappedPostTitle()
	{
		title = "";
		comparison = "equal";
	}
	
	public String getTitle() {return title;}
	
	public void setTitle(String title) {this.title = (title==null?"":title);}
	
	public String getComparison() {return comparison;}
	
	public void setComparison(String comparison)
	{
		this.comparison = (comparison == null || comparison.equals("")? "equal":comparison);
	}
	
	@Override
	public QueryPart<Post> map(PostRootHandler handler, QueryService service) {
		return QueryPartFactory.generatePostPart(handler, title, "title", comparison);
	}

}
