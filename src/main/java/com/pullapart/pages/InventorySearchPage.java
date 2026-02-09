package com.pullapart.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.pullapart.locators.InventorySearchPageLocators;
import org.testng.Assert;

public class InventorySearchPage extends BasePage {
    private final InventorySearchPageLocators loc;
    public InventorySearchPage(Page page) {
        super(page);
        this.loc = new InventorySearchPageLocators(page);
    }

    private void selectFromCombo(String comboName, String valueToType, String optionToClickExact) {
        Locator combo = loc.getSelectionCombo(comboName);
        assertThat(combo).isVisible();
        combo.click();

        Locator search = loc.getSearchBox();
        assertThat(search).isVisible();
        search.fill(valueToType);

        Locator option = loc.getSearchResult(optionToClickExact);
        assertThat(option).isVisible();
        option.click();
    }
    public void searchInventory(String location, String make, String model, String resultOptionText) {
        selectFromCombo("Select Location", location, location);
        selectFromCombo("Select Make", make, make);
        selectFromCombo("Select Model", model, resultOptionText);
        loc.getSearchButton().click();

    }

    public void clickSearchAndWait() {
        page.waitForResponse(
                r -> r.url().contains("/Model/OnYard") && r.status() == 200,
                () -> loc.getSearchButton().click()
        );
    }

    public void openNewOnYardSearch() {
        loc.newOnYardLink().click();
    }

    public void selectNewOnYardLocation(String location) {
        loc.dropdown().click();
        loc.listBox().waitFor();
        page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions()
                .setName(location).setExact(true)).click();
    }

    public void selectSevenDaysRadioButton() {
        loc.SevenDaysRadio().click();
    }

    public void selectNewOnYardSearchButton() {
        loc.newOnYardSearchButton().click();
    }

    public void verifyNewOnYardSearchSuccessful() {
        //wait for the results container to appear
        assertThat(loc.newOnYardResultsTable()).isVisible();
        //assert one or more role of new on yard is returned
        int returnedNewOnYardRoleCount = loc.newOnYardResultsTableRoles().count();
        Assert.assertTrue(returnedNewOnYardRoleCount > 0, "zero returned for new on yard search");
    }
}
