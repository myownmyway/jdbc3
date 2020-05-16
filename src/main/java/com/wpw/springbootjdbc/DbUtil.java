package com.wpw.springbootjdbc;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@UtilityClass
public class DbUtil {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            log.error("loading MySQL Driver fail,error message:{}", e.getMessage());
        } catch (SQLException e) {
            log.error("get sql connection fail,error message:{}", e.getMessage());
        }
        return null;
    }
}
