package hu.virgo.selenium.framework;

import hu.virgo.selenium.framework.context.Context;
import hu.virgo.selenium.framework.debug.DebugDataStore;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
			debugData.put("buildNumber", context.getBuildNumber());
			utils = new Utils(driver);
		}

		@Override
		protected void failed(Throwable e, Description description) {
			debugData.put("testCase", description.getClassName() + "." + description.getMethodName());
			debugData.put("cookies", driver.manage().getCookies());
			debugData.put("currentUrl", driver.getCurrentUrl());
			save();
		}

		@Override
		protected void finished(Description description) {
			driver.quit();
			driver = null;
		}

		private void save() {
			String resultsFolder = "target" + File.separator + "selenium-results";
			File folder = new File(resultsFolder);
			folder.mkdir();

			File selScreenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File imageFile = new File(resultsFolder + File.separator + context.getBuildNumber() + time() + ".png");
			try {
				FileUtils.copyFile(selScreenShot, imageFile);
				debugData.put("screenShot", imageFile.getCanonicalPath());
			} catch (IOException e) {
				e.printStackTrace();
			}

			File logFile = new File(resultsFolder + File.separator + context.getBuildNumber() + time() + ".json");
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));) {
				writer.write(debugData.flush());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private String time() {
			return new SimpleDateFormat("_yyyy-MM-dd_HH.mm.ss").format(new Date());
		}
	};

}
