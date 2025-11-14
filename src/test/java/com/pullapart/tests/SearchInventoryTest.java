package com.pullapart.tests;

import com.pullapart.pages.InventorySearchPage;
import org.testng.annotations.Test;


public class SearchInventoryTest extends BaseTest {

    @Test(dataProvider = "dataProvider", groups = {"basicsearch"}, description = "test to search inventory")
    public void testSearchInventory(String location, String make, String model) {

        InventorySearchPage searchPage = new InventorySearchPage(page);
        searchPage.searchInventory(location, make, model);
        searchPage.verifySearchSuccessful(make, model);
    }
}
