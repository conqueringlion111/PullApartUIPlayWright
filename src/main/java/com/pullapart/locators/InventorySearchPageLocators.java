package com.pullapart.locators;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.Page;

public class InventorySearchPageLocators {
    protected final Page page;
    public InventorySearchPageLocators(Page page) {
        this.page = page;
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
        return page.getByText("Sorry, we");
    }

    public Locator exactMatchResult() {
        return page.getByText("Exact Match");
    }



    public Locator newOnYardLink() {
        return page.locator("//div[contains(text(),'New On Yard')]");
    }

    public Locator dropdown() {
        return page.locator("//span[@class='select2-selection select2-selection--multiple'][@role='combobox']");
    }

    public Locator listBox() {
        return page.locator("ul[role='listbox'][id^='select2-location-'][id$='-results']:visible");
    }

    public Locator SevenDaysRadio() {
        return page.locator("input#sevenDays");
    }

    public Locator newOnYardSearchButton() {
        return page.locator("//div[@data-tab-content-id='newOnYard']//button[@class='button yellow']");
    }

    public Locator inventorySearchTable() {
        return page.locator("//div[@data-search-result= 'inventorySearch']");
    }

    public Locator resultTableRole() {
        return page.locator("//div[contains(@data-sortable-table, 'inventorySearchExact')]//div[@class='fl-table']");
    }

    public Locator newOnYardResultsTable() {
        return page.locator("//div[starts-with(@data-sortable-table, 'newOnYardSearchExact')]");
    }

    public Locator newOnYardResultsTableRoles() {
        return page.locator("//div[starts-with(@data-sortable-table, 'newOnYardSearchExact')]//div[@data-sortable-table-row='bodyLong']");
    }

}
