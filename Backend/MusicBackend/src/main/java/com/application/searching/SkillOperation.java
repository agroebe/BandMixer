package com.application.searching;

public class SkillOperation<T> extends QueryOperationPart<T>
{

	public SkillOperation(RootHandler<T> handler, SearchOperation op, String field, Object val) {
		super(handler, QueryClass.eSkill, op, field, val);
	}

}