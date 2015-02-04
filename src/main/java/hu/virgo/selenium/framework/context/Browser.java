package hu.virgo.selenium.framework.context;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Browser {

	private BrowserType type;
	private URL hub;
	private int windowWidth = 1366;
	private int windowHeigth = 768;
	private boolean windowMaximize = false;
	private FirefoxProfile firefoxProfile;

	public Browser(String type, URL hub) {
		this.type = BrowserType.valueOf(type.toUpperCase());
		this.hub = hub;
	}

	public Browser(String type) {
		this.type = BrowserType.valueOf(type.toUpperCase());
	}

	public WebDriver getNewDriver() {
		WebDriver driver;
		if (hub != null) {
			driver = getRemoteDriver();
		} else {
			driver = getLocalDriver();
		}

		if (windowMaximize) {
			driver.manage().window().maximize();
		} else {
			driver.manage().window().setPosition(new Point(0, 0));
			driver.manage().window().setSize(new Dimension(windowWidth, windowHeigth));
		}

		return driver;
	}

	public void setWindowMaximize(boolean windowMaximize) {
		this.windowMaximize = windowMaximize;
	}

	public void setWindowDimensions(int windowWidth, int windowHeigth) {
		this.windowWidth = windowWidth;
		this.windowHeigth = windowHeigth;
	}

	public void setFirefoxProfile(String profileParam) {
		File profileDir = new File(profileParam);
		this.firefoxProfile = new FirefoxProfile(profileDir);
	}

	private WebDriver getRemoteDriver() {
		DesiredCapabilities capabilities;
		switch (this.type) {
		case FIREFOX:
			capabilities = DesiredCapabilities.firefox();
			if (this.firefoxProfile != null)
				capabilities.setCapability(FirefoxDriver.PROFILE, this.firefoxProfile);
			break;
		case IE:
			capabilities = DesiredCapabilities.internetExplorer();
			break;
		case CHROME:
			capabilities = DesiredCapabilities.chrome();
			break;
		default:
			capabilities = DesiredCapabilities.firefox();
			break;
		}

		RemoteWebDriver driver;
		driver = new RemoteWebDriver(hub, capabilities);
		driver.setFileDetector(new LocalFileDetector());

		return driver;
	}

	private WebDriver getLocalDriver() {
		switch (this.type) {
		case FIREFOX:
			if (this.firefoxProfile != null) {
				return new FirefoxDriver(this.firefoxProfile);
			} else {
				return new FirefoxDriver();
			}
		case IE:
			return new InternetExplorerDriver();
		case CHROME:
			return new ChromeDriver();
		default:
			return new FirefoxDriver();
		}
	}

	private enum BrowserType {
		FIREFOX, IE, CHROME;
	}

}
