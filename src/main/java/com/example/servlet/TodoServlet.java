package com.example.servlet;

import com.example.dao.TodoDao;
import com.example.model.Todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/todos")
public class TodoServlet extends HttpServlet {

    private final TodoDao todoDao = new TodoDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Todo> todos = todoDao.getAllTodos();
            req.setAttribute("todos", todos);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Cannot retrieve todos", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String task = req.getParameter("task");
        if (task != null && !task.trim().isEmpty()) {
            try {
                todoDao.addTodo(task);
            } catch (SQLException e) {
                throw new ServletException("Cannot add todo", e);
            }
        }
        resp.sendRedirect("todos");
    }
}
