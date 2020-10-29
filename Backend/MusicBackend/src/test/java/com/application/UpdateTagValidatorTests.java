package com.application;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.application.tagging.RequestUpdateTag;
import com.application.tagging.Tag;
import com.application.tagging.TagRepository;


@SpringBootTest
//@RunWith(SpringRunner.class)
class UpdateTagValidatorTests 
{
	@MockBean
	private TagRepository tagRepo;
	
	@InjectMocks
	@Autowired
	Validator validator;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		Tag testTag = new Tag("test-tag");
		Mockito.when(tagRepo.findByName(Mockito.eq(testTag.getName()))).thenReturn(Optional.of(testTag));
		Mockito.when(tagRepo.findByName(AdditionalMatchers.not(Mockito.eq(testTag.getName())))).thenReturn(Optional.ofNullable(null));
	}
	
	@Test
	void basicValidatorTest()
	{
		RequestUpdateTag toTest = new RequestUpdateTag("test-tag", "test-tag2", true);
		Set<ConstraintViolation<RequestUpdateTag>> cvs = validator.validate(toTest);
		Assert.assertTrue("Must not be constraint violations.", cvs.isEmpty());
	}
	
	@Test
	void basicValidatorTest2()
	{
		RequestUpdateTag toTest = new RequestUpdateTag("test-tag2", "test-tag2", true);
		Set<ConstraintViolation<RequestUpdateTag>> cvs = validator.validate(toTest);
		Assert.assertTrue("Must not be constraint violations.", cvs.isEmpty());
	}
}
