package hu.virgo.selenium.framework.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class Context {

	private static final String CONF_LOCATION = System.getProperty("baseconfigdir", "src/main/config");
	private String sutPropertiesFileName;
	private Properties properties;
	private Browser browser;
	private String buildNumber;
	public SystemUnderTest sut;

	public Context() {
		sutPropertiesFileName = System.getProperty("sut.properties", "selenium-default.properties");
		properties = openFile(sutPropertiesFileName);

		sut = initSut(properties);
		browser = initBrowser(properties);
		buildNumber = initBuildNumber();
	}

	public WebDriver getNewDriver() {
		return browser.getNewDriver();
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	private Properties openFile(String fileName) {
		Properties prop = new Properties();
		String fileUri = CONF_LOCATION + File.separator + fileName;

		try {
			prop.load(new FileInputStream(fileUri));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	private SystemUnderTest initSut(Properties properties) {
		String sutType = properties.getProperty("sut.type");
		sutType = sutType.trim().toUpperCase();
		SutType type = SutType.valueOf(sutType);

		String baseUrl = properties.getProperty("sut.baseUrl");
		String adminBaseUrl = properties.getProperty("sut.admin.baseUrl");

		return new SystemUnderTest(type, baseUrl, adminBaseUrl);
	}

	private Browser initBrowser(Properties properties) {
		String browserName = properties.getProperty("browser");
		String hubStr = properties.getProperty("browser.hub");
		boolean windowMaximize = Boolean.parseBoolean(properties.getProperty("browser.windows.maximize", "false"));
		int windowWidth = Integer.parseInt(properties.getProperty("browser.window.width", "1366"));
		int windowHeigth = Integer.parseInt(properties.getProperty("browser.window.heigth", "768"));

		Browser browser;

		if (hubStr != null) {
			try {
				URL hub = new URL(hubStr);
				browser = new Browser(browserName, hub);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(e);
			}
		} else {
			browser = new Browser(browserName);
		}

		browser.setWindowMaximize(windowMaximize);
		browser.setWindowDimensions(windowWidth, windowHeigth);

		return browser;
	}

	private String initBuildNumber() {
		DateFormat iso8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		String defaultValue = "Build (" + iso8601.format(new Date()) + ")";
		return System.getProperty("buildNumber", defaultValue);
	}
}
