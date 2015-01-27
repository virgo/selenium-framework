package hu.virgo.selenium.framework.context;

import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Browser {

	private BrowserType type;
	private URL hub;

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

		return driver;
	}

	private WebDriver getRemoteDriver() {
		Capabilities capabilities;
		switch (this.type) {
		case FIREFOX:
			capabilities = DesiredCapabilities.firefox();
			break;
		case IE:
			capabilities = DesiredCapabilities.internetExplorer();
		case CHROME:
			capabilities = DesiredCapabilities.chrome();
		default:
			capabilities = DesiredCapabilities.firefox();
		}

		RemoteWebDriver driver;
		driver = new RemoteWebDriver(hub, capabilities);
		driver.setFileDetector(new LocalFileDetector());

		return driver;
	}

	private WebDriver getLocalDriver() {
		switch (this.type) {
		case FIREFOX:
			return new FirefoxDriver();
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
