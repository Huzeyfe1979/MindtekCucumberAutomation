package JDBC;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utilities.Driver;

import java.sql.*;

public class JDBCPractice {

    public static void main(String[] args) throws SQLException, InterruptedException {


        String URL = "jdbc:oracle:thin:@command.cjvmflvwtxbh.us-east-2.rds.amazonaws.com:1521/ORCL";
        String username = "huzeyfe";
        String password = "agalarim20171";

        DriverManager.getConnection(URL, username, password);
        Connection connection = DriverManager.getConnection(URL, username, password);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employees where first_name='Steven' AND last_name = 'King'");

     //   resultSet.next();
     //   System.out.println(resultSet.getString("first_name"));

     //   resultSet.next();
      //  System.out.println(resultSet.getString("first_name"));

     //   resultSet.next();
      //  System.out.println(resultSet.getInt("employee_id"));

       while (resultSet.next()) {
    //        System.out.println(resultSet.getString("first_name"));
    //        System.out.println(resultSet.getString("last_name"));

            WebDriver driver = Driver.getDriver();
            driver.get("https://www.google.com/");
            driver.findElement(By.name("q")).sendKeys(resultSet.getString("first_name") + " " +
                    resultSet.getString("last_name") + Keys.ENTER);
            Thread.sleep(1500);
            driver.quit();

        }
        // yukardakilerin yerine bu utils leri kullaniyoruz
       JDBCUtils.establishConnection();
       JDBCUtils.runQuery("select * from employees");

    }

   }
