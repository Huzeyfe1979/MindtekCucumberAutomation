package steps;

import Pojos.BankPojos.Account;
import Pojos.BankPojos.Customer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.APIUtilsBank;
import utilities.Configuration;

import java.util.*;

import static io.restassured.RestAssured.given;

public class BankApiSteps {

    Response response;
    String customerId;
    String accountID;

    @Given("User sends get customer api call with access token")
    public void user_sends_get_customer_api_call_with_access_token() {
        response = APIUtilsBank.getCall("/api/customers");
    }

    @Then("User validates {int} status code")
    public void user_validates_status_code(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());

    }

    @Given("User sends get customers api call without access token")
    public void user_sends_get_customers_api_call_without_access_token() {
        response = given().baseUri(Configuration.getProperty("ApiBankBaseURL"))
                .accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .when().get("/api/customers");
    }

    @Given("User creates customers with post api call using data")
    public void user_creates_customers_with_post_api_call_using_data(DataTable dataTable) {
        List<Map<String, Object>> data = dataTable.asMaps(String.class, Object.class);
        /*
        1.Headers
        2. Body
        3. Endpoint
         */

        String endpoint = "/api/customer";

        for (int i = 0; i < data.size(); i++) {
            Customer customer = new Customer();
            customer.setFullName(data.get(i).get("Name").toString());
            customer.setAddress(data.get(i).get("Address").toString());
            customer.setActive(Boolean.valueOf(data.get(i).get("isActive").toString())); // Object -> String ->  Boolean
            // bu yukarisi body

            response = APIUtilsBank.postCall(endpoint, customer);
            Assert.assertEquals(201, response.getStatusCode());
        }
    }

    @When("User sends get customers api call with {string} order")
    public void user_sends_get_customers_api_call_with_order(String order) {
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("order", order);
        response = APIUtilsBank.getCall("/api/customers", queryParam);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("User validates that customers are in {string} order")
    public void user_validates_that_customers_are_in_order(String order) {
        // Storing response body into Customers array
        Customer[] customers = response.body().as(Customer[].class);
        // Validating customers array names are in ascending order
        // We can create a new list and we will save original values
        // in that list, then we will sort customers, and then compare
        // if they are equal.

        List<Customer> actualCustomers = Arrays.asList(customers);
        List<Customer> expectedCustomers = actualCustomers;
        if (order.equalsIgnoreCase("asc")) {

            Collections.sort(actualCustomers, new Customer()); // this will sort customers by ascending order
            Assert.assertEquals(expectedCustomers, actualCustomers);

        } else if (order.equalsIgnoreCase("desc")) {
            Collections.sort(actualCustomers, Collections.reverseOrder(new Customer()));
            Assert.assertEquals(expectedCustomers, actualCustomers);
        }
    }
    @When("User sends get customers api call with {string}: limit")
    public void user_sends_get_customers_api_call_with_limit(String limit) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("limit", limit);
        response = APIUtilsBank.getCall("/api/customers", queryParams);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("User validates that get customers response has {string} customers")
    public void user_validates_that_get_customers_response_has_customers(String limit) {
        Customer[] customers = response.body().as(Customer[].class);
        Assert.assertEquals(limit, customers.length + "");
    }

    @Then("User validates that customer is created with data")
    public void user_validates_that_customer_is_created_with_data(DataTable dataTable) {
       /*
       Headers
       Endpoint -> /api/customers/id
        */

        Customer customer = response.body().as(Customer.class); // Json-> POJO = Deserialization
        customerId = customer.getId().toString();
        String endpoint = "/api/customers/" + customerId;

        response = APIUtilsBank.getCall(endpoint);
        Assert.assertEquals(200, response.getStatusCode());

        List<Map<String, Object>> expectedData = dataTable.asMaps(String.class, Object.class);
        Customer customer1 = response.body().as(Customer.class); // Json -> POJO = Deserialization

        Assert.assertEquals(expectedData.get(0).get("Name").toString(), customer1.getFullName());
        Assert.assertEquals(expectedData.get(0).get("Address").toString(), customer1.getAddress());
        Assert.assertEquals(expectedData.get(0).get("isActive").toString(), customer1.getIsActive().toString());
    }

    @When("User deletes created customer")
    public void user_deletes_created_customer() {
        /*
        Endpoint: /api/customers/:id
         */
        String endpoint = "/api/customers/" + customerId;
        response = APIUtilsBank.deleteCall(endpoint);
        Assert.assertEquals(204, response.statusCode());

    }

    @Then("User validates that customer is deleted")
    public void user_validates_that_customer_is_deleted() {
        /*
        Send get call and validate that customer not found
        with status code 404
        Endpoint : /api/customers/:id
         */
        String endpoint = "/api/customers/" + customerId;
        response = APIUtilsBank.getCall(endpoint);
        Assert.assertEquals(404, response.statusCode());

        String errorMessage = response.jsonPath().getString("message"); // from response body
        String expectedErrorMessage = "Customer not found with ID " + customerId;  // expected response code
        Assert.assertEquals(expectedErrorMessage, errorMessage);

    }

    @When("User creates an account for a customer with data")
    public void user_creates_an_account_for_a_customer_with_data(DataTable dataTable) {
        /*
        Headers
        Body
        Endpoint
         */
        String endpoint= "/api/account";

        Account account = new Account();
        Map<String, Object> data = dataTable.asMap(String.class,Object.class);
        account.setAccountType(data.get("accountType").toString());
        account.setBalance(Double.valueOf(data.get("Balance").toString()));
        Customer customer =new Customer();
        customerId =response.jsonPath().getString("id");
        customer.setId(Integer.valueOf(customerId));
        account.setCustomer(customer);

        response = APIUtilsBank.postCall(endpoint,account); // account POJO -> Json = Serialization
        Assert.assertEquals(201,response.statusCode());
        accountID = response.jsonPath().getString("id");

    }
        @Then("User validates that customer is linked to created account")
        public void user_validates_that_customer_is_linked_to_created_account (DataTable dataTable) {
             /*
             Send get customer api call, and validate json body includes that account info
             Get customer
             1. Endpoint -> "/api/customer/id"
             2. Headers are already set
              */
            String endpoint = "/api/customers/"+customerId;
            response = APIUtilsBank.getCall(endpoint);
            Customer customer = response.body().as(Customer.class);
            Map<String,Object> expectedData = dataTable.asMap(String.class, Object.class);

            /*
            1. Account -> accountType
            2. Account -> balance
            3. Customer -> customerId
             */
            Assert.assertEquals(expectedData.get("accountType").toString(),customer.getAccounts().get(0).getAccountType());
            Assert.assertEquals(Double.valueOf(expectedData.get("Balance").toString()),customer.getAccounts().get(0).getBalance());
            Assert.assertEquals(Integer.valueOf(customerId), customer.getId());
     }
    @When("User deletes created account")
    public void user_deletes_created_account() {
        /*
        Delete call:
        1. Endpoint -> /api/accounts/id
         */
        String endpoint = "/api/accounts/"+accountID;
        response = APIUtilsBank.deleteCall(endpoint);
        Assert.assertEquals(204,response.statusCode());

    }

    @Then("User validates that account is deleted")
    public void user_validates_that_account_is_deleted() {
        /*
        Get account api call
        1. Endpoint -> /api/accounts/id
         */
        String endpoint = "/api/accounts/"+accountID;
        response = APIUtilsBank.getCall(endpoint);
        Assert.assertEquals(404,response.statusCode());
        String expectedErrorMessage = "Account not found with ID "+accountID;
        Assert.assertEquals(expectedErrorMessage, response.jsonPath().getString("message"));

    }


}
