package com.enigma.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    // Properti berisi konfigurasi untuk koneksi ke database
    String dbHost = "localhost";
    String dbPort = "5432";
    String dbName = "enigmat";
    String dbUser = "postgres";
    String dbPassword = "indonesia";

    String dbUrl = String.format("jdbc:postgresql://%s:%s/%s", dbHost, dbPort, dbName);

    public Connection connect() {
        Connection conn = null;
        try {
            // Mengambil koneksi ke database
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("Connect to postgres is successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
