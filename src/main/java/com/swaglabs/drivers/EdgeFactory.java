package com.swaglabs.drivers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Map;

public class EdgeFactory extends AbstractDriver implements WebDriverOptionsAbstract<EdgeOptions>{
    @Override
    public EdgeOptions getOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--inprivate"); // وضع التخفي في Edge
        options.addArguments("--incognito");       // يفتح وضع التخفي
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        Map<String, Object> edgeprefs = Map.of(
                "profile.default_content_setting_values.notifications", 2,
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill.profile_enabled" , false);

        options.setExperimentalOption("prefs", edgeprefs);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return options;
    }

    @Override
    public WebDriver startDriver(){
return new EdgeDriver(getOptions());
    }

}
