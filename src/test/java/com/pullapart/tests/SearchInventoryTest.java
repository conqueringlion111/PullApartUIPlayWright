package com.pullapart.tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Response;
import com.pullapart.locators.InventorySearchPageLocators;
import com.pullapart.pages.InventorySearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class SearchInventoryTest extends BaseTest {

    @Test(dataProvider = "dataProvider", groups = {"basicsearch"}, description = "test to search inventory")
    public void testSearchInventory(String location, String make, String model, String result) {
        InventorySearchPage searchPage = new InventorySearchPage(page);
        InventorySearchPageLocators loc = new InventorySearchPageLocators(page);

        searchPage.searchInventory(location, make, model, result);
        Locator sorry = loc.zeroSearchResult();
        Locator exact = loc.exactMatchResult();
        page.waitForCondition(() -> sorry.isVisible() || exact.isVisible());
        if (sorry.isVisible()) {
            Assert.assertTrue(sorry.isVisible());
            return;
        }
        // if sorry is not displayed search result table is returned, we can assert against the make and model
        Locator table = loc.resultTableRole();
        Locator row = table
                .filter(new Locator.FilterOptions().setHasText(make))
                .filter(new Locator.FilterOptions().setHasText(model));

        assertThat(row.first()).isVisible();
    }

    @Test(dataProvider = "dataProvider", groups = {"basicsearch"}, description = "test to search New On Yard 7 Days")
    public void testSearchNewOnYardSevenDays(String location) {
        InventorySearchPage searchPage = new InventorySearchPage(page);
        InventorySearchPageLocators loc = new InventorySearchPageLocators(page);
        searchPage.openNewOnYardSearch();
        searchPage.selectNewOnYardLocation(location);
        searchPage.selectSevenDaysRadioButton();

        page.waitForResponse(
                r -> r.url().contains("inventoryservice.pullapart.com")
                        && r.status() == 200
                // optional: tighten further if you know path contains new-on-yard endpoint
                // && r.url().contains("NewOnYard")
                ,
                () -> searchPage.selectNewOnYardSearchButton()
        );
        assertThat(loc.newOnYardResultsTable()).isVisible();
        //assert one or more role of new on yard is returned
        int returnedNewOnYardRoleCount = loc.newOnYardResultsTableRoles().count();
        Assert.assertTrue(returnedNewOnYardRoleCount > 0, "zero returned for new on yard search");
    }
}
