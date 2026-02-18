package com.swaglabs.pages;

import com.swaglabs.Utils.CustomSoftAssertion;
import com.swaglabs.Utils.ElementActions;
import com.swaglabs.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InformationPage {
    //variables
    private GUIDriver driver;

    //constructor
    public InformationPage(GUIDriver driver){
        this.driver=driver;
    }

// locators

    // 1. private: Only this Page class can see it.
    // 2. final: This locator cannot be reassigned to another ID later.
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");

    //actions

    @Step("filling information page : First Name {0} , Last Name {1} , Postal Code {2}")
    public InformationPage fillInformationForm(String FirstName, String LastName , String PostalCode){
        driver.element().type( firstName , FirstName);
        driver.element().type(lastName , LastName);
        driver.element().type(postalCode ,PostalCode);

        return this;
    }

@Step("Clicking on continue button")
    public OverviewPage clickOnContinue(){
    driver.element().clickOnElement( continueButton );
        return new OverviewPage(driver);
    }


    //validations

    @Step("assert information page")
    public InformationPage validateInfoPage(String FirstName , String LastName , String PostalCode){
        CustomSoftAssertion.softAssertion.assertEquals(driver.element().getTextFromInput(firstName),FirstName);
        CustomSoftAssertion.softAssertion.assertEquals(driver.element().getTextFromInput(lastName),LastName);
        CustomSoftAssertion.softAssertion.assertEquals(driver.element().getTextFromInput( postalCode),PostalCode);



        return this;
    }









}
