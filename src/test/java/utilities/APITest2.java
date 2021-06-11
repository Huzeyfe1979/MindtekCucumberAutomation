package utilities;

import Pojos.Employee;
import io.restassured.response.Response;

public class APITest2 {

    public static void main(String[] args) {

       // Response response1 = APIUtils.getCall("/api/departments/10");
        Response response1 = APIUtils.getCall("/api/employees/100");
        System.out.println(response1.body().asString());

        response1.then().log().all();

        System.out.println(response1.andReturn().statusCode());

        Employee responseBody = new Employee(); // Employee Object

        //Deserilization -> Converting JSON to POJO
        responseBody = response1.body().as(Employee.class);

        System.out.println(responseBody.getLastName());
        System.out.println(responseBody.getJob().getSalary());
        System.out.println(responseBody.getDepartment().getLocation().getLocationCity());
        System.out.println(responseBody.getDepartment().getDepartmentName());




        /*
         Assignment:

         Create Department with name "Student Relations"
         Get Department created department
         Update department name with to "SR"
         Delete created department
         Validate it is deleted

         ** API calls
         */
    }
}
