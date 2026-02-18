package com.swaglabs.drivers;
import com.swaglabs.Utils.BrowserActions;
import com.swaglabs.Utils.ElementActions;
import com.swaglabs.Utils.LogsUtil;
import com.swaglabs.Utils.Validations;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class GUIDriver {
    //While the Factory Classes (ChromeFactory, FirefoxFactory) are responsible for creating the driver, the GUIDriver is responsible for managing and selecting them.
//It acts as the bridge between your Tests and your Factories
    // ely h3mel intialize ll drive fl class da bdal ma ykon mawgod fl before method
    //hn3mel thread local 3shan n3mel parlell execution 3shan admn eny a3mel only one instance intialized
    //so ThreadLocal is used to store a separate WebDriver instance for each test thread to enable safe parallel execution
    //code
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public GUIDriver(String browserName){
        WebDriver driver = getDriver(browserName).startDriver();
        setDriver(driver);

    }


    public static WebDriver getInstance(){
        return driverThreadLocal.get();
    }

    //and get() to retrieve the same driver during test execution without interacting with other threads
    public static AbstractDriver getDriver(String browserName){
        //code
       return switch (browserName){
            case "chrome" -> new ChromeFactory();
            case "firefox" ->new FireFoxFactory();
            case "edge" ->new EdgeFactory();
            default -> throw new IllegalArgumentException();
        };


    }

    //ThreadLocal uses set() to assign a driver to a specific thread
    public static void setDriver(WebDriver driver){
        driverThreadLocal.set(driver);
    }








    public WebDriver get(){
        if (driverThreadLocal.get() == null){
            LogsUtil.error("Driver is null");
            fail("Driver is null");
            return null;
        }
        return driverThreadLocal.get();
    }





    public ElementActions element(){
        return new ElementActions(get());
    }
    public BrowserActions browser(){
        return new BrowserActions(get());
    }

    public Validations validate(){
        return new Validations(get());
    }



}





