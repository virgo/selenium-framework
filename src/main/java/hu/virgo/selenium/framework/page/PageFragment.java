package hu.virgo.selenium.framework.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PageFragment {

	protected WebDriver driver;
	private static final int DEFAULT_TIMEOUT = 30;

	public PageFragment(WebDriver driver) {
		this(driver, DEFAULT_TIMEOUT);
	}

	public PageFragment(WebDriver driver, int timeOutInSeconds) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, timeOutInSeconds), this);
	}
}
