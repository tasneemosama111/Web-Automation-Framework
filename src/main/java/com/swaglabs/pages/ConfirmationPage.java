package com.swaglabs.pages;

import com.swaglabs.Utils.CustomSoftAssertion;
import com.swaglabs.Utils.ElementActions;
import com.swaglabs.Utils.Validations;
import com.swaglabs.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {


    // variables
    private GUIDriver driver ;


    // constructor
    public ConfirmationPage(GUIDriver driver){
        this.driver = driver;

    }

    //locators

    private final By confirmationMessage = By.cssSelector(".complete-header");




//actions
  public ConfirmationPage getConfirmationMessage(){
      driver.element().getText(confirmationMessage);

      return this;
   }


//validattion
    //Rule: In validation methods, the parameter is the Expected result (the Truth)
    //Rule: Any Validation Method must take the Expected Value as a String parameter. This is because the Assert method needs two Strings to compare, not a String and a Locator.

    @Step("assert confirmation message : {0}")
    public ConfirmationPage validateConfirmationMessage(String expectedMessage)  { //expected
        driver.validate().assertEqual                                                   //actual dyman bngebo gwa like get text, get attribute,...
                (driver.element().getText(confirmationMessage), expectedMessage , "confirmation message mismatch" ); //expected result

      return this;
    }




}
