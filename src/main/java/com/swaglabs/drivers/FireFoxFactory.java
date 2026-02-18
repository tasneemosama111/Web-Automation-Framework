package com.swaglabs.drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FireFoxFactory extends AbstractDriver implements WebDriverOptionsAbstract<FirefoxOptions> {
    @Override
    public FirefoxOptions getOptions() {


        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");       // يفتح وضع التخفي
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        // تعطيل الإشعارات في فايرفوكس
        options.addPreference("dom.webnotifications.enabled", false);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setAcceptInsecureCerts(true);
        return options;

    }


    @Override
    public WebDriver startDriver(){
        return new FirefoxDriver(getOptions());
    }


}

