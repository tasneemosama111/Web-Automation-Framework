package com.swaglabs.Utils;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.Files.newInputStream;

public class AllureUtils {
 public static final String ALLURE_RESULTS_PATH = "test-outputs/allure-results" ;
    static  String REPORT_PATH ="test-outputs/allure-report";
    static String USER_HOME = System.getProperty("user.home");
    static   String ALLURE_PATH =
            USER_HOME + File.separator +
            "scoop" + File.separator +
            "apps" + File.separator +
            "allure" + File.separator +
            "2.35.1" + File.separator +
            "bin" + File.separator +
            "allure" ;


    private AllureUtils(){
        super();
    }


    public static void generateAllureReport()  {
        if (PropertiesUtils.getPropertyValue("os.name").toLowerCase().contains("win")){
            //we're doing  If-Condition to detect which Operating System type  to execute the correct terminal commands.

            String WIN = ALLURE_PATH + ".bat";

            TerminalUtils.executeTerminalCommand(WIN , "generate" , ALLURE_RESULTS_PATH, "-o" , REPORT_PATH , "clean" , "--single-file");

            LogsUtil.info("allure report generated successfully on windows");
        } else {
            TerminalUtils.executeTerminalCommand(ALLURE_PATH , "generate" + ALLURE_RESULTS_PATH, "-o" , REPORT_PATH , "clean" , "--single-file");

            LogsUtil.info("allure report generated successfully on " , PropertiesUtils.getPropertyValue("os.name"));
        }
    }



    public static String renameReport(){



        File newName = new File("Report_" + TimeStampUtils.getTimestamp() + ".html") ;
        File oldName = new File(REPORT_PATH + File.separator + "index.html" );
        FilesUtils.renameFile(oldName, newName);
        return newName.getName();
    }


    public static void openReport(String fileName){

        //allure serve //path
      //String reportPath = REPORT_PATH;
        String reportPath = REPORT_PATH + File.separator + fileName;
      if (PropertiesUtils.getPropertyValue("openAllureAutomatically").equalsIgnoreCase("true")) {

          if (PropertiesUtils.getPropertyValue("os.name").toLowerCase().contains("win")) {

              TerminalUtils.executeTerminalCommand("cmd.exe", "/c", "start", reportPath);
          } else {
              TerminalUtils.executeTerminalCommand("open", reportPath);
          }

      }
    }




    public static void attachAllureLogs(){
        try {
            File logFile = FilesUtils.getLatestFiles(LogsUtil.LOGS_PATH);
            if (!logFile.exists()){
                LogsUtil.warn("log file does not exist :" + LogsUtil.LOGS_PATH);
                return;
            }
            Allure.addAttachment("logs.log", Files.readString(Path.of(logFile.getPath())));
            LogsUtil.info("Logs attached to Allure report");
        } catch (Exception e){
            LogsUtil.error("failed to attach logs to allure report:" + e.getMessage());
        }





    }
    public static void attachScreenshot(String screenshotName , String screenshotPath){
        try {

            Allure.addAttachment(screenshotName , newInputStream(Path.of(screenshotPath)));
        } catch (Exception e){
            LogsUtil.error("failed to attach screenshot" + e.getMessage());
        }


    }
}
