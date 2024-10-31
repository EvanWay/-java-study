package com.evan.study.leetcode.interview;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WeatherDataManager {
    public void fetchAndStoreWeatherData(String apiUrl, String dbUrl, String dbUser, String dbPassword) {
        HttpURLConnection apiConnection = null;
        BufferedReader reader = null;
        Connection dbConnection = null;
        PreparedStatement stmt = null;
        try {
            URL url = new URL(apiUrl);
            apiConnection = (HttpURLConnection) url.openConnection();
            apiConnection.setRequestMethod("GET");
            reader = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            dbConnection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            dbConnection.setAutoCommit(false);
            stmt = dbConnection.prepareStatement("INSERT INTO weather_data (data) VALUES (?)");
            stmt.setString(1, response.toString());
            stmt.executeUpdate();
            dbConnection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (dbConnection != null) {
                try {
                    dbConnection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (reader != null) reader.close();
                if (stmt != null) stmt.close();
                if (dbConnection != null) dbConnection.close();
                if (apiConnection != null) apiConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        WeatherDataManager manager = new WeatherDataManager();
        manager.fetchAndStoreWeatherData("https://api.example.com/weather", "jdbc:mysql://localhost/test", "user", "password");
    }
}