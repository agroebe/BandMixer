package com.application.searching.inputLayer;

import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.QueryPart;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("title")
public class MappedSubPostTitle extends MappedSubPostPart 
{
	String title;
	String comparison;
	
	public MappedSubPostTitle()
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
	public QueryPart<Long> map(SubPostRootHandler handler, QueryService service) {
		return QueryPartFactory.generatePostPart(handler, title, "title", comparison);
	}

}
