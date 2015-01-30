package hu.virgo.selenium.framework.page;

import hu.virgo.selenium.framework.Utils;
import hu.virgo.selenium.framework.page.verify.ElementVerifier;
import hu.virgo.selenium.framework.page.verify.PageVerifier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class Page {

	protected final WebDriver driver;
	protected final Utils utils;
	private final PageVerifier verifier;

	public Page(WebDriver driver, By element) {
		this(driver, new ElementVerifier(element));
	}

	public Page(WebDriver driver, PageVerifier verifier) {
		this.driver = driver;
		this.utils = new Utils(driver);
		this.verifier = verifier;
	}

	public void verify() {
		this.verifier.verify(driver);
	}
}
