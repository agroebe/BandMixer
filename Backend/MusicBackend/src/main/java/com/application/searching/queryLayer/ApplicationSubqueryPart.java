package com.application.searching.queryLayer;

import com.application.searching.criteriaLayer.QueryClass;
import com.application.searching.criteriaLayer.RootHandler;

public class ApplicationSubqueryPart<R> extends SubqueryPart<R, Long> {

	protected ApplicationSubqueryPart(RootHandler<R> handler) {
		super(handler, "id", QueryClass.ePost);
		// TODO Auto-generated constructor stub
	}

}
