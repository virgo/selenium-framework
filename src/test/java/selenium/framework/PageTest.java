package selenium.framework;

import hu.virgo.selenium.framework.JUnitTestTemplate;
import hu.virgo.selenium.framework.page.Page;
import hu.virgo.selenium.framework.page.verify.ElementVerifier;
import hu.virgo.selenium.framework.page.verify.PageVerificationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageTest extends JUnitTestTemplate {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void elementVerifierThrowsTheRightException() {
		driver.get("http://junit.org/");

		ElementVerifier verifier = new ElementVerifier(By.cssSelector("noSuchSelector"), 2);
		exception.expect(PageVerificationException.class);
		verifier.verify(driver);
	}

	@Test
	public void pageInitThrowsExceptionWhenPageVerificationFails() {
		driver.get("http://junit.org/");

		exception.expect(PageVerificationException.class);
		utils.initPage(ProblematicPage.class);
	}

	public static class ProblematicPage extends Page {
		public ProblematicPage(WebDriver driver) {
			super(driver, new ElementVerifier(By.cssSelector("noSuchSelector"), 2));
		}
	}
}
