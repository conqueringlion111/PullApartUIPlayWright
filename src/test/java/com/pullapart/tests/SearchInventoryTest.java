package com.pullapart.tests;

import com.pullapart.pages.InventorySearchPage;
import org.testng.annotations.Test;


public class SearchInventoryTest extends BaseTest {

    @Test(groups = {"basicsearch"}, description = "test to search inventory")
    public void testSearchInventory() {
        String location = "Atlanta North";
        String make = "INFINITI";
        String model = "G35";

        InventorySearchPage searchPage = new InventorySearchPage(page);
        searchPage.searchInventory(location, make, model);
        searchPage.verifySearchSuccessful(make, model);
    }
}
