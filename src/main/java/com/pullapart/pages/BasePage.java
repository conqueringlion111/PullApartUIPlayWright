package com.pullapart.pages;

import com.microsoft.playwright.Page;


public class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public String getTitle() {
        return page.title();
    }
}
