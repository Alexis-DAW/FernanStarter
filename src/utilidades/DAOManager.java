package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOManager {
    private Connection connection;
    private final String URL;
    private final String USER;
    private final String PASS;
    private static DAOManager singlenton;

    private DAOManager() {
        this.connection = null;
        this.URL= "jdbc:mysql://localhost:3310/fernanstarter";
        this.USER= "root";
        this.PASS= "";
    }

    public static DAOManager getSinglentonInstance(){
        if (singlenton == null) {
            singlenton = new DAOManager();
            return singlenton;
        } else return null;
    }

    public void open() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean ejecutaSentencia(String sql){
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
