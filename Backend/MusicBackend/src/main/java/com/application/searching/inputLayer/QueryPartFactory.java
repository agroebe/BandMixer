package com.application.searching.inputLayer;

import com.application.searching.criteriaLayer.QueryClass;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.criteriaLayer.SearchOperation;
import com.application.searching.queryLayer.QueryOperationPart;
import com.application.searching.queryLayer.QueryPart;

public class QueryPartFactory 
{
	public static <R> QueryPart<R> generatePart(RootHandler<R> handler, Object part, String partname, String operation, String cls)
	{
		QueryOperationPart<R> ret = new QueryOperationPart<R>(handler, getClss(cls),getOp(operation),partname,part);
		return ret;
	}
	
	public static <R> QueryPart<R> generatePostPart(RootHandler<R> handler, Object part, String partname, String operation)
	{
		return generatePart(handler, part, partname, operation, "post");
	}
	
	public static <R> QueryPart<R> generateUserPart(RootHandler<R> handler, Object part, String partname, String operation)
	{
		return generatePart(handler, part, partname, operation, "user");
	}
	
	private static SearchOperation getOp(String operation)
	{
		switch(operation)
		{
			case "greater than":
			{
				return SearchOperation.GREATER_THAN;
			}
			case "greater than equal":
			{
				return SearchOperation.GREATER_THAN_EQUAL;
			}
			case "less than":
			{
				return SearchOperation.LESS_THAN;
			}
			case "less than equal":
			{
				return SearchOperation.LESS_THAN_EQUAL;
			}
			case "equal":
			{
				return SearchOperation.EQUAL;
			}
			case "not equal":
			{
				return SearchOperation.NOT_EQUAL;
			}
			case "in":
			{
				return SearchOperation.IN;
			}
			case "not in":
			{
				return SearchOperation.NOT_IN;
			}
			case "match":
			{
				return SearchOperation.MATCH;
			}
			case "match start":
			{
				return SearchOperation.MATCH_START;
			}
			case "match end":
			{
				return SearchOperation.MATCH_END;
			}
			default:
			{
				throw new IllegalArgumentException("Invalid operation");
			}
		}
	}
	
	private static QueryClass getClss(String cls)
	{
		switch(cls)
		{
			case "user":
			{
				return QueryClass.eUser;
			}
			case "post":
			{
				return QueryClass.ePost;
			}
			case "profile":
			{
				return QueryClass.eProfile;
			}
			default:
			{
				throw new IllegalArgumentException("Invalid query class");
			}
		}
	}
}
