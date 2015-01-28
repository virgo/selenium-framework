package selenium.framework;

import hu.virgo.selenium.framework.JUnitTestTemplate;
import hu.virgo.selenium.framework.page.Page;
import hu.virgo.selenium.framework.page.verify.ElementVerifier;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageTest extends JUnitTestTemplate {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void ElementVerifierThrowsTheRightException() {
		driver.get("http://junit.org/");

		ElementVerifier verifier = new ElementVerifier(By.cssSelector("noSuchSelector"), 2);
		exception.expect(NoSuchElementException.class);
		verifier.verify(driver);
	}

	@Test
	public void pageInitThrowsExceptionWhenPageVerificationFails() {
		driver.get("http://junit.org/");

		exception.expect(RuntimeException.class);
		PageFactory.initElements(driver, ProblematicPage.class);
	}

	public static class ProblematicPage extends Page {
		public ProblematicPage(WebDriver driver) {
			super(driver, new ElementVerifier(By.cssSelector("noSuchSelector"), 2));
		}
	}
}
