package com.pullapart.tests;

import com.microsoft.playwright.*;
import com.pullapart.utils.ConfigReader;
import com.pullapart.utils.JsonReader;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.nio.file.Paths;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected BrowserContext context;

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
    }

    @BeforeMethod
    public void beforeMethod() {
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        page = context.newPage();
        page.navigate(ConfigReader.get("baseUrl"));

        context.tracing().start(new Tracing.StartOptions().setScreenshots(true)
                .setSnapshots(true).setSources(true));
    }

    @DataProvider(name = "dataProvider")
    public Object[][] passData(Method method) throws Exception {
        String className = this.getClass().getSimpleName();
        String filePath = "src/main/java/com/pullapart/dataprovider/" + className + ".json";
        return JsonReader.getData(filePath, method);
    }

    @AfterMethod
    public void afterMethod() {
        if (context != null) context.close();
    }

    @AfterClass
    public void afterClass() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}

