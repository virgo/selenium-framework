package hu.virgo.selenium.framework.navigation;

import hu.virgo.selenium.framework.JUnitTestTemplate;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class NavigationTimingTest extends JUnitTestTemplate {

    private NavigationTiming navTiming;

    @Before
    public void openAPage() {
        driver.get(context.sut.getBaseUrl());
        navTiming = new NavigationTiming(driver);
    }

    @Test
    public void testResponseEndedInMillis() throws Exception {
        assertThat(navTiming.responseEndedInMillis(), greaterThan(0L));
    }

    @Test
    public void testDomCompletedInMillis() throws Exception {
        assertThat(navTiming.domCompletedInMillis(), greaterThan(100L));
    }

    @Test
    public void testCompletePageLoadTimeInMillis1() throws Exception {
        assertThat(navTiming.completePageLoadTimeInMillis(), greaterThan(100L));
    }

    @Test
    public void relativeValues() {
        long responseEnded = navTiming.responseEndedInMillis();
        long domCompletes = navTiming.domCompletedInMillis();
        long completed = navTiming.completePageLoadTimeInMillis();

        assertThat(completed, greaterThan(responseEnded));
        assertThat(completed, greaterThan(domCompletes));
    }
}
