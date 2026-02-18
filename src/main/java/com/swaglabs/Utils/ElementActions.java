package com.swaglabs.Utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {

    private WebDriver driver;
    //waits bot
    private Waits waits;
    public ElementActions(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
    }

    @Step("Sending data: {data} to the element: {locator}")
    public void type (By locator, String data) {
        waits.waitForElementVisible( locator);
        scrollRoElement(locator);

        WebElement element = findElement(locator);
        element.clear(); // السطر ده مهم جداً عشان ميكتبش فوق داتا قديمة
        element.sendKeys(data);

        LogsUtil.info("Data entered: '", data, "' in the field: ", locator.toString());
    }

    @Step("Clicking on element: {locator}")
    public  void clickOnElement( By locator) {
       waits.waitForElementVisible( locator);
        scrollRoElement( locator);

        findElement(locator).click();
        LogsUtil.info("Clicked on the element: ", locator.toString());
    }

    @Step("Get text from element: {locator}")
    public  String getText( By locator) {
        waits.waitForElementVisible( locator);
        scrollRoElement( locator);
        String text = findElement(locator).getText();
        LogsUtil.info("Getting text from: ", locator.toString(), " | Text: ", findElement(locator).getText());
        return findElement(locator).getText();
    }

    public  WebElement findElement( By locator) {
        // خليناها public عشان Scrolling يشوفها
        LogsUtil.info("Finding element: ", locator.toString());
        return driver.findElement(locator);
    }



    // we use this getDomAttribute instead of gettext() cause gettext() returns--->The visible text located between the HTML tags while
    // getDomAttribute() The content stored inside the value attribute like input field ely taht so that why we use getdomattribute 3shan it Returns the actual text typed by the user.
    public  String getTextFromInput( By locator){
        // 1. Ensures the field is fully loaded and ready to be read.
        waits.waitForElementVisible( locator);
        // 2. Brings the field into view to avoid issues with hidden elements.
        scrollRoElement(locator);
        // 3. LOGS: Records the locator and the actual value found for Allure reporting.
        LogsUtil.info("Getting text from the input field: " // 1. Action Description: Tells you what the code is doing.
                , locator.toString() // 2. Locator ID: Shows which field is being read and change it to string to be able to see which locator is it
                ," | Text: "    // 3. Separator: Just for making the report readable and organized.
                , findElement(locator).getDomAttribute("value")); // 4. Real-time Data: Pulls the actual text typed by the user.
        // 4. RETURN: Sends the actual typed text back to the Assertion.
        return findElement(locator).getDomAttribute("value");

    }

    //scroll to element
    @Step("Scrolling to element: {locator}")
    public  void scrollRoElement(By locator) {
        LogsUtil.info("Scrolling to element: ", locator.toString());

        // بنادي findElement من كلاس ElementActions عشان أجيب العنصر
        WebElement element = findElement( locator);

        // تنفيذ الـ Scroll باستخدام JavaScript
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }





}