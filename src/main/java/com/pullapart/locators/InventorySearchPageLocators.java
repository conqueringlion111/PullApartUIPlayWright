package com.pullapart.locators;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import com.pullapart.pages.BasePage;
import com.microsoft.playwright.Page;

public class InventorySearchPageLocators extends BasePage {

    public InventorySearchPageLocators(Page page) {
        super(page);
    }

    public Locator getSelectionCombo(String selection) {
        return page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName(selection));
    }

    public Locator getSearchBox() {
        return page.getByRole(AriaRole.SEARCHBOX);
    }

    public Locator getSearchResult(String search) {
        return page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(search));
    }

    public Locator getSearchButton() {
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search"));
    }

    public Locator zeroSearchResult() {
        return page.getByText("Sorry, we couldn't");
    }

    public Locator exactMatchResult() {
        return page.getByText("Exact Match");
    }

    public static final String RESULT_TABLE_ROLE_XPATH = "//div[contains(@data-sortable-table, 'inventorySearchExact')]//div[@class='fl-table']";

}
