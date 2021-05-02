package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.WebOrdersHomePage;
import pages.WebOrdersLoginPage;
import utilities.BrowserUtils;
import utilities.Configuration;
import utilities.Driver;

import java.util.List;
import java.util.Map;

public class WebOrdersSteps {

    WebDriver driver = Driver.getDriver();
    WebOrdersLoginPage webOrdersLoginPage = new WebOrdersLoginPage();
    WebOrdersHomePage webOrdersHomePage = new WebOrdersHomePage();
    List<Map<String, Object>> data;

    String deleted1 = webOrdersHomePage.check1Content.toString();
    String deleted2 = webOrdersHomePage.check3Content.toString();
    String deleted3 = webOrdersHomePage.checkContent5.toString();


    @Given("User navigates to application")
    public void user_navigates_to_application() {
        driver.get(Configuration.getProperty("WebOrdersURL"));
    }


    @When("User login with username {string} and password {string}")
    public void user_login_with_username_and_password(String string, String string2) {
        webOrdersLoginPage.username.sendKeys("Tester");
        webOrdersLoginPage.password.sendKeys("test");
        webOrdersLoginPage.loginButton.click();

    }
    @Then("User validates that application is on homepage")
    public void user_validates_that_application_is_on_homepage() {
        String expectedTitle = "Web Orders";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
        driver.quit();

    }

    @Then("User validates error login message{string}")
    public void user_validates_error_login_message(String expectedError) {
        String actualErrorMessage = webOrdersLoginPage.errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, expectedError);
        driver.quit();
    }
    @When("User clicks on Order module")
    public void user_clicks_on_Order_module() {
        webOrdersHomePage.orderButton.click();

    }
    @When("User provides Product {string} and quantity {int}")
    public void user_provides_Product_and_quantity(String product, Integer quantity) {
        BrowserUtils.selectDropdownByValue(webOrdersHomePage.products,product);
        webOrdersHomePage.quantity.sendKeys(Keys.BACK_SPACE);
        webOrdersHomePage.quantity.sendKeys(quantity.toString()+Keys.ENTER);
    }
    @Then("User validates total is calculated properly for quantity {int}")
    public void user_validates_total_is_calculated_properly_for_quantity(Integer quantity) {
       String pricePerUnit= webOrdersHomePage.unitPrice.getAttribute("value");  //80
        int discountAmount = Integer.parseInt(webOrdersHomePage.discountBox.getAttribute("value"));
        int expectedTotal=0;
        if ( discountAmount!=0){
            expectedTotal=quantity*Integer.parseInt(pricePerUnit)*(100-discountAmount)/100;

        }else {
            expectedTotal = quantity * Integer.parseInt(pricePerUnit);
        }

       int actualTotal = Integer.parseInt( webOrdersHomePage.totalPrice.getAttribute("value"));
        Assert.assertEquals(actualTotal,expectedTotal);
    }
    @When("User creates order with data")
    public void user_creates_order_with_data(DataTable dataTable) {
        // Convert dataTable to List of Maps -> List <Map <String, Object>>
        data = dataTable.asMaps(String.class,Object.class);
        System.out.println(data.get(0).get("Product"));

        for (int i=0; i<data.size();i++) {

            BrowserUtils.selectDropdownByValue(webOrdersHomePage.products, data.get(i).get("Product").toString());
            webOrdersHomePage.quantity.sendKeys(Keys.BACK_SPACE);
            webOrdersHomePage.quantity.sendKeys(data.get(i).get("Quantity").toString());
            webOrdersHomePage.inputName.sendKeys(data.get(i).get("Customer Name").toString());
            webOrdersHomePage.inputStreet.sendKeys(data.get(i).get("Street").toString());
            webOrdersHomePage.inputCity.sendKeys(data.get(i).get("City").toString());
            webOrdersHomePage.inputState.sendKeys(data.get(i).get("State").toString());
            webOrdersHomePage.inputZip.sendKeys(data.get(i).get("Zip").toString());
            webOrdersHomePage.visaCardBtn.click();
            webOrdersHomePage.inputCCNumber.sendKeys(data.get(i).get("Card Number").toString());
            webOrdersHomePage.inputExpDate.sendKeys(data.get(i).get("Exp Date").toString());
            webOrdersHomePage.processBtn.click();

        }


    //    BrowserUtils.selectDropdownByValue(webOrdersHomePage.products,data.get(1).get("Product").toString());
    //    webOrdersHomePage.quantity.sendKeys(Keys.BACK_SPACE);
    //    webOrdersHomePage.quantity.sendKeys(data.get(1).get("Quantity").toString());
    //    webOrdersHomePage.inputName.sendKeys(data.get(1).get("Customer name").toString());
    //    webOrdersHomePage.inputStreet.sendKeys(data.get(1).get("Street").toString());
    //    webOrdersHomePage.inputCity.sendKeys(data.get(1).get("City").toString());
    //    webOrdersHomePage.inputState.sendKeys(data.get(1).get("State").toString());
    //    webOrdersHomePage.inputZip.sendKeys(data.get(1).get("Zip").toString());
    //    webOrdersHomePage.visaCardBtn.click();
    //    webOrdersHomePage.inputCCNumber.sendKeys(data.get(1).get("Card Number").toString());
    //    webOrdersHomePage.inputExpDate.sendKeys(data.get(1).get("Exp Date").toString());
    //    webOrdersHomePage.processBtn.click();
    }

    @Then("User validates success message {string}")
    public void user_validates_success_message(String expectedResult) {

        String actualResult = webOrdersHomePage.successMessage.getText();
        Assert.assertEquals(actualResult,expectedResult);
    }
    @Then("User validates that created orders are in the list of all orders")
    public void user_validates_that_created_orders_are_in_the_list_of_all_orders() {

        webOrdersHomePage.allOrdersPart.click();

        int numberOfCreatedOrders = data.size();

        for ( int i = 0, r=numberOfCreatedOrders+1; i<numberOfCreatedOrders; i++, r-- ){

            List <WebElement> row = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']//tr["+r+"]/td"));

            Assert.assertEquals(row.get(1).getText(),data.get(i).get("Customer Name"));

        }

    }
    @When("User choose the ones to be deleted and delete them")
    public void user_choose_the_ones_to_be_deleted_and_delete_them() {

         webOrdersHomePage.checkBox1.click();
         webOrdersHomePage.checkBox3.click();
         webOrdersHomePage.checkBox5.click();

         // Store names and product values as S

         webOrdersHomePage.deleteButton.click();
    }


    @Then("User validates the chosen ones are deleted")
    public void user_validates_the_chosen_ones_are_deleted() {

        String orderTable = webOrdersHomePage.orderTable.toString();

        // Verify stored names do not exist in the table

        Assert.assertFalse(orderTable.contains(deleted1) || orderTable.contains(deleted2) || orderTable.contains(deleted3));

        }
}





