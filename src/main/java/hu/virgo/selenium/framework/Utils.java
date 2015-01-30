package hu.virgo.selenium.framework;

import hu.virgo.selenium.framework.page.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Utils {

	private final WebDriver driver;

	public Utils(WebDriver driver) {
		this.driver = driver;
	}

	public <P extends Page> P initPage(Class<P> pageClass) {
		P page = PageFactory.initElements(driver, pageClass);
		page.verify();
		return page;
	}
}
