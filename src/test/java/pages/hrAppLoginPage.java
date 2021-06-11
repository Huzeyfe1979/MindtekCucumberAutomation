package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class hrAppLoginPage {

    public hrAppLoginPage() {
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }
    @FindBy (name = "username")
    public static WebElement username;
    @FindBy (name = "password")
    public static WebElement password;
    @FindBy (xpath = "//button")
    public static  WebElement loginBtn;
}
