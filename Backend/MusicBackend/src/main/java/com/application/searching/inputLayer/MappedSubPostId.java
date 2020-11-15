package com.application.searching.inputLayer;

import javax.validation.constraints.NotNull;

import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.QueryPart;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("id")
public class MappedSubPostId extends MappedSubPostPart 
{
	@NotNull
	private Long id;
	
	private String comparison;
	
	public MappedSubPostId()
	{
		id = null;
		comparison = "equal";
	}
	
	public Long getId() {return id;}
	
	public void setId(Long id) {this.id = id;}
	
	public String getComparison() {return comparison;}
	
	public void setComparison(String comparison)
	{
		this.comparison = (comparison == null || comparison.equals("")? "equal":comparison);
	}
	
	@Override
	public QueryPart<Long> map(SubPostRootHandler handler, QueryService service) {
		return QueryPartFactory.generatePostPart(handler, id, "id", comparison);
	}

}
