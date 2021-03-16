package project;

/**
 * @author Ilia Moskalenko
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCAccess {
    public static final String DB_URL = "jdbc:h2:/home/ilia/home/IliaLessons/db/testDB";
    public static final String DB_Driver = "org.h2.Driver";

    public static void main(String[] args) {
        try {
            Class.forName(DB_Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            // statement.executeUpdate("create table test(id int)");
          /*  statement.executeUpdate("insert into test values(1)");
            statement.executeUpdate("insert into test values(2)");
            statement.executeUpdate("insert into test values(3)");
            statement.executeUpdate("insert into test values(4)");
            statement.executeUpdate("insert into test values(5)");
            ResultSet resultSet = statement.executeQuery("select * from test");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id"));
            }*/
            // statement.executeUpdate("CREATE DATA BASE testJUnit");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
