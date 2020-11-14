package com.application.searching.queryLayer;

import com.application.people.User;
import com.application.posts.Post;
import com.application.searching.criteriaLayer.RootHandler;
import com.application.searching.criteriaLayer.SubPostRootHandler;

public class PostSubquery extends MySubquery<Long>
{

	protected PostSubquery(SubPostRootHandler handler) {
		super(handler);
	}

}
