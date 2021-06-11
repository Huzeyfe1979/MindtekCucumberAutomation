package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HRAPPHomePage {

    public HRAPPHomePage (){

        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);

    }
}
