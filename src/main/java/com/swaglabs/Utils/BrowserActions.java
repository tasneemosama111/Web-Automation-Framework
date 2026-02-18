package com.swaglabs.Utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    //we're using bot pattern from now on in this class
    private WebDriver driver;

    public BrowserActions(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Navigate to URL: {URL}")
    public void navigateToURL( String URL) {
        driver.get(URL);
        LogsUtil.info("Navigated to URL: ", URL);
    }

    @Step("Getting current URL")
    public String getCurrentURL() {
        String url = driver.getCurrentUrl();
        LogsUtil.info("Current URL is: ", url);
        return url;
    }

    @Step("Getting page title")
    public  String getPageTitle() {
        String title = driver.getTitle();
        LogsUtil.info("Page title is: ", title);
        return title;
    }

    @Step("Refreshing the page")
    public  void refreshPage() {
        driver.navigate().refresh();
        LogsUtil.info("Page refreshed");
    }

    @Step("Closing the browser")
    public void closeBrowser() {
        LogsUtil.info("Closing the browser");
        driver.quit();
    }
}