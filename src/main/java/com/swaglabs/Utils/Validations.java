package com.swaglabs.Utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validations {
    private WebDriver driver;
    private BrowserActions browserActions;
     public Validations(WebDriver driver){
         this.driver=driver;
         browserActions = new BrowserActions(driver);
     }

    @Step("Asserting that [{actual}] is equal to [{expected}]")
    public  void assertEqual(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    @Step("Asserting that [{actual}] is NOT equal to [{expected}]")
    public  void assertNotEqual(String actual, String expected, String message) {
        Assert.assertNotEquals(actual, expected, message); // صلحنا دي
    }

    @Step("Asserting that condition is TRUE")
    public  void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    @Step("Asserting that condition is FALSE")
    public  void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message); // صلحنا دي وغيرنا اسمها لـ False عشان تبقى أوضح
    }

    @Step("Asserting the page URL is: {expected}")
    public  void assertPageUrl(String expected) {
        Assert.assertEquals(browserActions.getCurrentURL(), expected);
    }

    @Step("Asserting the page title is: {expected}")
    public  void assertTitle( String expected) {
        Assert.assertEquals(browserActions.getPageTitle(), expected);
    }
}