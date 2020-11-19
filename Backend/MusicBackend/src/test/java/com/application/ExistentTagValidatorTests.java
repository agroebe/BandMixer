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

import com.application.tagging.RequestExistentTag;
import com.application.tagging.RequestNewTag;
import com.application.tagging.Tag;
import com.application.tagging.TagRepository;
import com.application.util.BeanUtil;


@SpringBootTest(classes = MusicBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class ExistentTagValidatorTests {

	public ExistentTagValidatorTests() {}
	
	@MockBean
	private TagRepository tagRepo;
	
	
	Validator validator;
	
	RequestExistentTag toTest;
	
	MockedStatic<BeanUtil> mocked;
	
	Set<ConstraintViolation<RequestExistentTag>> cvs;
	
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
		mocked.reset();
		mocked.close();
	}
	
	@Test
	public void validTest()
	{
		toTest = new RequestExistentTag();
		toTest.setName("test-value");
		cvs = validator.validate(toTest);
		Assert.assertTrue("Requests for existent tags where a tag of the name exists should be accepted.", cvs.isEmpty());
		
	}
	
	@Test
	public void nullOrEmptyNameTest()
	{
		toTest = new RequestExistentTag();
		cvs = validator.validate(toTest);
		Assert.assertFalse("Requests for existent tags with null names should be rejected, constructor version.", cvs.isEmpty());
		
		toTest = new RequestExistentTag();
		toTest.setName("test-value");
		toTest.setName(null);
		cvs = validator.validate(toTest);
		Assert.assertFalse("Requests for existent tags with null names should be rejected, setter version.", cvs.isEmpty());
		
		
		toTest = new RequestExistentTag();
		toTest.setName("");
		cvs = validator.validate(toTest);
		Assert.assertFalse("Requests for existent tags with empty names should be rejected.", cvs.isEmpty());
	}
	
	
	
	@Test
	public void notExistentNameTest()
	{
		toTest = new RequestExistentTag();
		toTest.setName("test-value4");
		cvs = validator.validate(toTest);
		Assert.assertFalse("Requests for existent tags where a tag of the name does not exist should be rejected.", cvs.isEmpty());
	}
	
}
