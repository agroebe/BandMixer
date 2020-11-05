package com.application.searching;

public class Subquery extends QueryPart 
{
	private UserQuery child;
	
	public Subquery(Query parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SearchCriteria generate(RootHandler handler) {
		return new BasicSearchCriteria("owner",child.generate(handler),SearchOperation.IN,type,QueryClass.ePost);
	}
	
	public void setChild(UserQuery c)
	{
		child = c;
	}

}
