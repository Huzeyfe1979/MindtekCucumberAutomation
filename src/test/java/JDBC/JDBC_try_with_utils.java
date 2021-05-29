package JDBC;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JDBC_try_with_utils {

    public static void main(String[] args) throws SQLException {

        JDBCUtils.establishConnection();
        List<Map<String,Object>> myFirstQuery = JDBCUtils.runQuery("select * from employees");

        System.out.println(myFirstQuery); // shows a list of maps
        System.out.println(myFirstQuery.get(0));
        System.out.println(myFirstQuery.get(0).get("EMPLOYEE_ID"));
        System.out.println(myFirstQuery.get(0).get("MANAGER_ID"));

        JDBCUtils.closeConnection();

        System.out.println(JDBCUtils.runQuery("select * from employees"));

    }
}