package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HREmployeePage {

    public HREmployeePage() {
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Employee ID..']")
    public static WebElement searchBox;
    @FindBy(xpath = "//td[2]")
    public static WebElement firstName;
    @FindBy(xpath = "//td[3]")
    public WebElement lastName;
    @FindBy(id = "searchButton")
    public WebElement searchBtn;
    @FindBy(id = "//td[4]")
    public WebElement departmentName;
}
