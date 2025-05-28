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
        }
        else return null;
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
        try {
            if (connection == null || connection.isClosed()) {
                reconectar();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean ejecutaSentencia(String sql) {
        try {
            if (connection == null || connection.isClosed()) {
                System.err.println("⚠️ Conexión cerrada. Intentando reabrir...");
                reconectar(); //
            }
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void reconectar() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3310/fernanstarter", "root", "");
                System.out.println("✅ Conexión reabierta correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
