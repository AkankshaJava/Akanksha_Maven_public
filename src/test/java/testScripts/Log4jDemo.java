package testScripts;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

public class Log4jDemo {

	Logger log = Logger.getLogger(Log4jDemo.class);

	@Test
	public void m1() {
		PropertyConfigurator.configure("src/main/resources/log4j.properties");

		log.trace("This is trace");
		log.debug("This is debug");
		log.info("this is info");
		log.warn("this is warn");
		log.error("this is error");
		log.fatal("this is fatal");

	}

}
