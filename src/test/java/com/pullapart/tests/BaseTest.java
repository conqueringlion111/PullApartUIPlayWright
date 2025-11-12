package com.pullapart.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.pullapart.utils.ConfigReader;
import org.testng.annotations.*;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    @BeforeClass
    public void setUp() {
        // Load properties
        ConfigReader.loadProperties();
        String browserName = ConfigReader.get("browser").toLowerCase();
        String baseUrl = ConfigReader.get("baseUrl");
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));

        playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(headless)
                .setArgs(java.util.Arrays.asList("--start-maximized"));

        switch (browserName) {
            case "firefox":
                browser = playwright.firefox().launch(options);
                break;
            case "edge":
                browser = playwright.chromium().launch(options.setChannel("msedge"));
                break;
            case "chrome":
            default:
                browser = playwright.chromium().launch(options.setChannel("chrome"));
                break;
        }

        page = browser.newPage();
        page.navigate(baseUrl);
    }

    @AfterClass
    public void tearDown() {
        if (page != null) page.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}

