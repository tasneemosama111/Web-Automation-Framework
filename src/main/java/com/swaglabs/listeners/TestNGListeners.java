package com.swaglabs.listeners;

import com.swaglabs.Utils.*;
import com.swaglabs.drivers.GUIDriver;
import org.apache.commons.io.FileUtils;
import org.testng.*;

import java.io.File;

import static com.swaglabs.Utils.PropertiesUtils.loadProperties;

public class TestNGListeners implements IExecutionListener, ITestListener , IInvokedMethodListener {

    File allure_results = new File("test-outputs");
    File logs = new File("test-outputs/Logs");
    File screenshots = new File("test-outputs/screenshots");



    @Override
    public void onExecutionStart() {
    LogsUtil.info("Test Execution started");

    // 2. Load data from .properties files into the system memory
    loadProperties();
    // 3. Delete old Allure results files to keep the new report clean
    // This prevents old failed runs from showing up in your new report
    FilesUtils.deleteFiles(allure_results);
    FilesUtils.cleanDirectory(logs);
    FilesUtils.cleanDirectory(screenshots);
        FilesUtils.createDirectory(allure_results);
        FilesUtils.createDirectory(logs);
        FilesUtils.createDirectory(screenshots);



    }

@Override
    public void onExecutionFinish() {

    LogsUtil.info("Test Execution finished");
    AllureUtils.generateAllureReport();
   String reportName = AllureUtils.renameReport();
    AllureUtils.openReport(reportName);

}

@Override
public void afterInvocation(IInvokedMethod method, ITestResult testResult) {



    // Check if the current method is a @Test method (not @Before/@After) , This ensures that logs and screenshots are only attached to actual test cases
    //this is to make sure that it attach logs only to test methods not before/after methods we dont need it
if (method.isTestMethod()) {





    //we deleted this based on video 20
//    try {   //  We MUST call assertAll() at the end to "collect" all failures. If we skip this line, the test will always pass (Green) even if there are mismatches
//        CustomSoftAssertion.customAssertAll();
//    } catch (AssertionError e){
//        // FYI: If any assertion failed, we catch the error to manually tell TestNG
//        // to mark this specific test as "FAILED" in the final report.
//        testResult.setStatus(ITestResult.FAILURE);
//        // FYI: We attach the error details so you can see the reason for failure
//        // inside Allure or your console logs.
//        testResult.setThrowable(e);
//    }

    CustomSoftAssertion.customAssertAll(testResult);

    // this switch to handle screenshots based on the test status (Success, Failure, or Skip)
    switch (testResult.getStatus()){
        case ITestResult.SUCCESS -> ScreenshotsUtils.takeScreenShots(GUIDriver.getInstance(), "passed-" + testResult.getName());
        case ITestResult.FAILURE -> ScreenshotsUtils.takeScreenShots(GUIDriver.getInstance(),"failed-" + testResult.getName());
        case ITestResult.SKIP ->ScreenshotsUtils.takeScreenShots(GUIDriver.getInstance(),"skipped-" + testResult.getName());
    }
    AllureUtils.attachAllureLogs();
}


}
@Override
public void onTestSuccess(ITestResult result) {
    // hena ba2ol en el test case w b3mel mention l esm el method w b2ol enha passed
    LogsUtil.info("Testcase " , result.getName() , "is passed");
    // FYI : hanshel el ss mn hena 3shan kan fe error fl logs ely fl sonsole bt2ol eno msh 3aref yakhod ss l2en el test bykon khlas run fa bygble error no test is running so we solved this eny ahotohm fl afterinvokation w elmethod shghala awl mtkhlas fl lahza de khod ss w 3amlna switch case l case law passed - failed - skipped
  //  ScreenshotsUtils.takeScreenShots("passed-" + result.getName());


}

@Override
public void onTestFailure(ITestResult result) {
        LogsUtil.info("Test case " , result.getName() , "is failed");
    // FYI: hanshel el ss mn hena 3shan kan fe error fl logs ely fl sonsole bt2ol eno msh 3aref yakhod ss l2en el test bykon khlas run fa bygble error no test is running so we solved this eny ahotohm fl afterinvokation w elmethod shghala awl mtkhlas fl lahza de khod ss w 3amlna switch case l case law passed - failed - skipped
    //ScreenshotsUtils.takeScreenShots("failed-" + result.getName());

}

@Override
public void onTestSkipped(ITestResult result) {
        LogsUtil.info("Testcase " , result.getName() , "is skipped");
    //FYI: hanshel el ss mn hena 3shan kan fe error fl logs ely fl sonsole bt2ol eno msh 3aref yakhod ss l2en el test bykon khlas run fa bygble error no test is running so we solved this eny ahotohm fl afterinvokation w elmethod shghala awl mtkhlas fl lahza de khod ss w 3amlna switch case l case law passed - failed - skipped
    //ScreenshotsUtils.takeScreenShots("skipped-" + result.getName());
    }










}
