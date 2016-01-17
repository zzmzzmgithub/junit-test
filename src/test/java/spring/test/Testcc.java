package spring.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import service.HelloJava;
import servise.Helloword;

public class Testcc extends TestCave {
	@Resource
	private Helloword helloword;
	
	@Autowired
	private HelloJava hellojava;
	
	@Test
	public void yy() {
		helloword.sayHello();
	}
	@Test
	public void aa(){
		hellojava.name();
	}
}
