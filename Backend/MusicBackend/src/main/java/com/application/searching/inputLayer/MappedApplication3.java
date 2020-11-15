package com.application.searching.inputLayer;

import java.util.Optional;

import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.ApplicationRootHandler;
import com.application.searching.criteriaLayer.QueryClass;
import com.application.searching.criteriaLayer.SearchOperation;
import com.application.searching.criteriaLayer.SearchOperator;
import com.application.searching.criteriaLayer.SubPostRootHandler;
import com.application.searching.queryLayer.ApplicationSubquery;
import com.application.searching.queryLayer.ApplicationSubqueryPart;
import com.application.searching.queryLayer.QueryOperationPart;
import com.application.searching.queryLayer.QueryOperatorPart;
import com.application.searching.queryLayer.QueryPart;
import com.application.skill_level.SkillLevel;
import com.application.tagging.Tag;

public class MappedApplication3 extends MappedProfilePart 
{

	private String tag;
	private String operation;
	private String skill;
	
	public MappedApplication3()
	{
		tag = "";
		operation = "equal";
		skill = "unset";
	}
	
	public String getTag() {return tag;}
	
	public void setTag(String tag)
	{
		this.tag = (tag==null? "":tag);
	}
	
	public String getSkill() {return skill;}
	
	public void setSkill(String skill)
	{
		this.skill = (skill==null || skill.equals("")? "unset":skill);
	}
	
	public String getOperation() {return operation;}
	
	public void setOperation(String operation)
	{
		this.operation = (operation==null || operation.equals("")? "equal":operation);
	}
	
	@Override
	public QueryPart<Long> map(SubPostRootHandler handler, QueryService service) {
		ApplicationRootHandler h = handler.generateApplicationQuery();
		ApplicationSubquery q = new ApplicationSubquery(h);
		QueryPart<Long> child;
		Optional<Tag> t = service.getTagRepo().findByName(tag);
		Optional<SkillLevel> s = service.getSkillRepo().findByName(skill);
		if(skill == "unset")
		{
			child = new QueryOperationPart<>(h,QueryClass.eTag,SearchOperation.EQUAL,"name",tag);
		}
		else if(!t.isPresent() || !s.isPresent())
		{
			child = new QueryOperatorPart<>(h, SearchOperator.AND);
			QueryOperationPart<Long> p = new QueryOperationPart<>(h,QueryClass.eTag,SearchOperation.EQUAL,"name",tag);
			((QueryOperatorPart<Long>)child).addChild(p);
			p = new QueryOperationPart<>(h,QueryClass.eSkill,SearchOperation.EQUAL,"name",skill);
			((QueryOperatorPart<Long>)child).addChild(p);
		}
		else
		{
			SkillLevel sk = s.get();
			Tag tg = t.get();
			if(!tg.getAllowskill())
			{
				child = new QueryOperationPart<>(h,QueryClass.eTag,SearchOperation.EQUAL,"name",tag);
			}
			else
			{
				child = new QueryOperatorPart<>(h, SearchOperator.AND);
				SearchOperation op;
				switch(operation)
				{
					case "equal":
					{
						op = SearchOperation.EQUAL;
						break;
					}
					case "not equal":
					{
						op = SearchOperation.NOT_EQUAL;
						break;
					}
					case "less than":
					{
						op = SearchOperation.LESS_THAN;
						break;
					}
					case "less than equal":
					{
						op = SearchOperation.LESS_THAN_EQUAL;
						break;
					}
					case "greater than":
					{
						op = SearchOperation.GREATER_THAN;
						break;
					}
					case "greater than equal":
					{
						op = SearchOperation.GREATER_THAN_EQUAL;
						break;
					}
					default:
					{
						throw new IllegalArgumentException("Illegal argument for skill level comparison.");
					}
				}
				child = new QueryOperatorPart<>(h, SearchOperator.AND);
				QueryOperationPart<Long> p = new QueryOperationPart<>(h,QueryClass.eTag,SearchOperation.EQUAL,"name",tag);
				((QueryOperatorPart<Long>)child).addChild(p);
				p = new QueryOperationPart<>(h,QueryClass.eSkill,op,"value",sk.getValue());
				((QueryOperatorPart<Long>)child).addChild(p);
			}
		}
		q.setChild(child);
		ApplicationSubqueryPart<Long> ret= new ApplicationSubqueryPart<>(handler);
		ret.setChild(q);
		return ret;
	}

}
