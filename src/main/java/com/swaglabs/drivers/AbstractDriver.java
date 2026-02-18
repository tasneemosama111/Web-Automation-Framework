package com.swaglabs.drivers;

import org.openqa.selenium.WebDriver;

public abstract class AbstractDriver {
    //This acts as a template/parent. It has a method called startDriver that ensures every factory follows the same structure
    public abstract WebDriver startDriver();
}
