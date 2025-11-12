package com.pullapart.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.pullapart.locators.InventorySearchPageLocators;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static com.pullapart.locators.InventorySearchPageLocators.selectLocation;
import static com.pullapart.locators.InventorySearchPageLocators.selectMake;

public class InventorySearchPage extends BasePage {

    public InventorySearchPage(Page page) {
        super(page);
    }

    public void searchInventory(String location, String make, String model) {
        page.locator(InventorySearchPageLocators.selectLocation).click();
        page.locator("//input[@class='select2-search__field']").type(location);
        page.locator("//li[contains(text(),'" + location + "')]").click();

        page.locator(InventorySearchPageLocators.selectMake).click();
        page.locator("//input[@class='select2-search__field']").type(make);
        page.locator("//li[contains(text(),'" + make + "')]").click();

        page.locator(InventorySearchPageLocators.selectModel).click();
        page.locator("//input[@class='select2-search__field']").type(model);
        page.locator("//li[contains(text(),'" + model + "')]").click();

        page.locator(InventorySearchPageLocators.searchButton).click();
        page.waitForLoadState();
    }

    public void verifySearchSuccessful(String make, String model) {
        //see if search actually returned results
        page.locator("//div[contains(@data-search-result, 'inventorySearch')]").isVisible();
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
                System.out.println("Assertions passed!");
            }
        } else {
            System.out.println("at else statement ");
            page.locator("#InventorySearchPage1").isVisible();
        }
    }
}
