package ru.netology.data;

import java.sql.*;

public class DBHelper {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/app";
    private static final String DB_USER = "app";
    private static final String DB_PASS = "123qwe!@#";

    private static String getId(User user) {
        String QUERY_ID = "SELECT id FROM users WHERE login = '" + user.getLogin() + "'";
        String result = null;
        try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY_ID);) {
            while (rs.next()) {
                result = rs.getString("id");
            }
        } catch (SQLException e ) {
            System.out.println(e);
        }
        return result;
    }

    public static String getCode(User user) {
        String QUERY_CODE = "SELECT code FROM auth_codes WHERE user_id = '" + getId(user) + "' ORDER BY created DESC LIMIT 1";
        String result = null;
        try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY_CODE);) {
            while (rs.next()) {
                result = rs.getString("code");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public static String getUserStatus(User user) {
        String QUERY_STATUS = "SELECT status FROM users WHERE login = '" + user.getLogin() + "'";
        String result = null;
        try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY_STATUS);) {
            while (rs.next()) {
                result = rs.getString("status");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public static void flushTables() {
        String QUERY_FLUSH_TRANSACTIONS = "DELETE FROM card_transactions";
        String QUERY_FLUSH_CODES = "DELETE FROM auth_codes";
        String QUERY_FLUSH_CARDS = "DELETE FROM cards";
        String QUERY_FLUSH_USERS = "DELETE FROM users";
        try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement stmt = conn.createStatement();) {
            stmt.executeUpdate(QUERY_FLUSH_TRANSACTIONS);
            stmt.executeUpdate(QUERY_FLUSH_CODES);
            stmt.executeUpdate(QUERY_FLUSH_CARDS);
            stmt.executeUpdate(QUERY_FLUSH_USERS);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}