package com.wpw.springbootjdbc;

import com.wpw.springbootjdbc.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wpw
 */
@RestController
@Slf4j
public class UserController {
    @GetMapping(value = "/getUserList")
    private List<User> getUserList() {
        Connection connection = DbUtil.getConnection();
        List<User> userList = new ArrayList<>();
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                user = User.builder().id(id).userName(username).passWord(password).build();
                userList.add(user);
            }
        } catch (SQLException e) {
            log.error("error message:{}", e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("connection close fail");
            }
        }
        return userList;
    }

    @PostMapping(value = "/saveUser")
    public void saveUser(@RequestBody User user) {
        Connection connection = DbUtil.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user(id,username,password) values(?,?,?)");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getPassWord());
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error("insert fail");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("connection close fail");
            }
        }

    }
}
