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

import com.application.skill_level.RequestNewSkillLevel;
import com.application.skill_level.SkillLevel;
import com.application.skill_level.SkillLevelRepository;
import com.application.util.BeanUtil;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class NewSkillLevelValidatorTests {

	public NewSkillLevelValidatorTests() {}
	
	@MockBean
	private SkillLevelRepository skillRepo;
	
	
	Validator validator;
	
	RequestNewSkillLevel toTest;
	
	MockedStatic<BeanUtil> mocked;
	
	Set<ConstraintViolation<RequestNewSkillLevel>> cvs;
	
	@BeforeEach
	public void setUp()
	{
		try{
			mocked = mockStatic(BeanUtil.class);
			mocked.when(()->BeanUtil.getBean(SkillLevelRepository.class)).thenReturn(skillRepo);
		}
		catch(Exception e)
		{
			if(mocked != null) {mocked.close();}
			throw e;
		}
		SkillLevel testSkill = new SkillLevel("test-value",3);
		SkillLevel othertestSkill = new SkillLevel("test-value3",4);
		Mockito.when(skillRepo.findByName(Mockito.eq(testSkill.getName()))).thenReturn(Optional.of(testSkill));
		Mockito.when(skillRepo.findByName(Mockito.eq(othertestSkill.getName()))).thenReturn(Optional.of(othertestSkill));
		Mockito.when(skillRepo.findByName(AdditionalMatchers.not(AdditionalMatchers.or(Mockito.eq(testSkill.getName()), Mockito.eq(othertestSkill.getName()))))).thenReturn(Optional.ofNullable(null));
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}
	
	
	@AfterEach
	public void cleanUp()
	{
		Mockito.reset(skillRepo);
		mocked.reset();
		mocked.close();
	}
	
	@Test
	public void validNewTestFull()
	{
		toTest = new RequestNewSkillLevel("test-value2",5);
		cvs = validator.validate(toTest);
		Assert.assertTrue("SkillLevels with a valid name and a valid value should be accepted.", cvs.isEmpty());
	}
	
	@Test
	public void validNewTestFullValSteal()
	{
		toTest = new RequestNewSkillLevel("test-value2",4);
		cvs = validator.validate(toTest);
		Assert.assertTrue("SkillLevels with a valid name and a valid value should be accepted, even if the value belongs to a different item.", cvs.isEmpty());
	}
	
	@Test
	public void validNewTestFullUnset()
	{
		toTest = new RequestNewSkillLevel("unset",0);
		cvs = validator.validate(toTest);
		Assert.assertTrue("SkillLevels with name 'unset' should be accepted if value is 0, and vise-versa.", cvs.isEmpty());
	}
	
	
	@Test
	public void nullOrEmptyNameAndValueTest()
	{
		toTest = new RequestNewSkillLevel(null,null);
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevels with null names and null values should be rejected.", cvs.isEmpty());
		
		toTest = new RequestNewSkillLevel("",null);
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevels with empty names and null values should be rejected.", cvs.isEmpty());
	}
	
	@Test
	public void nullOrEmptyNameTest()
	{
		toTest = new RequestNewSkillLevel(null,6);
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevels with null names should be rejected.", cvs.isEmpty());
		
		toTest = new RequestNewSkillLevel("",6);
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevels with empty names should be rejected.", cvs.isEmpty());
	}
	
	@Test
	public void nullValueTest()
	{
		toTest = new RequestNewSkillLevel("test-value2", null);
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevels with null values should be rejected, basic version.", cvs.isEmpty());
		
		toTest = new RequestNewSkillLevel("unset", null);
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevels with null values should be rejected, 'unset' version.", cvs.isEmpty());
	}
	
	@Test
	public void unsetNonZeroTest()
	{
		toTest = new RequestNewSkillLevel("unset",6);
		cvs = validator.validate(toTest);
		Assert.assertFalse("Value of 'unset' cannot be positive, only 0.", cvs.isEmpty());
		
		toTest = new RequestNewSkillLevel("unset",-40);
		cvs = validator.validate(toTest);
		Assert.assertFalse("Value of 'unset' cannot be negative, only 0.", cvs.isEmpty());
	}
	
	@Test
	public void zeroValueTest()
	{
		toTest = new RequestNewSkillLevel("test-value2",0);
		cvs = validator.validate(toTest);
		Assert.assertFalse("Only 'unset' can have value of 0.", cvs.isEmpty());
		
	}
	
	@Test
	public void badNameTest()
	{
		toTest = new RequestNewSkillLevel("super value",9);
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevel names should not be allowed to contain whitespace.", cvs.isEmpty());
		
		toTest = new RequestNewSkillLevel("super\tvalue", 9);
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevel names should not be allowed to contain tabs.", cvs.isEmpty());
	}
	
	@Test
	public void alreadyExistentNameTest()
	{
		toTest = new RequestNewSkillLevel("test-value",3);
		cvs = validator.validate(toTest);
		Assert.assertFalse("New SkillLevel names should not match other skill levels already in existence, same value version.", cvs.isEmpty());
		
		toTest = new RequestNewSkillLevel("test-value",9);
		cvs = validator.validate(toTest);
		Assert.assertFalse("New SkillLevel names should not match other skill levels already in existence, new value version.", cvs.isEmpty());
	}
	
	@Test
	public void negativeValueTest()
	{
		toTest = new RequestNewSkillLevel("test-value2", -40);
		cvs = validator.validate(toTest);
		Assert.assertFalse("Skill values should not be allowed to take on negative values.", cvs.isEmpty());
	}
	
}