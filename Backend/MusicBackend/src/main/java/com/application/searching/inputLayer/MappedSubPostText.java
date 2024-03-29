package com.application.searching.inputLayer;

import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.QueryPart;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("textContent")
public class MappedSubPostText extends MappedSubPostPart {

	private String text;
	private String comparison;
	
	public MappedSubPostText()
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
	public QueryPart<Long> map(SubPostRootHandler handler, QueryService service) {
		return QueryPartFactory.generatePostPart(handler, text, "textContent", comparison);
	}

}
