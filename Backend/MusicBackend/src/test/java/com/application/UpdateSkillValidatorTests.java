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

import com.application.skill_level.RequestSkillLevelUpdate;
import com.application.skill_level.SkillLevel;
import com.application.skill_level.SkillLevelRepository;
import com.application.tagging.TagRepository;
import com.application.util.BeanUtil;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class UpdateSkillValidatorTests {

	public UpdateSkillValidatorTests() {}
	
	@MockBean
	private SkillLevelRepository skillRepo;
	
	
	Validator validator;
	MockedStatic<BeanUtil> mocked;
	
	@BeforeEach
	public void setUp()
	{
		try {
			mocked = mockStatic(BeanUtil.class);
			mocked.when(()->BeanUtil.getBean(SkillLevelRepository.class)).thenReturn(skillRepo);
		}
		catch(Exception e)
		{
			if(mocked != null) {mocked.close();}
			throw e;
		}
		SkillLevel unsetSkill = new SkillLevel("unset",0);
		SkillLevel testSkill = new SkillLevel("test-value",3);
		SkillLevel othertestSkill = new SkillLevel("test-value3",4);
		Mockito.when(skillRepo.findByName(Mockito.eq(unsetSkill.getName()))).thenReturn(Optional.of(unsetSkill));
		Mockito.when(skillRepo.findByName(Mockito.eq(testSkill.getName()))).thenReturn(Optional.of(testSkill));
		Mockito.when(skillRepo.findByName(Mockito.eq(othertestSkill.getName()))).thenReturn(Optional.of(othertestSkill));
		Mockito.when(skillRepo.findByName(AdditionalMatchers.not(AdditionalMatchers.or(AdditionalMatchers.or(Mockito.eq(unsetSkill.getName()),Mockito.eq(testSkill.getName())), Mockito.eq(othertestSkill.getName()))))).thenReturn(Optional.ofNullable(null));
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
	public void validUpdateTestFull()
	{
		RequestSkillLevelUpdate toTest = new RequestSkillLevelUpdate("test-value", "test-value2",5);
		Set<ConstraintViolation<RequestSkillLevelUpdate>> cvs = validator.validate(toTest);
		Assert.assertTrue("Updates with a valid new name and a new, valid value should be accepted.", cvs.isEmpty());
	}
	
	@Test
	public void validUpdateTestFullValSteal()
	{
		RequestSkillLevelUpdate toTest = new RequestSkillLevelUpdate("test-value", "test-value2",4);
		Set<ConstraintViolation<RequestSkillLevelUpdate>> cvs = validator.validate(toTest);
		Assert.assertTrue("Updates with a valid new name and a new, valid value should be accepted, even if the value belongs to a different item.", cvs.isEmpty());
	}
	
	@Test
	public void validUpdateTestValue()
	{
		RequestSkillLevelUpdate toTest = new RequestSkillLevelUpdate("test-value", null, 5);
		Set<ConstraintViolation<RequestSkillLevelUpdate>> cvs = validator.validate(toTest);
		Assert.assertTrue("Updates with a different skill value should be accepted, even if new name is unchanged via null.", cvs.isEmpty());
		
		toTest = new RequestSkillLevelUpdate("test-value", "", 5);
		cvs = validator.validate(toTest);
		Assert.assertTrue("Updates with a different skill value should be accepted, even if new name is unchanged via empty string.", cvs.isEmpty());
		
		toTest = new RequestSkillLevelUpdate("test-value", "test-value",5);
		cvs = validator.validate(toTest);
		Assert.assertTrue("Updates with a different skill value should be accepted, even if new name is unchanged via same name.", cvs.isEmpty());
		
		toTest = new RequestSkillLevelUpdate("test-value", null, 4);
		cvs = validator.validate(toTest);
		Assert.assertTrue("Updates with a different skill value(matching other skill value) should be accepted, even if new name is unchanged via null.", cvs.isEmpty());
		
		toTest = new RequestSkillLevelUpdate("test-value", "", 4);
		cvs = validator.validate(toTest);
		Assert.assertTrue("Updates with a different skill value(matching other skill value) should be accepted, even if new name is unchanged via empty string.", cvs.isEmpty());
		
		toTest = new RequestSkillLevelUpdate("test-value", "test-value",4);
		cvs = validator.validate(toTest);
		Assert.assertTrue("Updates with a different skill value(matching other skill value) should be accepted, even if new name is unchanged via same name.", cvs.isEmpty());
	}
	
	@Test
	public void validUpdateTestName()
	{
		RequestSkillLevelUpdate toTest = new RequestSkillLevelUpdate("test-value", "test-value2");
		Set<ConstraintViolation<RequestSkillLevelUpdate>> cvs = validator.validate(toTest);
		Assert.assertTrue("Updates with a valid new name should be accepted, even if the skill value is unchanged, null value version.", 
				cvs.isEmpty());
		
		toTest = new RequestSkillLevelUpdate("test-value", "test-value2", 3);
		cvs = validator.validate(toTest);
		Assert.assertTrue("Updates with a valid new name should be accepted, even if the skill value is unchanged, same value version.", 
				cvs.isEmpty());
	}
	
	@Test
	public void nullOrEmptyNameTest()
	{
		RequestSkillLevelUpdate toTest = new RequestSkillLevelUpdate(null,"test-value2",6);
		Set<ConstraintViolation<RequestSkillLevelUpdate>> cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevel updates with null names should be rejected.", cvs.isEmpty());
		
		toTest = new RequestSkillLevelUpdate("","test-value2",6);
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevel updates with empty names should be rejected.", cvs.isEmpty());
	}

	@Test
	public void nonexistentSkillTest()
	{
		RequestSkillLevelUpdate toTest = new RequestSkillLevelUpdate("test-value2","test-value4",6);
		Set<ConstraintViolation<RequestSkillLevelUpdate>> cvs = validator.validate(toTest);
		Assert.assertFalse("Updates with non-existent skill levels should be rejected.", cvs.isEmpty());
	}
	
	@Test
	public void updatingUnsetTest()
	{
		RequestSkillLevelUpdate toTest = new RequestSkillLevelUpdate("unset","test-value4",6);
		Set<ConstraintViolation<RequestSkillLevelUpdate>> cvs = validator.validate(toTest);
		Assert.assertFalse("Updates to 'unset' should be rejected.", cvs.isEmpty());
		
		toTest = new RequestSkillLevelUpdate("unset","test-value4",0);
		cvs = validator.validate(toTest);
		Assert.assertFalse("Updates to 'unset' should be rejected, even if value remains 0.", cvs.isEmpty());
	}
	
	@Test
	public void badNewNameTest()
	{
		RequestSkillLevelUpdate toTest = new RequestSkillLevelUpdate("test-value", "super value");
		Set<ConstraintViolation<RequestSkillLevelUpdate>> cvs = validator.validate(toTest);
		Assert.assertFalse("Updated SkillLevel names should not be allowed to contain whitespace.", cvs.isEmpty());
		
		toTest = new RequestSkillLevelUpdate("test-value", "super\tvalue");
		cvs = validator.validate(toTest);
		Assert.assertFalse("Updated SkillLevel names should not be allowed to contain tabs.", cvs.isEmpty());
	}
	
	@Test
	public void alreadyExistentNewNameTest()
	{
		RequestSkillLevelUpdate toTest = new RequestSkillLevelUpdate("test-value", "test-value3");
		Set<ConstraintViolation<RequestSkillLevelUpdate>> cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevel names should not be updated to match other skill levels already in existence.", cvs.isEmpty());
	}
	
	@Test
	public void unsetAsNewNameTest()
	{
		RequestSkillLevelUpdate toTest = new RequestSkillLevelUpdate("test-value", "unset");
		Set<ConstraintViolation<RequestSkillLevelUpdate>> cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevel names should not be updated to 'unset' already in existence.", cvs.isEmpty());
	}
	
	@Test
	public void zeroAsValueTest()
	{
		RequestSkillLevelUpdate toTest = new RequestSkillLevelUpdate("test-value", "test-value2", 0);
		Set<ConstraintViolation<RequestSkillLevelUpdate>> cvs = validator.validate(toTest);
		Assert.assertFalse("Skill values should not be allowed to take on the value 0, which is reserved for 'unset', updated name version.", cvs.isEmpty());
		
		toTest = new RequestSkillLevelUpdate("test-value", null, 0);
		cvs = validator.validate(toTest);
		Assert.assertFalse("Skill values should not be allowed to take on the value 0, which is reserved for 'unset', unchanged name version.", cvs.isEmpty());
	}
	
	@Test
	public void negativeValueTest()
	{
		RequestSkillLevelUpdate toTest = new RequestSkillLevelUpdate("test-value", "test-value2", -40);
		Set<ConstraintViolation<RequestSkillLevelUpdate>> cvs = validator.validate(toTest);
		Assert.assertFalse("Skill values should not be allowed to take on negative values, updated name version.", cvs.isEmpty());
		
		toTest = new RequestSkillLevelUpdate("test-value", null, -40);
		cvs = validator.validate(toTest);
		Assert.assertFalse("Skill values should not be allowed to take on negative values, unchanged name version.", cvs.isEmpty());
	}
	
	@Test
	public void noUpdatesTest()
	{
		RequestSkillLevelUpdate toTest = new RequestSkillLevelUpdate("test-value");
		Set<ConstraintViolation<RequestSkillLevelUpdate>> cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevel updates with nothing to update should be rejected, null name null value version.", cvs.isEmpty());
		
		toTest = new RequestSkillLevelUpdate("test-value", null, 3);
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevel updates with nothing to update should be rejected, null name same value version.", cvs.isEmpty());
		
		toTest = new RequestSkillLevelUpdate("test-value", "");
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevel updates with nothing to update should be rejected, empty string name null value version.", cvs.isEmpty());
		
		toTest = new RequestSkillLevelUpdate("test-value", "", 3);
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevel updates with nothing to update should be rejected, empty string name same value version.", cvs.isEmpty());
		
		toTest = new RequestSkillLevelUpdate("test-value", "test-value");
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevel updates with nothing to update should be rejected, same name null value version.", cvs.isEmpty());
		
		toTest = new RequestSkillLevelUpdate("test-value", "test-value", 3);
		cvs = validator.validate(toTest);
		Assert.assertFalse("SkillLevel updates with nothing to update should be rejected, same name same value version.", cvs.isEmpty());
	}
	
	
}