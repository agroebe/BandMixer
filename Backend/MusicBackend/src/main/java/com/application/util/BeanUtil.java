package com.application.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class BeanUtil implements ApplicationContextAware {

   private static ApplicationContext context;
   
   private static Object mockbean = null;

   @Override

   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

       context = applicationContext;

   }
   
   public static void setMock(Object o)
   {
	   mockbean = o;
   }
   
   public static Object getMock() {return mockbean;}

   public static <T> T getBean(Class<T> beanClass) {

//	   if(isJUnitTest())
//	   {
//		   
//		   return Mockito.mock(beanClass);
//	   }
       return context.getBean(beanClass);

   }
   
   public static boolean isJUnitTest() {  
	   for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
	     if (element.getClassName().startsWith("org.junit.")) {
	       return true;
	     }           
	   }
	   return false;
	 }

}
