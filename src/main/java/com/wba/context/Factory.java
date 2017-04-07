package main.java.com.wba.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Factory {

	public static ApplicationContext getContext() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		return context;
	}

}
