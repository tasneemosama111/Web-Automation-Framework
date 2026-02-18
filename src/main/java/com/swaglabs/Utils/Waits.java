package com.swaglabs.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Waits {


    private WebDriver driver;


    // Constructor private لمنع عمل instance
    public Waits(WebDriver driver) {
        this.driver=driver;
    }

    // الانتظار حتى يكون العنصر موجود في كود الصفحة (DOM)
    public  WebElement waitForElementPresent( By locator) {
        LogsUtil.info("Waiting for element to be present: ", locator.toString());
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> d.findElement(locator));
    }

    // الانتظار حتى يكون العنصر مرئي للعين (Visible)
    public  WebElement waitForElementVisible( By locator) {
        LogsUtil.info("Waiting for element to be visible: ", locator.toString());
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> {
                    WebElement element = waitForElementPresent(locator);
                    return element.isDisplayed() ? element : null;
                });
    }

    // الانتظار حتى يكون العنصر قابل للضغط عليه (Clickable)
    public  WebElement waitForElementClickable( By locator) {
        LogsUtil.info("Waiting for element to be clickable: ", locator.toString());
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> {
                    WebElement element = waitForElementVisible(locator);
                    return element.isEnabled() ? element : null;
                });
    }
}