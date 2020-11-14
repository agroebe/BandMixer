package com.application.searching.inputLayer;

import com.application.people.User;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.queryLayer.QueryPart;

public abstract class MappedApplicationPart 
{
	public abstract QueryPart<Long> map(RootHandler<Long> handler, QueryService service);
}
