package steps;

import JDBC.JDBCUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.HREmployeePage;
import pages.hrAppLoginPage;
import utilities.Configuration;
import utilities.Driver;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class HRAppTestSteps {

    WebDriver driver = Driver.getDriver();

    @Given("user navigates to login page")
    public void user_navigates_to_login_page() {
        // driver.get("https://devenv-webhr-arslan.herokuapp.com/login");
     driver.get(Configuration.getProperty("HRURL"));
        Assert.assertEquals("HrApp",driver.getTitle());
    }

    @When("user logins to HRApp")
    public void user_logins_to_HRApp() {
        hrAppLoginPage.username.sendKeys(Configuration.getProperty("username"));
        hrAppLoginPage.password.sendKeys(Configuration.getProperty("password"));
        hrAppLoginPage.loginBtn.click();

    }

    @And("creates new employee")
    public void creates_new_employee() {
        System.out.println("New employee is created");
    }

    @Then("user validates new employee is created in database")
    public void user_validates_new_employee_is_created_in_database() throws SQLException {

        HREmployeePage.searchBox.sendKeys("101" + Keys.ENTER);
        String expected = HREmployeePage.firstName.getText();
//        System.out.println(expected);
        JDBCUtils.establishConnection();
        List<Map<String,Object>> actual = JDBCUtils.runQuery("select first_name from employees where employee_id = 101");
//        System.out.println(actual.get(0));
        Assert.assertEquals(expected,actual.get(0).get("FIRST_NAME"));

        JDBCUtils.closeConnection();
    }

}


