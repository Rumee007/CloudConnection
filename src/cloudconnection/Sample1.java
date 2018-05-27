///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cloudconnection;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
///**
// *
// * @author user
// */
//public class Sample1 {
//
//    public static void main(String[] args) throws IOException, SQLException {
//        // TODO: fill this in
//        // The instance connection name can be obtained from the instance overview page in Cloud Console
//        // or by running "gcloud sql instances describe <instance> | grep connectionName".
//        String instanceConnectionName = "my-test-project-201903:asia-south1:mytestdata";
//
//        // TODO: fill this in
//        // The database from which to list tables.
//        String databaseName = "weather";
//
//        String username = "rumee";
//
//        // TODO: fill this in
//        // This is the password that was set via the Cloud Console or empty if never set
//        // (not recommended).
//        String password = "123456";
//
//        if (!instanceConnectionName.equals("my-test-project-201903:asia-south1:mytestdata")) {
//            System.err.println("Please update the sample to specify the instance connection name.");
//            System.exit(1);
//        }
//
//        if (!password.equals("123456")) {
//            System.err.println("Please update the sample to specify the mysql password.");
//            System.exit(1);
//        }
//
//        //[START doc-example]
//        String jdbcUrl = String.format(
//                "jdbc:mysql://35.200.130.33:3306",
//                databaseName,
//                instanceConnectionName);
//
//        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
//        //[END doc-example]
//
//        try (Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery("SHOW TABLES");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString(1));
//            }
//        }
//    }
//}
