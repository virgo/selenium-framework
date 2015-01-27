package hu.virgo.selenium.framework;

import hu.virgo.selenium.framework.verify.ElementVerifier;
import hu.virgo.selenium.framework.verify.PageVerifier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Page {

	protected final WebDriver driver;

	public Page(WebDriver driver, By element) {
		this(driver, new ElementVerifier(element));
	}

	public Page(WebDriver driver, PageVerifier verifier) {
		this.driver = driver;
		verifier.verify(driver);
	}

}
