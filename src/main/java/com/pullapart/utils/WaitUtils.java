package com.pullapart.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class WaitUtils {

    public static void waitForLocatorCountAtLeast(Page page, Locator locator, int minCount, int timeoutMs) {
        long deadline = System.currentTimeMillis() + timeoutMs;
        while (System.currentTimeMillis() < deadline) {
            if (locator.count() >= minCount) return;
            page.waitForTimeout(100);
        }
        throw new AssertionError("Timed out waiting for locator count >= " + minCount);
    }

    // convenient overload
    public static void waitForLocatorCountAtLeast(Page page, Locator locator, int minCount) {
        waitForLocatorCountAtLeast(page, locator, minCount, 10_000);
    }

}
