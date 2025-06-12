package com.yourcompany.shoppingweb.dao;

import com.yourcompany.shoppingweb.model.User;
import com.yourcompany.shoppingweb.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 用户数据访问对象，使用JDBC操作数据库
public class UserDao {
    // 注册新用户，返回true表示注册成功
    public boolean register(String username, String password) {
        String checkSql = "SELECT id FROM user WHERE username = ?";
        String insertSql = "INSERT INTO user(username, password) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                return false; // 用户名已存在
            }
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, username);
                insertStmt.setString(2, password);
                insertStmt.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 校验用户名和密码，返回User对象或null
    public User login(String username, String password) {
        String sql = "SELECT id, username, password FROM user WHERE username = ? AND password = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String uname = rs.getString("username");
                String pwd = rs.getString("password");
                return new User(id, uname, pwd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
} 