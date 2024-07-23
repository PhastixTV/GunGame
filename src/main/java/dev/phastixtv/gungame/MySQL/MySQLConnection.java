package dev.phastixtv.gungame.MySQL;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class MySQLConnection {

    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;

    private Connection connection;

    public MySQLConnection(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public boolean isConnected() {
        return (connection != null);
    }

    public void setConnection() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                System.out.println("[MySQL] Verbunden!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
                System.out.println("[MySQL] Verbindung getrennt!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
