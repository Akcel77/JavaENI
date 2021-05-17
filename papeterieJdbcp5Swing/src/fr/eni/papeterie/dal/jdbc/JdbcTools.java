package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.dal.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {

    /**
     * Chargement du driver via l'url de Settings
     */
    static {
        try {
            Class.forName(Settings.getProperties("driverjdbc"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Connection a la DB via Setting url / user / password
     * @return la connection
     */
    public static Connection getConnection() throws SQLException {
        Connection myConn = DriverManager.getConnection(Settings.getProperties("url"),Settings.getProperties("user"),Settings.getProperties("password"));
        return myConn;
    }


}
