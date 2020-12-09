package com.chancrawler.chancrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@SpringBootApplication
@EnableScheduling
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
		SpringApplication application = new SpringApplication(ChanCrawlerApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

}
