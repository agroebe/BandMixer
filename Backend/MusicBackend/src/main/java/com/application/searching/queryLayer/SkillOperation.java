package com.application.searching.queryLayer;

import com.application.searching.criteriaLayer.QueryClass;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.criteriaLayer.SearchOperation;

public class SkillOperation<T> extends QueryOperationPart<T>
{

	public SkillOperation(RootHandler<T> handler, SearchOperation op, String field, Object val) {
		super(handler, QueryClass.eSkill, op, field, val);
	}

}