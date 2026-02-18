package com.swaglabs.pages;

import com.swaglabs.Utils.ElementActions;
import com.swaglabs.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {

    // variables
   private GUIDriver driver ;
   // constructor
    public OverviewPage(GUIDriver driver){
        this.driver = driver;

    }

   //locators
   // 1. private: Only this Page class can see it.
// 2. final: This locator cannot be reassigned to another ID later.
    private final By finishaButton = By.id("finish");



//actions

    @Step("clicking on finish button")
    public ConfirmationPage clickOnFinish(){
        driver.element().clickOnElement( finishaButton);
return new ConfirmationPage(driver);
}




}









