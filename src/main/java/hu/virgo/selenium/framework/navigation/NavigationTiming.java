package hu.virgo.selenium.framework.navigation;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * More about Navigation Timing
 * https://dvcs.w3.org/hg/webperf/raw-file/tip/specs/NavigationTiming/Overview.html
 */
public class NavigationTiming {

    private static final String SCRIPT = "var performance = window.performance || window.webkitPerformance || window.mozPerformance || window.msPerformance || {}; var timings = performance.timing || {}; return timings;";
    private JavascriptExecutor js;
    private Map<String, Object> timings;

    public NavigationTiming(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
        this.timings = new HashMap<>();
        this.timings = (Map<String, Object>) js.executeScript(SCRIPT);
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
        long start = Long.parseLong(String.valueOf(timings.get("navigationStart")));
        long end = Long.parseLong(String.valueOf(timings.get(eventName)));
        return end - start;
    }
}
