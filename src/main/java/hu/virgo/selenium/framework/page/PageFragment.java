package hu.virgo.selenium.framework.page;

import hu.virgo.selenium.framework.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PageFragment {

	protected WebDriver driver;
	protected final Utils utils;
	private static final int DEFAULT_TIMEOUT = 30;

	public PageFragment(WebDriver driver) {
		this(driver, DEFAULT_TIMEOUT);
	}

	public PageFragment(WebDriver driver, int timeOutInSeconds) {
		this.driver = driver;
		this.utils = new Utils(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, timeOutInSeconds), this);
	}
}
