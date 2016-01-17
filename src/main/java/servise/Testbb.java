package servise;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import servise.Helloword;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/spring-helloword.xml")
public class Testbb extends AbstractJUnit4SpringContextTests {
	@Resource
    private Helloword helloword;
	
	@Test
	public void yy(){
		helloword.sayHello();
	}
}

