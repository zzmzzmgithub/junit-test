package servise;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHello {


	private static ClassPathXmlApplicationContext context;

	// 加载 spring配置文件
	public static void init() {
	}

	public static void main(String[] args) {
		init();
		context = new ClassPathXmlApplicationContext("spring/spring-helloword.xml");
		context.start();
		//HelloWordImpl hell =new HelloWordImpl();
		HelloWordImpl hell =(HelloWordImpl)context.getBean("helloword");
		hell.sayHello();
	}

}
