package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.EtsyHomePage;
import utilities.BrowserUtils;
import utilities.Configuration;
import utilities.Driver;

import java.util.List;
import java.util.Locale;

public class EtsyAppSteps {

    WebDriver driver = Driver.getDriver();
    EtsyHomePage etsyHomePage= new EtsyHomePage();

    @Given("User navigates to Etsy application")
    public void user_navigates_to_Etsy_application() {

        driver.get(Configuration.getProperty("EtsyURL"));


    }


    @When("User searches for {string}")
    public void user_searches_for(String item) {
        etsyHomePage.searchBox.sendKeys(item,Keys.ENTER);

    }
    @Then("User validates the search results contain")
    public void user_validates_the_search_results_contain(DataTable dataTable) {
        List<String> keywords = dataTable.asList();
        System.out.println(keywords.get(0));
        System.out.println(keywords.get(2));

        for (int i=0; i<etsyHomePage.resultItems.size();i++){
            String itemDescription = etsyHomePage.resultItems.get(i).getText();
            System.out.println(itemDescription);
            boolean isFound = false;
            for (int a=0; a<keywords.size();a++){
                if (itemDescription.toLowerCase().contains(keywords.get(a))){
                    isFound = true;
                }
            }
            Assert.assertTrue(itemDescription,isFound);
        }
    }
    @When("User selects price range more than {int}")
    public void user_selects_price_range_more_than(Integer priceRange) {  // 1000

        etsyHomePage.filtersButton.click();
        // BrowserUtils.scroll(250);
        etsyHomePage.over1000.click();
        etsyHomePage.applyButton.click();
    }

    @Then("User validates price range is more than {int}")
    public void user_validates_price_range_is_more_than(Integer priceRange) { // 1000

        for (int i=0;i<etsyHomePage.prices.size();i++ ){
            String price = etsyHomePage.prices.get(i).getText();
            price = price.replaceAll(",","");
            //  price.replaceAll("\\D+","");
            // Convert String to double
            double actualPrice = Double.parseDouble(price);
           Assert.assertTrue(actualPrice>=priceRange);

        }
    }
    @When("User clicks on {string} module")
    public void user_clicks_on_module(String module) {
       driver.findElement(By.xpath("//a[@class='wt-text-link-no-underline']//span[contains(text(),'"+module+"')]")).click();

    }

    @Then("User validates title {string}")
    public void user_validates_title(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
    }

}
