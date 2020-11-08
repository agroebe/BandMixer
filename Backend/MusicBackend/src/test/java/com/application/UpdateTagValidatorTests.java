package com.application;

import static org.mockito.Mockito.mockStatic;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.application.tagging.RequestUpdateTag;
import com.application.tagging.Tag;
import com.application.tagging.TagRepository;
import com.application.util.BeanUtil;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class UpdateTagValidatorTests {

	public UpdateTagValidatorTests() {}
	
	@MockBean
	private TagRepository tagRepo;
	
	
	Validator validator;
	MockedStatic<BeanUtil> mocked;
	
	@BeforeEach
	public void setUp()
	{
		try {
			mocked = mockStatic(BeanUtil.class);
			mocked.when(()->BeanUtil.getBean(TagRepository.class)).thenReturn(tagRepo);
		}
		catch(Exception e)
		{
			if(mocked != null) {mocked.close();}
			throw e;
		}
		Tag testTag = new Tag("test-tag",false);
		Tag othertestTag = new Tag("test-tag3",false);
		Mockito.when(tagRepo.findByName(Mockito.eq(testTag.getName()))).thenReturn(Optional.of(testTag));
		Mockito.when(tagRepo.findByName(Mockito.eq(othertestTag.getName()))).thenReturn(Optional.of(othertestTag));
		Mockito.when(tagRepo.findByName(AdditionalMatchers.not(AdditionalMatchers.or(Mockito.eq(testTag.getName()), Mockito.eq(othertestTag.getName()))))).thenReturn(Optional.ofNullable(null));
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}
	
	@AfterEach
	public void cleanUp()
	{
		Mockito.reset(tagRepo);
		mocked.reset();
		mocked.close();
	}
	
	@Test
	public void validUpdateTestFull()
	{
		RequestUpdateTag toTest = new RequestUpdateTag("test-tag", "test-tag2", true);
		Set<ConstraintViolation<RequestUpdateTag>> cvs = validator.validate(toTest);
		Assert.assertTrue("Updates with a valid new name and a different skill allowance should be accepted.", cvs.isEmpty());
	}
	
	@Test
	public void validUpdateTestSkill()
	{
		RequestUpdateTag toTest = new RequestUpdateTag("test-tag", null, true);
		Set<ConstraintViolation<RequestUpdateTag>> cvs = validator.validate(toTest);
		Assert.assertTrue("Updates with a different skill allowance should be accepted, even if new name is unchanged via null.", cvs.isEmpty());
		
		toTest = new RequestUpdateTag("test-tag", "", true);
		cvs = validator.validate(toTest);
		Assert.assertTrue("Updates with a different skill allowance should be accepted, even if new name is unchanged via empty string.", cvs.isEmpty());
		
		toTest = new RequestUpdateTag("test-tag", "test-tag", true);
		cvs = validator.validate(toTest);
		Assert.assertTrue("Updates with a different skill allowance should be accepted, even if new name is unchanged via same name.", cvs.isEmpty());
	}
	
	@Test
	public void validUpdateTestName()
	{
		RequestUpdateTag toTest = new RequestUpdateTag("test-tag", "test-tag2", false);
		Set<ConstraintViolation<RequestUpdateTag>> cvs = validator.validate(toTest);
		Assert.assertTrue("Updates with a valid new name should be accepted, even if the skill allowance is unchanged.", cvs.isEmpty());
	}
	
	@Test
	public void nullOrEmptyNameTest()
	{
		RequestUpdateTag toTest = new RequestUpdateTag();
		Set<ConstraintViolation<RequestUpdateTag>> cvs = validator.validate(toTest);
		Assert.assertFalse("Tag updates with null names should be rejected.", cvs.isEmpty());
		
		toTest = new RequestUpdateTag("");
		cvs = validator.validate(toTest);
		Assert.assertFalse("Tag updates with empty names should be rejected.", cvs.isEmpty());
	}

	@Test
	public void nonexistentTagTest()
	{
		RequestUpdateTag toTest = new RequestUpdateTag("test-tag2");
		Set<ConstraintViolation<RequestUpdateTag>> cvs = validator.validate(toTest);
		Assert.assertFalse("Updates with non-existent tags should be rejected.", cvs.isEmpty());
	}
	
	@Test
	public void badNewNameTest()
	{
		RequestUpdateTag toTest = new RequestUpdateTag("test-tag", "super tag", false);
		Set<ConstraintViolation<RequestUpdateTag>> cvs = validator.validate(toTest);
		Assert.assertFalse("Updated Tag names should not be allowed to contain whitespace.", cvs.isEmpty());
		
		toTest = new RequestUpdateTag("test-tag", "super\ttag", false);
		cvs = validator.validate(toTest);
		Assert.assertFalse("Updated Tag names should not be allowed to contain tabs.", cvs.isEmpty());
	}
	
	@Test
	public void alreadyExistentNewNameTest()
	{
		RequestUpdateTag toTest = new RequestUpdateTag("test-tag", "test-tag3", false);
		Set<ConstraintViolation<RequestUpdateTag>> cvs = validator.validate(toTest);
		Assert.assertFalse("Tag names should not be updated to match other tags already in existence.", cvs.isEmpty());
	}
	
	@Test
	public void noUpdatesTest()
	{
		RequestUpdateTag toTest = new RequestUpdateTag("test-tag", null, false);
		Set<ConstraintViolation<RequestUpdateTag>> cvs = validator.validate(toTest);
		Assert.assertFalse("Tag updates with nothing to update should be rejected, null name version.", cvs.isEmpty());
		
		toTest = new RequestUpdateTag("test-tag", "", false);
		cvs = validator.validate(toTest);
		Assert.assertFalse("Tag updates with nothing to update should be rejected, empty string name version.", cvs.isEmpty());
		
		toTest = new RequestUpdateTag("test-tag", "test-tag", false);
		cvs = validator.validate(toTest);
		Assert.assertFalse("Tag updates with nothing to update should be rejected, same name version.", cvs.isEmpty());
	}
	
	
}
