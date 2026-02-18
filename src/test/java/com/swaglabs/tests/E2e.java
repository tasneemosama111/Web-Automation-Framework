package com.swaglabs.tests;
import com.swaglabs.Utils.*;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.*;
import com.swaglabs.drivers.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static com.swaglabs.Utils.TimeStampUtils.getTimestamp;

@Listeners(TestNGListeners.class)
public class E2e {
    //Variables
   //مبقاش ليه لزمة تحتفظي بـ driver variable في كل class
    //الـ driver خلاص بقى centralized in ThreadLocal وبتجيبيه في أي مكان بنفس الطريقة
    GUIDriver driver;
    JsonUtils testData;
    String FIRST_NAME;
    String LAST_NAME;



    //TESTS
    @Test
    public void SuccessfulLogin(){
        // hena 3amlt el fluent pattern that let me do "method chaining" ely bykhlenee acall aktr mn function mn el page class in the same line by putting the returntype of the function the classname that i want to return to it to take the next method w lazem akteb gwa el function de return this; if its in the same class as below:

        new LoginPage(driver).enterUserName(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLoginButton()
                .assertSuccessfulLogin();


    }
    @Test(dependsOnMethods = "SuccessfulLogin")
    public void addingProductToCart(){
        new HomePage(driver).addSpecificProductToCart(
                testData.getJsonData("product-names.item1.name"))
                .assertProductAddedToCart(testData.getJsonData("product-names.item1.name"));

    }
    @Test (dependsOnMethods = "addingProductToCart")
    public void checkOutProduct(){
        new HomePage(driver).clickOnCartIcon()
                .assertProductDetais(
                        testData.getJsonData("product-names.item1.name"),
                        testData.getJsonData("product-names.item1.price"));
    }


    @Test (dependsOnMethods = "checkOutProduct")
    public void fillInformationForm(){
//
        new CartPage(driver).clickOnCHekOutButton().fillInformationForm(
                        FIRST_NAME,
                        LAST_NAME,
                testData.getJsonData("information-form.postal-code")
                                 )
                .validateInfoPage(
                        FIRST_NAME,
                        LAST_NAME,
                testData.getJsonData("information-form.postal-code"));
    }

    @Test (dependsOnMethods = "fillInformationForm")
    public void finishCheckOut(){
        new  InformationPage(driver)
                .clickOnContinue()
                .clickOnFinish()
                .validateConfirmationMessage(testData.getJsonData("confirmation-message"));

    }






    //configuration
    @BeforeClass
   public void BeforeClass(){
        //code
        // Create a new instance of JsonUtils to read data from the "test-data.json" file,This allows us to pull test data (like usernames and passwords) into our test cases
         // 1. Initialize JsonUtils to fetch test data (usernames/passwords) from "test-data.json" in resources
        testData = new JsonUtils("test-data");

          FIRST_NAME = testData.getJsonData("information-form.first-name") + getTimestamp();

          LAST_NAME = testData.getJsonData("information-form.last-name") + getTimestamp();

        // 2. Retrieve the browser type (e.g., chrome) defined in your configuration properties file
        String browserName = PropertiesUtils.getPropertyValue("browserType");
        // 3. Create a fresh browser instance using the GUIDriver based on the retrieved browser name
        driver= new GUIDriver(browserName);
        // 4. Use the LoginPage object to trigger navigation to the application's login URL
        new LoginPage(driver).navigateToLoginPage();
        // 3amlt anonmynous object yndhly 3la el login page el class w ygeb el function bta3et navigate
    }

    @AfterClass
    public void tearDown(){
       driver.browser().closeBrowser();
    }

}

