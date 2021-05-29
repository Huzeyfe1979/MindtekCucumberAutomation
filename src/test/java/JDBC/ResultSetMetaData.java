package JDBC;

import java.sql.*;

public class ResultSetMetaData {

    public static void main(String[] args) throws SQLException {


        String URL = "jdbc:oracle:thin:@command.cjvmflvwtxbh.us-east-2.rds.amazonaws.com:1521/ORCL";
        String username = "huzeyfe";
        String password = "agalarim20171";

        DriverManager.getConnection(URL, username, password);
        Connection connection = DriverManager.getConnection(URL, username, password);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employees");

        java.sql.ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        System.out.println("employees table has " +resultSetMetaData.getColumnCount());

        System.out.println(resultSetMetaData.getColumnName(2));
        System.out.println(resultSetMetaData.getColumnName(1));
        System.out.println(resultSetMetaData.getColumnName(3));
    }


}
