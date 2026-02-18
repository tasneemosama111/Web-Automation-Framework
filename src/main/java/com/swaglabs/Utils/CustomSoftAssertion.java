package com.swaglabs.Utils;

import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

public class CustomSoftAssertion extends SoftAssert {
    // Initialize a new static instance of the custom soft assertion class
public static CustomSoftAssertion  softAssertion = new CustomSoftAssertion();

public static void customAssertAll(ITestResult result){
    // Start a try block to execute assertions and catch potential failures
    try {
        // Evaluate all recorded soft assertions and trigger failure if any mismatches exist
        softAssertion.assertAll("custom soft assertion");
    }
    // Catch the specific error thrown by TestNG when a soft assertion fails
    catch (AssertionError e){
        // Print the custom failure message and error details to the console for debugging
        // 1. print the error message to the Console for the developer to see during execution
        System.out.println("custom soft assertion failed" + e.getMessage());
        // 2. Mark the test status as FAILED in the TestNG report so it turns red in reports
        result.setStatus(ITestResult.FAILURE);
        // 3. Attach the full exception details (Stack Trace) to the report for debugging
        result.setThrowable(e);
    }
    // Use finally to ensure this code runs whether the test passed or failed
    finally {
        // Reset the soft assertion object to clear its history for the next test case
        reInitializeSoftAssert();
    }
}

private static void reInitializeSoftAssert(){
// Create a fresh instance of the soft assertion object to clear previous failure history
softAssertion = new CustomSoftAssertion();
}
}
