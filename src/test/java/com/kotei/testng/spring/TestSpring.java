package com.kotei.testng.spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.kotei.controller.MainController;
import com.kotei.service.EmailGenerator;
/**
 * 测试用例
 * @author langl
 *
 */

@Test
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
public class TestSpring extends AbstractTestNGSpringContextTests {

	@Autowired
	EmailGenerator emailGenerator;
	//@Autowired
	//MainController mainContaroller;
	
	

	@Test()
	void testEmailGenerator() {

		String email = emailGenerator.generate();
		//mainController.getdata();
		
		//System.out.println(email);

		Assert.assertNotNull(email);
		Assert.assertEquals(email, "feedback@kotei.com");
		

	}

}