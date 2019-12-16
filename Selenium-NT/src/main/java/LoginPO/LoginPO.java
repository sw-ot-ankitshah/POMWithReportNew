package LoginPO;

import CommonPO.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPO extends BasePage {
    public LoginPO(WebDriver driver) {
        super(driver);
    }

    ////////////////////////Locator Start//////////////////////////////
    public By loginBtn_xpath = By.xpath("//a[@class='ico-login']");
    public By email_xpath = By.id("Email");
    public By password_xpath = By.id("Password");
    public By submitBtn_xPath = By.xpath("//input[@value='Log in']");
    //test
    /////////////////////////////Locator End///////////////////////////
}
