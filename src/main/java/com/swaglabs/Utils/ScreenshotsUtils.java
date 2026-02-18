package com.swaglabs.Utils;

import com.swaglabs.drivers.GUIDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static com.swaglabs.Utils.TimeStampUtils.getTimestamp;

public class ScreenshotsUtils {

    public static final String SCREENSHOTS_PATH ="test-outputs/screenshots";
    private ScreenshotsUtils(){
        super();

    }

    public static void takeScreenShots(WebDriver driver , String screenshotName){

        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(SCREENSHOTS_PATH + "/" + screenshotName + "_" + getTimestamp() + " .png");
            FileUtils.copyFile(screenshot , screenshotFile);
            AllureUtils.attachScreenshot(screenshotName , screenshotFile.getPath());

        } catch (Exception e){
            LogsUtil.error("failed to take screenshot " + e.getMessage());
        }



    }
}
