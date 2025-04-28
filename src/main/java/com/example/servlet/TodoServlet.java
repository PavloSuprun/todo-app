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
    private TodoDao todoDao = new TodoDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Todo> todos = todoDao.getAllTodos();
            request.setAttribute("todos", todos);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Cannot retrieve todos", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                String task = request.getParameter("task");
                todoDao.addTodo(task);
            } else if (action.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                todoDao.deleteTodo(id);
            } else if (action.equals("toggle")) {
                int id = Integer.parseInt(request.getParameter("id"));
                todoDao.toggleTodo(id);
            }
        } catch (SQLException e) {
            throw new ServletException("Database operation failed", e);
        }
        response.sendRedirect("todos");
    }
}
