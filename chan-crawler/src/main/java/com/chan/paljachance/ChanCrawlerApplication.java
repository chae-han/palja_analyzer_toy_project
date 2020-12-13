package com.chan.paljachance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//For this example, you need not modify the AccessingDataMysqlApplication class.
//
//@SpringBootApplication is a convenience annotation that adds all of the following:
//
//@Configuration: Tags the class as a source of bean definitions for the application context.
//
//@EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet.
//
//@ComponentScan: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers.
//
//		The main() method uses Spring Boot’s SpringApplication.run() method to launch an application. Did you notice that there was not a single line of XML? There is no web.xml file, either. This web application is 100% pure Java and you did not have to deal with configuring any plumbing or infrastructure.

@SpringBootApplication
public class ChanCrawlerApplication {

	private static Logger logger;

	static {
		try {
			final InputStream inputStream = ChanCrawlerApplication.class
					.getClassLoader()
					.getResourceAsStream("Logger.properties");

			LogManager.getLogManager().readConfiguration(inputStream);
			logger = Logger.getLogger(ChanCrawlerApplication.class.getName());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		//SpringApplication.run(ChanCrawlerApplication.class, args);
		Set<String> configs = new HashSet<String>();

		SpringApplication application = new SpringApplication(ChanCrawlerApplication.class);
		configs.add("classpath:AppConfig.xml");
		application.setSources(configs);

		//application.setWebApplicationType(WebApplicationType);
		application.run(args);
	}

}
