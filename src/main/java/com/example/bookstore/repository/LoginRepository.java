package com.example.bookstore.repository;

import com.example.bookstore.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LoginRepository {


    private final JdbcTemplate jdbcTemplate;

    public LoginRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public void addUser(User newUser) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, newUser.getUsername(), newUser.getPassword());

    }

    public void deleteUser(User targetUser) {
            String sql = "DELETE FROM users WHERE username = ?";
            jdbcTemplate.update(sql, targetUser.getUsername());
    }


    public List<User> getUsers() {
        String sql = "SELECT * FROM users ";
        return getUsers(sql);
    }

    public List<User> getAdmins() {
        String sql = "SELECT * FROM admins ";
        return getUsers(sql);
    }

    private List<User> getUsers(String sql) {
        RowMapper<User> purchaserowmapper = (r, i) ->{
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setUsername(r.getString("username"));
            rowObject.setPassword(r.getString("password"));
            return rowObject ;

        };

        return jdbcTemplate.query(sql , purchaserowmapper) ;
    }

}
