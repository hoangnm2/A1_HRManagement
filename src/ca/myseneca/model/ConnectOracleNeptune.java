package ca.myseneca.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * bbbb
 * This example shows Oracle "thin" driver to connect to neptune instance/service - the Oracle database on Zenit
 */
public class ConnectOracleNeptune {
    public static void main(String[] args) {
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;

            String thinDriverConnectDescriptorURL = "jdbc:oracle:thin:@//zenit.senecac.on.ca:1521/neptune" ;

            try {
            	System.out.println("aaa");
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection(thinDriverConnectDescriptorURL, "John", "password12");
                String query = "SELECT * FROM departments WHERE department_id = 10";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                resultSet.next();

                String deptName = resultSet.getString("department_name");
                System.out.println("Department Name is : " + deptName );
            }
            catch (ClassNotFoundException cnfex) {
                System.err.println("Failed to load JDBC/ODBC driver.");
            } catch (SQLException e) {
                System.out.println("The error is:  " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close(); 
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Exception caught in StatementExample.main() finally block");
                    System.out.println("Exception: " + e.getMessage());
                    e.printStackTrace();
                }
            } // Finally
        } // Main
} // Class
