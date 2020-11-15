package com.application.searching.inputLayer;

import com.application.posts.Post;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.PostRootHandler;
import com.application.searching.queryLayer.QueryPart;

public class MappedPostContentType extends MappedPostPart {

	String type;
	String comparison;
	
	public MappedPostContentType()
	{
		type = "";
		comparison = "equal";
	}
	
	public String getType() {return type;}
	
	public void setType(String type) {this.type = (type==null?"":type);}
	
	public String getComparison() {return comparison;}
	
	public void setComparison(String comparison)
	{
		this.comparison = (comparison == null || comparison.equals("")? "equal":comparison);
	}
	
	@Override
	public QueryPart<Post> map(PostRootHandler handler, QueryService service) {
		return QueryPartFactory.generatePostPart(handler, type, "contentType", comparison);
	}

}
