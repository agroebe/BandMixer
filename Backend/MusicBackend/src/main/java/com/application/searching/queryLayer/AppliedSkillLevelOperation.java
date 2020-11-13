package com.application.searching.queryLayer;

import com.application.searching.criteriaLayer.QueryClass;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.criteriaLayer.SearchOperation;

public class AppliedSkillLevelOperation<T> extends QueryOperationPart<T>
{

	public AppliedSkillLevelOperation(RootHandler<T> handler, SearchOperation op, String field, Object val) {
		super(handler, QueryClass.eAppliedSkill, op, field, val);
	}

}