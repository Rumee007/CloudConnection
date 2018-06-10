/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author rumee
 */
public class AzureCloudConnection {

    public static void main(String[] args) throws Exception {
        try {
            Connection connection = createDBConnection();
            String value = "on";
           // insertData(connection, value);

            //deleteTable(connection, "weather");
            showData(connection);
//            dumpData(connection);
//            lastInsertData(connection);

            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("ERROR :" + ex);
        }
    }

    private static Connection createDBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = null;
        try {
            connection = (Connection) DriverManager.getConnection(
                    "jdbc:sqlserver://etenderdb.database.windows.net:1433;database=IOTPOC;user=etenderAdmin@etenderdb;password=Eraetender1@;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30");

            if (connection != null) {
                System.out.println("Connection up!");
            } else {
                System.out.println("Connection Failed.");
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to create connection to database.", e);
        }
        return connection;
    }

    private static boolean deleteTable(Connection connection, String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String SQL = "DELETE FROM " + tableName;
            if (statement.executeUpdate(SQL) > 0) {
                statement.close();
                return true;
            }
            statement.close();
            return false;
        }
    }

    private static boolean insertData(Connection connection, String value) throws SQLException {
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        try (Statement statement = connection.createStatement()) {
            String a = "INSERT INTO Movement_Reg (m_status, a_status, date_time) VALUES "
                    + "('"+ value + "','', '"+currentTimestamp+"')";
            
            String b = "UPDATE Movement_Reg SET m_status = 'off', date_time = '"+currentTimestamp+"' WHERE reg_id = 1";
            
            System.out.println(">>" + b);
            if (statement.executeUpdate(b) > 0) {
                statement.close();
                return true;
            }
            return false;
        }
    }

    private static void dumpData(Connection connection) throws SQLException {
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        String value = "24";
        Statement statement = connection.createStatement();
        for (int i = 0; i < 500; i++) {
            String sql = "INSERT INTO weather (temperature,currdatetime) VALUES ('" + value + "." + i + "','" + currentTimestamp + "')";
            System.out.println("sql :" + sql);
            statement.executeUpdate(sql);
        }
    }

    private static void showData(Connection connection) throws SQLException {
        String query = "SELECT * FROM Movement_Reg";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("resultSet.getString(1) :" + resultSet.getString(1));
                System.out.println("resultSet.getString(2) :" + resultSet.getString(2));
                System.out.println("resultSet.getString(3) :" + resultSet.getString(3));
                System.out.println("resultSet.getString(3) :" + resultSet.getString(4));
            }
            statement.close();
        }
    }

    private static void lastInsertData(Connection connection) throws SQLException {
        String query = "SELECT top FROM weather";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("resultSet.getString(1) :" + resultSet.getString(1));
                System.out.println("resultSet.getString(2) :" + resultSet.getString(2));
                System.out.println("resultSet.getString(3) :" + resultSet.getString(3));
            }
            statement.close();
        }
    }
}
