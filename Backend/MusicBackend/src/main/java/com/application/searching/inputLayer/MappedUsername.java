package com.application.searching.inputLayer;

import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.QueryPart;

public class MappedUsername extends MappedProfilePart {

	String username;
	String comparison;
	
	public MappedUsername()
	{
		username = "";
		comparison = "equal";
	}
	
	public String getUsername() {return username;}
	
	public void setUsername(String username) {this.username = (username==null?"":username);}
	
	public String getComparison() {return comparison;}
	
	public void setComparison(String comparison)
	{
		this.comparison = (comparison == null || comparison.equals("")? "equal":comparison);
	}
	
	@Override
	public QueryPart<Long> map(SubPostRootHandler handler, QueryService service) {
		return QueryPartFactory.generatePart(handler, username, "username", comparison,"profile");
	}


}
