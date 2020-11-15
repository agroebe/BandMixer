package com.application.searching.inputLayer;

import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.QueryPart;

public class MappedSubPostContentType extends MappedSubPostPart {
	String type;
	String comparison;
	
	public MappedSubPostContentType()
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
	public QueryPart<Long> map(SubPostRootHandler handler, QueryService service) {
		return QueryPartFactory.generatePostPart(handler, type, "contentType", comparison);
	}
}
