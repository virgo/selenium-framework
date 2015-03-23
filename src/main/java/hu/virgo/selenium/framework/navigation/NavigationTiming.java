package hu.virgo.selenium.framework.navigation;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Map;

/**
 * More about Navigation Timing
 * https://dvcs.w3.org/hg/webperf/raw-file/tip/specs/NavigationTiming/Overview.html
 */
public class NavigationTiming {

    private static final String SCRIPT = "var performance = window.performance || window.webkitPerformance || window.mozPerformance || window.msPerformance || {}; var timings = performance.timing || {}; return timings;";
    private final Map<String, Object> timings;

    public NavigationTiming(WebDriver driver) {
        final JavascriptExecutor js = (JavascriptExecutor) driver;

        @SuppressWarnings("unchecked")
        final Map<String, Object> result =  (Map<String, Object>) js.executeScript(SCRIPT);

        this.timings = result;
    }

    public long responseEndedInMillis() {
        return millisBetweenNavigationStartAndEvent("responseEnd");
    }

    public long domCompletedInMillis() {
        return millisBetweenNavigationStartAndEvent("domComplete");
    }

    public long completePageLoadTimeInMillis() {
        return millisBetweenNavigationStartAndEvent("loadEventEnd");
    }

    private long millisBetweenNavigationStartAndEvent(String eventName) {
        long start = ((Number)timings.get("navigationStart")).longValue();
        long end = ((Number)timings.get(eventName)).longValue();
        return end - start;
    }
}
