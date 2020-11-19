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
import com.application.skill_level.RequestSkillLevel;
import com.application.skill_level.SkillLevel;
import com.application.skill_level.SkillLevelRepository;
import com.application.tagging.RequestExistentTag;
import com.application.util.BeanUtil;



@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MusicBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExistentSkillLevelValidatorTests {

	public ExistentSkillLevelValidatorTests() {}
	
	@MockBean
	private SkillLevelRepository skillRepo;
	
	
	Validator validator;
	
	RequestSkillLevel toTest;
	
	MockedStatic<BeanUtil> mocked;
	
	Set<ConstraintViolation<RequestSkillLevel>> cvs;
	
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
	public void validTest()
	{
		toTest = new RequestSkillLevel();
		toTest.setName("test-value");
		cvs = validator.validate(toTest);
		Assert.assertTrue("Requests for existent skill levels where a skill level of the name exists should be accepted, setter version.", cvs.isEmpty());
		
		toTest = new RequestSkillLevel("test-value");
		cvs = validator.validate(toTest);
		Assert.assertTrue("Requests for existent skill levels where a skill level of the name exists should be accepted, constructor version.", cvs.isEmpty());
		
	}
	
	
	
	@Test
	public void notExistentNameTest()
	{
		toTest = new RequestSkillLevel();
		toTest.setName("test-value4");
		cvs = validator.validate(toTest);
		Assert.assertFalse("Requests for existent skill levels where a skill level of the name does not exist should be rejected, setter version.", cvs.isEmpty());
		
		toTest = new RequestSkillLevel("test-value4");
		cvs = validator.validate(toTest);
		Assert.assertFalse("Requests for existent skill levels where a skill level of the name does not exist should be rejected, constructor version.", cvs.isEmpty());
		
	}
	
}