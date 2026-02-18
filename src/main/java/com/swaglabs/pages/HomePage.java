package com.swaglabs.pages;

import com.swaglabs.Utils.*;
import com.swaglabs.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class HomePage {
    //code
    //variables
    private GUIDriver driver;
    //constructor
    public HomePage(GUIDriver driver){
        this.driver = driver;
    }

     //locators
   private final By cartIcon = By.cssSelector("[data-test=\"shopping-cart-link\"]");

    //actions
    @Step ("navigate to home page")
    public HomePage navigateToHomePage(){
        // FYI: We bring the URL from "environment.properties" instead of writing it here
        // This class was created to store base URLs and essential configurations This makes it easy to change the link in one place without touching the code
        driver.browser().navigateToURL( PropertiesUtils.getPropertyValue("homeURL"));

        return this;
    }

    @Step("add specific product to cart")
    public HomePage addSpecificProductToCart(String productName){
        LogsUtil.info("adding" + productName + "to cart");
        By addToCartButton =RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='" + productName + "']"));
        driver.element().clickOnElement(addToCartButton);

        return this;
    }

    @Step("click on cart icon")
    public CartPage clickOnCartIcon(){
        LogsUtil.info("clicking on the cart icon");
        driver.element().clickOnElement(cartIcon);
        return new CartPage(driver);
    }





    //validations
    @Step("validate that the product added to the cart")

public HomePage assertProductAddedToCart(String productName){
    By addToCartButton =RelativeLocator.with(By.tagName("button")).below(By.xpath("//div[.='" + productName + "']"));
    String actualValue = driver.element().getText( addToCartButton);
    LogsUtil.info("actual value : " + actualValue);
    driver.validate().assertEqual(actualValue , "Remove" , "product not added to cart");
    LogsUtil.info(productName + " added to cart successfully");
    return this;
}








}
