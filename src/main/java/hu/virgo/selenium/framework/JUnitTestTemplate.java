package hu.virgo.selenium.framework;

import hu.virgo.selenium.framework.context.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

public class JUnitTestTemplate {

	protected static Context context;
	protected WebDriver driver;
	protected Utils utils;

	@BeforeClass
	public static void initContext() {
		context = new Context();
	}

	@Before
	public void initTest() {
		driver = context.getNewDriver();
		utils = new Utils(driver);
	}

	@After
	public void tearDown() {
		driver.quit();
		driver = null;
	}
}
