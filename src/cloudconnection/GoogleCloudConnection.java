package cloudconnection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GoogleCloudConnection {

    /**
     * @param args the command line arguments
     */
    private static Connection connection = null;

    public static void main(String[] args) {
        try {
            connection = createDBConnection();
            if (connection.isClosed() || connection == null) {
                System.out.println("connection is NULL :" + connection);
            }
//            if (connection.isClosed()) {
//                System.out.println("connection is Closed() :" + connection.isClosed());
//            }

//            String value = "3.3";
            //insertData(connection, value);
//            deleteTable(connection, "temperature");
            //showData(connection);            

            final long timeInterval = 1000;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    while (!Thread.currentThread().isInterrupted()) {
                        // ------- code for task to run
                        System.out.println("Hello !!");
                        String value = "3.3";
                        try {
                            insertData(connection, value);
                        } catch (SQLException ex) {
                            Logger.getLogger(GoogleCloudConnection.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        // ------- ends here
                        try {
                            Thread.sleep(timeInterval);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();

        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                System.out.println("NullPointerException");
            } else {
                System.out.println("Ex :" + e);
            }
        }
    }

    private static Connection createDBConnection() {
        while (true) {
            try {
                connection = (Connection) DriverManager.getConnection(
                        "jdbc:mysql://35.200.130.33:3306/weather", "rumee", "123s456");

                if (connection != null) {
                    System.out.println("Connection up!");
                    return connection;
                } else {
                    System.out.println("Connection Failed.");
                }
            } catch (Exception ex) {
                System.out.println("ERROR :" + ex);
            }
        }
    }

    private static void deleteTable(Connection connection, String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String SQL = "DELETE FROM " + tableName;
            statement.executeUpdate(SQL);
        }
    }

    private static void insertData(Connection connection, String value) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO temperature " + "VALUE ('" + value + "')");
            System.out.println("Insert Succesfully.............");
            statement.close();
//            connection.close();
        }
    }

    private static void showData(Connection connection) throws SQLException {
        String query = "SELECT degree FROM temperature";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
            statement.close();
        }
    }
}
