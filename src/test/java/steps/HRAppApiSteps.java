package steps;

import Pojos.Department;
import Pojos.Employee;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HREmployeePage;
import pages.hrAppLoginPage;
import utilities.APIUtils;
import utilities.Configuration;
import utilities.Driver;

import java.util.Map;

public class HRAppApiSteps {

    Response response;
    WebDriver driver = Driver.getDriver();
    hrAppLoginPage hrAppLoginPage = new hrAppLoginPage();
    HREmployeePage hrEmployeePage = new HREmployeePage();

    @Given("User sends create employee api post call with data")
    public void user_sends_create_employee_api_post_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        Map<String, Object> data = dataTable.asMap(String.class, Object.class);

       String endpoint = "/api/employees";

        Employee requestBody = new Employee();
        requestBody.setFirstName(data.get("firstName").toString());
        requestBody.setLastName(data.get("lastName").toString());

        Department department = new Department();
        department.setDepartmentName(data.get("departmentName").toString());
        requestBody.setDepartment(department);

         response = APIUtils.postCall(endpoint,requestBody);
    }

    @Then("User validates status code {int}")
    public void user_validates_status_code(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());

    }

    @Then("User validates data populated in UI")
    public void user_validates_data_populated_in_UI(io.cucumber.datatable.DataTable dataTable) {
        driver.get(Configuration.getProperty("HRAppUrl"));
        hrAppLoginPage.username.sendKeys(Configuration.getProperty("HRUsername"));
        hrAppLoginPage.password.sendKeys(Configuration.getProperty("HRPassword"));
        hrAppLoginPage.loginBtn.click();

        // response will include JSON body
        // JSON -> POJO (responseBody) object

        Employee responseBody = response.body().as(Employee.class);
        String employeeId = responseBody.getEmployeeId().toString();

        hrEmployeePage.searchBox.sendKeys(employeeId);
        hrEmployeePage.searchBtn.click();

        String actualFirstName = hrEmployeePage.firstName.getText();
        String actualLastName = hrEmployeePage.lastName.getText();
        String actualDepartmentName = hrEmployeePage.departmentName.getText();

        Map <String, Object> data = dataTable.asMap(String.class, Object.class);

        Assert.assertEquals(data.get("firstName").toString(),actualFirstName);
        Assert.assertEquals(data.get("lastName").toString(),actualLastName);
        Assert.assertEquals(data.get("departmentName").toString(),actualDepartmentName);


    }

    @Then("User validates employee data is persisted into DB")
    public void user_validates_employee_data_is_persisted_into_DB() {

    }

    @Then("User validates data with get employee api call")
    public void user_validates_data_with_get_employee_api_call(io.cucumber.datatable.DataTable dataTable) {

    }
}
