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

import com.application.tagging.RequestNewTag;
import com.application.tagging.Tag;
import com.application.tagging.TagRepository;
import com.application.util.BeanUtil;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class NewTagValidatorTests {

	public NewTagValidatorTests() {}
	
	@MockBean
	private TagRepository tagRepo;
	
	
	Validator validator;
	
	RequestNewTag toTest;
	
	Set<ConstraintViolation<RequestNewTag>> cvs;
	
	@BeforeEach
	public void setUp()
	{
		try (MockedStatic<BeanUtil> mocked = mockStatic(BeanUtil.class)){
			mocked.when(()->BeanUtil.getBean(TagRepository.class)).thenReturn(tagRepo);
		}
		Tag testTag = new Tag("test-value");
		Tag othertestTag = new Tag("test-value3",false);
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
	}
	
	@Test
	public void validNewTestFull()
	{
		toTest = new RequestNewTag("test-value2",true);
		cvs = validator.validate(toTest);
		Assert.assertTrue("New Tags with valid names should be accepted, true accepts skill version.", cvs.isEmpty());
		
		toTest = new RequestNewTag("test-value2",false);
		cvs = validator.validate(toTest);
		Assert.assertTrue("New Tags with valid names should be accepted, false accepts skill version.", cvs.isEmpty());
	}
	
	@Test
	public void nullOrEmptyNameTest()
	{
		toTest = new RequestNewTag(null, true);
		cvs = validator.validate(toTest);
		Assert.assertFalse("New Tags with null names should be rejected, true accepts skill version.", cvs.isEmpty());
		
		toTest = new RequestNewTag(null, false);
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevels with null names should be rejected, false accepts skill version.", cvs.isEmpty());
		
		toTest = new RequestNewTag("",true);
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevels with empty names should be rejected, true accepts skill version.", cvs.isEmpty());
		
		toTest = new RequestNewTag("",false);
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevels with empty names should be rejected, false accepts skill version.", cvs.isEmpty());
	}
	
	@Test
	public void badNameTest()
	{
		toTest = new RequestNewTag("super value",true);
		cvs = validator.validate(toTest);
		Assert.assertFalse("Tag names should not be allowed to contain whitespace, true accepts skill version.", cvs.isEmpty());
		
		toTest = new RequestNewTag("super value",false);
		cvs = validator.validate(toTest);
		Assert.assertFalse("Tag names should not be allowed to contain whitespace, false accepts skill version.", cvs.isEmpty());
		
		toTest = new RequestNewTag("super\tvalue", true);
		cvs = validator.validate(toTest);
		Assert.assertFalse("Tag names should not be allowed to contain tabs, true accepts skill version.", cvs.isEmpty());
		
		toTest = new RequestNewTag("super\tvalue", false);
		cvs = validator.validate(toTest);
		Assert.assertFalse("Tag names should not be allowed to contain tabs, false accepts skill version.", cvs.isEmpty());
	}
	
	@Test
	public void alreadyExistentNameTest()
	{
		toTest = new RequestNewTag("test-value",true);
		cvs = validator.validate(toTest);
		Assert.assertFalse("New Tag names should not match other tags already in existence, true accepts skill version.", cvs.isEmpty());
		
		toTest = new RequestNewTag("test-value",false);
		cvs = validator.validate(toTest);
		Assert.assertFalse("New Tag names should not match other tags already in existence, false accepts skill version.", cvs.isEmpty());
	}
	
}
