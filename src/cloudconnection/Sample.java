///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package cloudconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class Sample {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String databaseName = "weather";
        String instanceConnectionName = "my-test-project-201903:asia-south1:mytestdata";

        //202.40.190.10
        String username = "rumee";
        String password = "123456";
        Class.forName("com.mysql.jdbc.Driver");
        String jdbcUrl = String.format(
                "jdbc:mysql://google/%s?cloudSqlInstance=%s&"
                + "socketFactory=com.google.cloud.sql.mysql.SocketFactory",
                databaseName,
                instanceConnectionName);

        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

        if (connection != null) {
            System.out.println("Connection up!");
            connection.close();
        } else {
            System.out.println("Connection Failed.");
        }
    }
}
