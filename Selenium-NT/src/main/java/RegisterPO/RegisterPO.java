package RegisterPO;

import CommonPO.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPO extends BasePage {
    public RegisterPO(WebDriver driver) {
        super(driver);
    }

    ////////////////////////Locator Start//////////////////////////////
    public By register_xpath = By.xpath("//a[@class='ico-register']");
    public By gender_xpath = By.id("gender-male");
    public By firstName_xpath = By.id("FirstName");
    public By lastName_xpath = By.id("LastName");
    public By date_xpath = By.xpath("//select[@name='DateOfBirthDay']");
    public By month_xpath = By.xpath("//select[@name='DateOfBirthMonth']");
    public By year_xpath = By.xpath("//select[@name='DateOfBirthYear']");
    public By email_xpath = By.id("Email");
    public By company_xpath = By.id("Company");
    public By password_xpath = By.id("Password");
    public By confirmPassword_xpath = By.id("ConfirmPassword");
    public By registerBtn_xpath = By.name("register-button");
    public By continueBtn_xpath = By.name("register-continue");
    public By logOutBtn_xpath = By.xpath("//a[text()='Log out']");
    /////////////////////////////Locator End///////////////////////////////
}