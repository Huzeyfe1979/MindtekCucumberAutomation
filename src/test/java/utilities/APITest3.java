package utilities;

import Pojos.Department;
import Pojos.Employee;
import Pojos.Job;
import io.restassured.response.Response;

public class APITest3 {

    public static void main(String[] args) {

        /*
         Create an Employee
         Post call
         1. Endpoint
         2. Headers
         3. Json
         */

        String endpoint = "/api/employees";

        Employee requestBody = new Employee();
        requestBody.setFirstName("Huzeyfe");
        requestBody.setLastName("Sutcu");

        Department department = new Department();
        department.setDepartmentName("IT");
        requestBody.setDepartment(department);

        Job job = new Job();
        job.setTitle("SDET");
        job.setSalary(10000.0);
        requestBody.setJob(job);

        // POJO (requestBody) -> JSON ===> Serialization
        Response response = APIUtils.postCall(endpoint,requestBody); // Endpoint and Json Body

        System.out.println(response.statusCode());
    }
}
