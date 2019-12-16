package CommonPO;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CommonMethods {
    private WebDriver driver;

    public CommonMethods(WebDriver driver) {
        this.driver = driver;
    }

    public boolean clickOnLinkOrButton(By by) {
        try {
            Thread.sleep(2000);
            WebElement generic_WebL = (new WebDriverWait(driver, 60))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            generic_WebL.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean selectFromDropDown(By by, String value) {
        try {
            Thread.sleep(1000);
            Select selectRule = new Select((new WebDriverWait(driver, 60))
                    .until(ExpectedConditions.visibilityOfElementLocated(by)));
            selectRule.selectByVisibleText(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean enterTextInInputField(By by, String data) {
        try {
            WebElement generic_WebL = (new WebDriverWait(driver, 90))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            if (generic_WebL.isDisplayed()) {
                generic_WebL.clear();
                generic_WebL.sendKeys(data);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyElementIsVisible(By by) {
        try {
            Thread.sleep(1000);
            WebElement generic_WebL = driver.findElement(by);
            return generic_WebL.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clearTextBox(By by) {
        try {
            Thread.sleep(2000);
            WebElement generic_WebL = (new WebDriverWait(driver, 90))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            clickByJS(by);
            //generic_WebL.click();
            generic_WebL.clear();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clearTextBoxUsingBackSpaceKey(By by) {
        try {
            Thread.sleep(2000);
            driver.findElement(by).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            driver.findElement(by).sendKeys(Keys.BACK_SPACE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickByJS(By by) {
        try {
            WebElement webElement = (new WebDriverWait(driver, 60))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}