package com.example.dao;

import com.example.model.Todo;
import com.example.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {

    public List<Todo> getAllTodos() throws SQLException {
        List<Todo> todos = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, task, is_done FROM todos")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String task = rs.getString("task");
                boolean isDone = rs.getBoolean("is_done");
                todos.add(new Todo(id, task, isDone));
            }
        }

        return todos;
    }

    public void addTodo(String task) throws SQLException {
        String sql = "INSERT INTO todos (task) VALUES (?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task);
            pstmt.executeUpdate();
        }
    }
}
