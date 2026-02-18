package com.swaglabs.pages;

import com.swaglabs.Utils.BrowserActions;
import com.swaglabs.Utils.CustomSoftAssertion;
import com.swaglabs.Utils.ElementActions;
import com.swaglabs.Utils.Validations;
import com.swaglabs.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.bidi.log.Log;

import static com.swaglabs.Utils.PropertiesUtils.getPropertyValue;

public class LoginPage {
    private final GUIDriver driver;

    // Locators
    // private hides the locator from other classes, and final ensures it cannot be reassigned after initialization
   // final ensures the locator reference never changes after being defined, making the page object more stable and predictable.
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By LoginBtn = By.id("login-button");
    private final By errorMssg = By.cssSelector("[data-test=\"error\"]");

    // Constructor
    // FYI: Receives the active browser instance to maintain the same driver across pages.
    // It ensures that Selenium continues working in the same browser window instead of opening a new one.
    public LoginPage(GUIDriver driver) {
        this.driver = driver;
    }

    @Step("navigating to login page")
    public void navigateToLoginPage() {
        driver.browser().navigateToURL("https://www.saucedemo.com/");
        //BrowserActions.navigateToURL(driver, "https://www.saucedemo.com/");
        //return this;
    }

    // Actions
    @Step("entering username")
    public LoginPage enterUserName(String Username) {
        driver.element().type( username, Username);
        return this;
    }

    @Step("entering password")
    public LoginPage enterPassword(String Password) {
        driver.element().type(password, Password);
        return this;
    }

    @Step("clicking on login button")
    public LoginPage clickLoginButton() {
        driver.element().clickOnElement(LoginBtn);
        return this;
    }

    // Getters
    @Step("get error message")
    public String getErroMssg() {

        return driver.element().getText(errorMssg);
    }








    //validations
    @Step("Assert login page URL")
    public LoginPage assertLoginPageURL(){
      //  driver.validation().assertEqual(get);
        CustomSoftAssertion.softAssertion.assertEquals(driver.browser().getCurrentURL() , getPropertyValue("home"));

        return this;
    }

    @Step("Assert login page title")

    public LoginPage assertLoginPageTitle(){
        CustomSoftAssertion.softAssertion.assertEquals(driver.browser().getPageTitle(), getPropertyValue("appTitle"));
        return this;
    }


    @Step("Assert Soft successful login")
    public LoginPage assertSuccessfulLoginSoft(){
       assertLoginPageURL().assertLoginPageTitle();
        return this;
    }



    @Step("Assert successful login")
    public HomePage assertSuccessfulLogin(){
        driver.validate().assertPageUrl( getPropertyValue("homeURL"));
    return new HomePage(driver);
    }



    @Step("Assert unsuccessful login")
    public HomePage assertUnsuccessfulLogin(){
        driver.validate().assertEqual(getErroMssg() , getPropertyValue("errorMSG"), "Error unsuccessful login");
        return new HomePage(driver);
    }






}