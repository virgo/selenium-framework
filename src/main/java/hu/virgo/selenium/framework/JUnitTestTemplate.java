package hu.virgo.selenium.framework;

import hu.virgo.selenium.framework.context.Context;
import hu.virgo.selenium.framework.debug.DebugDataStore;

import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

public class JUnitTestTemplate {

	protected static Context context = new Context();
	protected WebDriver driver;
	protected Utils utils;
	protected DebugDataStore debugData;

	@Rule
	public TestWatcher watchman = new TestWatcher() {

		@Override
		protected void starting(Description description) {
			driver = context.getNewDriver();
			debugData = new DebugDataStore();
			utils = new Utils(driver);
		}

		@Override
		protected void failed(Throwable e, Description description) {
			debugData.put("testCase", description.getClassName() + "." + description.getMethodName());
			debugData.put("cookies", driver.manage().getCookies());
			debugData.put("currentUrl", driver.getCurrentUrl());
			System.err.println(debugData.flush());
		}

		@Override
		protected void finished(Description description) {
			driver.quit();
			driver = null;
		}
	};

}
