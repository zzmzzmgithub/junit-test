package service;

import org.springframework.beans.factory.annotation.Autowired;

import servise.Helloword;

public class HelloJavaImpl implements HelloJava {

	 private String  name;
	 
	 @Autowired
	 private Helloword helloword;
	 
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public void name() {
		System.out.println(this.name);
		helloword.sayHello();
	}

}
