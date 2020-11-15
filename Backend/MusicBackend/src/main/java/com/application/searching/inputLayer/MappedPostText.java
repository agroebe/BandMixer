package com.application.searching.inputLayer;

import com.application.posts.Post;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.PostRootHandler;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.QueryPart;

public class MappedPostText extends MappedPostPart {

	String text;
	String comparison;
	
	public MappedPostText()
	{
		text = "";
		comparison = "equal";
	}
	
	public String getText() {return text;}
	
	public void setText(String text) {this.text = (text==null?"":text);}
	
	public String getComparison() {return comparison;}
	
	public void setComparison(String comparison)
	{
		this.comparison = (comparison == null || comparison.equals("")? "equal":comparison);
	}
	
	@Override
	public QueryPart<Post> map(PostRootHandler handler, QueryService service) {
		return QueryPartFactory.generatePostPart(handler, text, "textContent", comparison);
	}

}
