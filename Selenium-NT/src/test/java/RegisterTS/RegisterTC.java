package RegisterTS;

import CommonPO.BaseTest;
import CommonPO.CommonMethods;
import RegisterPO.RegisterPO;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTC extends BaseTest {
    public String email = "";
    private int flag = 1;

    public RegisterTC() {
    }

    public RegisterTC(WebDriver passDriver, int Flag) {
        driver = passDriver;
        flag = Flag;
    }

    @Test
    public void RegisterOnNopCommerceTC() {
        if (flag > 0) {
            test = extent.createTest("Register new user");
            test.assignCategory("User Registration Module");
        }
        test.log(Status.INFO, "Registration Test Case Started");

        RegisterPO registerPO = new RegisterPO(driver);
        CommonMethods commonMethods = new CommonMethods(driver);

        //Step 1: Click on Register
        Assert.assertTrue(commonMethods.clickOnLinkOrButton(registerPO.register_xpath), "Unable to click on Register button");
        test.log(Status.PASS, "Register page is open");

        //Step 2: Click on (male) gender
        Assert.assertTrue(commonMethods.clickOnLinkOrButton(registerPO.gender_xpath), "Unable click on (male) gender");
        test.log(Status.PASS, "Click on (male) gender");

        //Step 3: Enter First Name
        String FirstName = "Nishit";
        Assert.assertTrue(commonMethods.enterTextInInputField(registerPO.firstName_xpath, FirstName), "Unable to Enter First Name");
        test.log(Status.PASS, "Enter First Name");

        //Step 4: Enter Last Name
        String LastName = "Trivedi";
        Assert.assertTrue(commonMethods.enterTextInInputField(registerPO.lastName_xpath, LastName), "Unable to Enter Last Name");
        test.log(Status.PASS, "Enter Last Name");

        //Step 5: Select Date of Birth
        String date = "5";
        String month = "December";
        String year = "1991";
        Assert.assertTrue(commonMethods.selectFromDropDown(registerPO.date_xpath, date), "Unable to select Date");
        Assert.assertTrue(commonMethods.selectFromDropDown(registerPO.month_xpath, month), "Unable to select Month");
        Assert.assertTrue(commonMethods.selectFromDropDown(registerPO.year_xpath, year), "Unable to select Year");
        test.log(Status.PASS, "Select Date of Birth");

        //Step 6: Enter Email
        email = "nishit" + commonMethods.getCurrentDateTime() + "@test.com";
        Assert.assertTrue(commonMethods.enterTextInInputField(registerPO.email_xpath, email), "Unable to Enter Email");
        test.log(Status.PASS, "Enter Email");

        //Step 7: Enter Company Name
        String company = "Demo Testing Site";
        Assert.assertTrue(commonMethods.enterTextInInputField(registerPO.company_xpath, company), "Unable to Enter Company Name");
        test.log(Status.PASS, "Enter Company Name");

        //Step 8: Enter Password
        String password = "abc@123";
        Assert.assertTrue(commonMethods.enterTextInInputField(registerPO.password_xpath, password), "Unable to Enter Password");
        test.log(Status.PASS, "Enter Password");

        //Step 9: Enter Confirm Password
        Assert.assertTrue(commonMethods.enterTextInInputField(registerPO.confirmPassword_xpath, password), "Unable to Enter Confirm Password");
        test.log(Status.PASS, "Enter Confirm Password");

        //Step 10: Click on Register
        Assert.assertTrue(commonMethods.clickOnLinkOrButton(registerPO.registerBtn_xpath), "Unable to Click on Register");
        test.log(Status.PASS, "Click on Register");

        //Step 11: Click on Continue
        Assert.assertTrue(commonMethods.clickOnLinkOrButton(registerPO.continueBtn_xpath), "Unable to Click on Continue");
        test.log(Status.PASS, "Click on Continue");

        //Step 12: Click on Log out
        Assert.assertTrue(commonMethods.clickOnLinkOrButton(registerPO.logOutBtn_xpath), "Unable to Click on Log out");
        test.log(Status.PASS, "Click on Log out");

        test.log(Status.INFO, "Registration Test Case Ended");
    }
}