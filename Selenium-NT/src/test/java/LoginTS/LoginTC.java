package LoginTS;

import CommonPO.BaseTest;
import CommonPO.CommonMethods;
import LoginPO.LoginPO;
import RegisterTS.RegisterTC;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTC extends BaseTest {
    private int flag = 1;

    public LoginTC() {
    }

    public LoginTC(WebDriver passDriver, int Flag) {
        driver = passDriver;
        flag = Flag;
    }

    @Test
    public void LoginTC1() {
        if (flag > 0) {
            test = extent.createTest("Login with newly created user");
            test.assignCategory("User Login Module");
        }

        LoginPO loginPO = new LoginPO(driver);
        CommonMethods commonMethods = new CommonMethods(driver);

        //Pre-Condition
        RegisterTC registerTC = new RegisterTC(driver, 0);
        registerTC.RegisterOnNopCommerceTC();

        test.log(Status.INFO, "Login Test Case Started");

        //Step 1: Click on Log In
        Assert.assertTrue(commonMethods.clickOnLinkOrButton(loginPO.loginBtn_xpath), "Unable to Click on Log In");
        test.log(Status.PASS, "Click on Log In");

        //Step 2: Enter on Email
        Assert.assertTrue(commonMethods.enterTextInInputField(loginPO.email_xpath, registerTC.email), "Unable to Enter on Email");
        test.log(Status.PASS, "Enter on Email");

        //Step 3: Enter password
        String pwd = "abc@123";
        Assert.assertTrue(commonMethods.enterTextInInputField(loginPO.password_xpath, pwd), "Unable to Enter password");
        test.log(Status.PASS, "Enter password");

        //Step 5: click on Submit button
        Assert.assertTrue(commonMethods.clickOnLinkOrButton(loginPO.submitBtn_xPath), "Unable to click on Submit button");
        test.log(Status.PASS, "click on Submit button");

        test.log(Status.INFO, "Login Test Case Ended");
    }
}
