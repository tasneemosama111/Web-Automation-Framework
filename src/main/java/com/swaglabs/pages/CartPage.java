package com.swaglabs.pages;

import com.swaglabs.Utils.*;
import com.swaglabs.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    //variables
    private GUIDriver driver;
    //constructor
    public CartPage(GUIDriver driver){
        this.driver = driver;
    }


    //locators
    private final By productName = By.cssSelector(".inventory_item_name");
    private final By productPrice = By.cssSelector(".inventory_item_price");
    private final By checkOutButton = By.id("checkout");


    //actions
    @Step("getting product name")
    private String getProductName(){

        return driver.element().getText(productName);
    }

    @Step("getting product price")
    private String getProductprice(){
        return driver.element().getText(productPrice);
        //Validations.assertEqual( productPrice , "29.99", "price is not correct");
    }

    @Step("clicking on checkout button")
    public  InformationPage clickOnCHekOutButton(){
        driver.element().clickOnElement(checkOutButton);
        return new InformationPage(driver);
    }


    //validations

    @Step("validate that the product details is correct")
    public CartPage assertProductDetais(String productName , String productPrice){
        // FYI: We call getProductName() to "fetch" the text from the browser , We store it in a String variable so we have a fixed value to compare later.
        String actualProductName = getProductName();
        String actualProductPrice = getProductprice();

        CustomSoftAssertion.softAssertion.assertEquals(actualProductName,productName , "product name is mismatch");

        CustomSoftAssertion.softAssertion.assertEquals(actualProductPrice,productPrice , "product price is mismatch");


        return this;
    }





}
