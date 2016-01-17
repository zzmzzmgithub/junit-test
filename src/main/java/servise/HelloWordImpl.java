package servise;

import java.util.Date;

public class HelloWordImpl implements Helloword {

	@Override
	public void sayHello() {
		
		System.out.println( new Date ());

	}

}
