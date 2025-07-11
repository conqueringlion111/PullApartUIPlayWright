package com.pullapart.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InventorySearchPage {

    public void verifySearchSuccessful(Page page, String make, String model) {
        //see if search actually returned results
        int elementCount = page.locator("//div[@data-sortable-table='inventorySearchExact4']").count();
        if (elementCount > 0) {
            //get total result count
            int totalResult = page.locator("//div[@data-sortable-table='inventorySearchExact4']//div[@data-sortable-table-row='bodyLong']").count();
            for (int i = 1; i <= totalResult; ++i) {
                String index = String.valueOf(i);
                Locator makeSearch = page.locator("//div[@data-sortable-table='inventorySearchExact4']//div[@data-sortable-table-row='bodyLong'][" + index + "]//div[contains(text(),'" + make + "')]");
                Locator modelSearch = page.locator("//div[@data-sortable-table='inventorySearchExact4']//div[@data-sortable-table-row='bodyLong'][" + index + "]//div[contains(text(),'" + model + "')]");
                assertThat(makeSearch).isVisible();
                assertThat(modelSearch).isVisible();
            }
        } else {
            page.locator("#InventorySearchPage1").isVisible();
        }
    }
}
