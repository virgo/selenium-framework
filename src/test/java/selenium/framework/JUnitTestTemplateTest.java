package selenium.framework;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import hu.virgo.selenium.framework.JUnitTestTemplate;

import org.junit.Test;

public class JUnitTestTemplateTest extends JUnitTestTemplate {

	@Test
	public void testTemplateInitlizesTheDriver() {
		assertThat(driver, not(nullValue()));
	}
}
