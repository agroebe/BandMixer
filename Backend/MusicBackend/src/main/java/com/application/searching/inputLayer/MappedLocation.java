package com.application.searching.inputLayer;

import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.QueryPart;

public class MappedLocation extends MappedProfilePart 
{
	String location;
	String comparison;
	
	public MappedLocation()
	{
		location = "";
		comparison = "equal";
	}
	
	public String getLocation() {return location;}
	
	public void setLocation(String location) {this.location = (location==null?"":location);}
	
	public String getComparison() {return comparison;}
	
	public void setComparison(String comparison)
	{
		this.comparison = (comparison == null || comparison.equals("")? "equal":comparison);
	}
	
	@Override
	public QueryPart<Long> map(SubPostRootHandler handler, QueryService service) {
		return QueryPartFactory.generatePart(handler, location, "location", comparison,"profile");
	}
}
