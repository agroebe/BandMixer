package com.application.searching.inputLayer;

import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.QueryPart;

public abstract class MappedProfilePart {
	public abstract QueryPart<Long> map(SubPostRootHandler handler, QueryService service);
}
