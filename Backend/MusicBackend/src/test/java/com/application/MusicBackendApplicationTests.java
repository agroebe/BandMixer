package com.application;

import static org.mockito.Mockito.mockStatic;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.application.tagging.RequestUpdateTag;
import com.application.tagging.Tag;
import com.application.tagging.TagRepository;
import com.application.util.BeanUtil;


@SpringBootTest
@ExtendWith(SpringExtension.class)
//@PrepareForTest(BeanUtil.class)
class MusicBackendApplicationTests {

	public MusicBackendApplicationTests() {}
	@MockBean
	private TagRepository tagRepo;
	
	
	
	//@InjectMocks
	//@Autowired
	Validator validator;
	
	@BeforeEach
	public void setUp()
	{
		//PowerMockito.mockStatic(BeanUtil.class);
		//PowerMockito.when(BeanUtil.getBean(TagRepository.class)).thenReturn(tagRepo);
		try (MockedStatic<BeanUtil> mocked = mockStatic(BeanUtil.class)){
			mocked.when(()->BeanUtil.getBean(TagRepository.class)).thenReturn(tagRepo);
		}
		Tag testTag = new Tag("test-tag");
		Mockito.when(tagRepo.findByName(Mockito.eq(testTag.getName()))).thenReturn(Optional.of(testTag));
		Mockito.when(tagRepo.findByName(AdditionalMatchers.not(Mockito.eq(testTag.getName())))).thenReturn(Optional.ofNullable(null));
		//BeanUtil.setMock(tagRepo);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}
	
	@Test
	public void basicValidatorTest()
	{
		RequestUpdateTag toTest = new RequestUpdateTag("test-tag", "test-tag2", true);
		Set<ConstraintViolation<RequestUpdateTag>> cvs = validator.validate(toTest);
		Assert.assertTrue("Must not be constraint violations.", cvs.isEmpty());
	}
	
//	@Test
//	public void basicValidatorTest2()
//	{
//		RequestUpdateTag toTest = new RequestUpdateTag("test-tag2", "test-tag2", true);
//		Set<ConstraintViolation<RequestUpdateTag>> cvs = validator.validate(toTest);
//		Assert.assertTrue("Must not be constraint violations.", cvs.isEmpty());
//	}

}
