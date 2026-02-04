package com.pullapart.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.AriaRole;
import com.pullapart.locators.InventorySearchPageLocators;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InventorySearchPage extends BasePage {

    public InventorySearchPage(Page page) {
        super(page);
    }

    public void searchInventory(String location, String make, String model, String result) {
        //Find the Select Location combo box and click
        InventorySearchPageLocators isp = new InventorySearchPageLocators(page);
        Locator comboSelect = isp.getSelectionCombo("Select Location");
        comboSelect.isVisible();
        comboSelect.click();
        //Find the input field and fill location
        Locator searchBox = isp.getSearchBox();
        searchBox.fill(location);
        //Click on the searched option
        Locator selection = isp.getSearchResult(location);
        selection.click();

        //Select Make
        Locator selectMake = isp.getSelectionCombo("Select Make");
        selectMake.click();
        Locator makeSearchBox = isp.getSearchBox();
        makeSearchBox.fill(make);
        Locator makeSelection = isp.getSearchResult(make);
        makeSelection.click();

        //Select Model
        Locator selectModel = isp.getSelectionCombo("Select Model");
        selectModel.click();
        Locator modelSearchBox = isp.getSearchBox();
        modelSearchBox.fill(model);
        Locator modelSelection = isp.getSearchResult(result);
        modelSelection.click();

        //Click on search button
        Locator button = isp.getSearchButton();
        button.click();

    }

    public void verifySearchSuccessful(String make, String model) {
        InventorySearchPageLocators searchPage = new InventorySearchPageLocators(page);
        Response resp = page.waitForResponse(r -> r.url().startsWith("https://inventoryservice.pullapart.com/Model/OnYard")
                        && r.status() == 200, searchPage::zeroSearchResult);
        //see if search returned zero results
        Locator zero = searchPage.zeroSearchResult();
        if (zero.isVisible()) {
            Assert.assertTrue(zero.isVisible());
        } else {
            Assert.assertTrue(searchPage.exactMatchResult().isVisible());
            Locator rows = page.locator(InventorySearchPageLocators.RESULT_TABLE_ROLE_XPATH);
            Locator makeModelRow = rows.filter(new Locator.FilterOptions().setHasText(make))
                    .filter(new Locator.FilterOptions().setHasText(model));
            assertThat(makeModelRow.first()).isVisible();
        }
    }
}
