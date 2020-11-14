package com.application.searching.inputLayer;

import com.application.people.User;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.QueryPart;

public abstract class MappedSubPostPart 
{
	public abstract QueryPart<Long> map(SubPostRootHandler handler, QueryService service);
}
