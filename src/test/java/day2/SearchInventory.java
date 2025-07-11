package day2;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.pullapart.pages.InventorySearchPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SearchInventory {

    public static void main(String[] args) {

        String location = "Atlanta North";
        String make = "INFINITI";
        String model = "G35";

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false)
                .setArgs(java.util.Arrays.asList("--start-maximized")));
        Page page = browser.newPage();
        page.navigate("https://www.pullapart.com/");
        page.locator("//span[contains(text(),'Select Location')]").click();
        page.locator("//input[@class='select2-search__field']").type(location);
        page.locator("//li[contains(text(),'" + location + "')]").click();

        page.locator("//span[contains(text(),'Select Make')]").click();
        page.locator("//input[@class='select2-search__field']").type(make);
        page.locator("//li[contains(text(),'" + make + "')]").click();

        page.locator("//span[contains(text(),'Select Model')]").click();
        page.locator("//input[@class='select2-search__field']").type(model);
        page.locator("//li[contains(text(),'" + model + "')]").click();

        //click on search button
        page.locator("//div[@class='basic-search']//button[contains(text(),'Search')]").click();

        page.waitForTimeout(3000);


        //verify search successful
        InventorySearchPage inventory = new InventorySearchPage();
        inventory.verifySearchSuccessful(page, make, model);


        page.close();
        browser.close();
        playwright.close();

    }
}
