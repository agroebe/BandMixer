package com.application.searching.inputLayer;

import java.util.Optional;

import com.application.posts.Post;
import com.application.searching.QueryService;
import com.application.searching.criteriaLayer.ApplicationRootHandler;
import com.application.searching.criteriaLayer.PostRootHandler;
import com.application.searching.criteriaLayer.QueryClass;
import com.application.searching.criteriaLayer.SearchOperation;
import com.application.searching.criteriaLayer.SearchOperator;
import com.application.searching.queryLayer.ApplicationSubquery;
import com.application.searching.queryLayer.ApplicationSubqueryPart;
import com.application.searching.queryLayer.QueryOperationPart;
import com.application.searching.queryLayer.QueryOperatorPart;
import com.application.searching.queryLayer.QueryPart;
import com.application.skill_level.SkillLevel;
import com.application.tagging.Tag;

public class MappedApplication1 extends MappedPostPart
{
	private String tag;
	private String operation;
	private String skill;
	
	public MappedApplication1()
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
	public QueryPart<Post> map(PostRootHandler handler, QueryService service) {
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
				child = new QueryOperatorPart<>(h, SearchOperator.OR);
				QueryOperatorPart<Long> basic= new QueryOperatorPart<>(h, SearchOperator.AND);
				QueryOperationPart<Long> p = new QueryOperationPart<>(h,QueryClass.eTag,SearchOperation.EQUAL,"name",tag);
				basic.addChild(p);
				p = new QueryOperationPart<>(h,QueryClass.eSkill,op,"value",sk.getValue());
				basic.addChild(p);
				((QueryOperatorPart<Long>)child).addChild(basic);
				
				QueryOperationPart<Long> bounded1 = new QueryOperationPart<>(h,QueryClass.eAppliedSkill,SearchOperation.NOT_EQUAL,
						"isBounded",(Integer)0);
				QueryOperationPart<Long> bounded2 = new QueryOperationPart<>(h,QueryClass.eAppliedSkill,SearchOperation.NOT_EQUAL,
						"isBounded",(Integer)0);
				QueryOperationPart<Long> lower = new QueryOperationPart<>(h,QueryClass.eAppliedSkill,SearchOperation.NOT_EQUAL,
						"isLowerBound",(Integer)0);
				QueryOperationPart<Long> upper = new QueryOperationPart<>(h,QueryClass.eAppliedSkill,SearchOperation.EQUAL,
						"isLowerBound",(Integer)0);
				QueryOperatorPart<Long> lowerBounded = new QueryOperatorPart<>(h,SearchOperator.AND);
				lowerBounded.addChild(bounded1);
				lowerBounded.addChild(lower);
				QueryOperatorPart<Long> upperBounded = new QueryOperatorPart<>(h,SearchOperator.AND);
				upperBounded.addChild(bounded2);
				upperBounded.addChild(upper);
				QueryOperationPart<Long> seenByLower = new QueryOperationPart<>(h,QueryClass.eSkill,
						SearchOperation.LESS_THAN_EQUAL,"value",sk.getValue());
				QueryOperationPart<Long> seenByUpper = new QueryOperationPart<>(h,QueryClass.eSkill,
						SearchOperation.GREATER_THAN_EQUAL,"value",sk.getValue());
				lowerBounded.addChild(seenByLower);
				upperBounded.addChild(seenByUpper);
				((QueryOperatorPart<Long>)child).addChild(lowerBounded);
				((QueryOperatorPart<Long>)child).addChild(upperBounded);
			}
		}
		q.setChild(child);
		ApplicationSubqueryPart<Post> ret= new ApplicationSubqueryPart<>(handler);
		ret.setChild(q);
		return ret;
	}

}
