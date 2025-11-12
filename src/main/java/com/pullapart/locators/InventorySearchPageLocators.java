package com.pullapart.locators;

import com.pullapart.pages.BasePage;
import com.microsoft.playwright.Page;

public class InventorySearchPageLocators extends BasePage {

    public InventorySearchPageLocators(Page page) {
        super(page);
    }

    // Locators
    public static final String selectLocation = "//span[contains(text(),'Select Location')]";
    public static final String selectMake = "//span[contains(text(),'Select Make')]";
    public static final String selectModel = "//span[contains(text(),'Select Model')]";
    public static final String searchButton = "//div[@class='basic-search']//button[contains(text(),'Search')]";

}
