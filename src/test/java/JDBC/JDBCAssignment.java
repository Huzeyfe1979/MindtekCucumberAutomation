package JDBC;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utilities.Driver;

import java.sql.*;

public class JDBCAssignment {

    public static void main(String[] args) throws SQLException, InterruptedException {


        String URL = "jdbc:oracle:thin:@command.cjvmflvwtxbh.us-east-2.rds.amazonaws.com:1521/ORCL";
        String username = "huzeyfe";
        String password = "agalarim20171";

        DriverManager.getConnection(URL, username, password);
        Connection connection = DriverManager.getConnection(URL, username, password);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employees");

     //   Print employee_id, first_name, last_name, manager_id and hire_date columns from employees
     //   Print country id, country name, and region_id columns from countries
     //   Print department_id, department_name, and location_id columns from job_history
     //   Print start_date, end_date, and job_id columns from job_history.
     //   Ask google how to live on the lowest min_salary from jobs table? Min_salary should be dynamic from database

        resultSet.next();
        System.out.println(resultSet.getInt("employee_id"));

        resultSet.next();
        System.out.println(resultSet.getString("first_name"));

        resultSet.next();
        System.out.println(resultSet.getInt("manager_id"));

        resultSet.next();
        System.out.println(resultSet.getDate("hire_date"));

        resultSet.next();
        System.out.println(resultSet.getString("first_name"));
        System.out.println("-------------------------------------------------");

        ResultSet resultSet1 = statement.executeQuery("select * from countries");

        resultSet1.next();
        System.out.println(resultSet1.getString("country_id"));




    }
}