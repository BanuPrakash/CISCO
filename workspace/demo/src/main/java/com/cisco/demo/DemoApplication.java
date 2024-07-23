package com.cisco.demo;

import com.cisco.demo.entity.Employee;
import com.cisco.demo.service.AppService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

		String[] beans = ctx.getBeanDefinitionNames();
		for(String bean: beans) {
			System.out.println(bean);
		}

		System.out.println("******");

		AppService service = ctx.getBean("appService", AppService.class);
		Employee e = new Employee("Roger", "roger@cisco.com");
		service.insert(e);
	}

}
