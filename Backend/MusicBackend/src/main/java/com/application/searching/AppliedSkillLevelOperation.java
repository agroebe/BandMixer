package com.application.searching;

public class AppliedSkillLevelOperation<T> extends QueryOperationPart<T>
{

	public AppliedSkillLevelOperation(RootHandler<T> handler, SearchOperation op, String field, Object val) {
		super(handler, QueryClass.eAppliedSkill, op, field, val);
	}

}