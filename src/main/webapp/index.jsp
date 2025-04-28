<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Todo" %>

<!DOCTYPE html>
<html>
<head>
    <title>Todo List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            text-align: center;
        }
        table {
            margin: 20px auto;
            border-collapse: collapse;
            width: 60%;
        }
        table, th, td {
            border: 1px solid black;
            padding: 10px;
        }
        form {
            margin-bottom: 20px;
        }
        input[type="text"] {
            padding: 5px;
            width: 200px;
        }
        input[type="submit"], button {
            padding: 5px 10px;
            margin: 5px;
        }
    </style>
</head>
<body>
    <h1>Todo List</h1>

    <form method="post" action="todos">
        <input type="text" name="task" required />
        <input type="submit" value="Add" />
    </form>

    <table>
        <tr>
            <th>ID</th>
            <th>Task</th>
            <th>Completed</th>
            <th>Actions</th>
        </tr>
        <%
            List<Todo> todos = (List<Todo>) request.getAttribute("todos");
            for (Todo todo : todos) {
        %>
        <tr>
            <td><%= todo.getId() %></td>
            <td><%= todo.getTask() %></td>
            <td><%= todo.isDone() ? "Yes" : "No" %></td>
            <td>
                <form method="post" action="todos" style="display:inline;">
                    <input type="hidden" name="action" value="delete" />
                    <input type="hidden" name="id" value="<%= todo.getId() %>" />
                    <input type="submit" value="Delete" />
                </form>
                <form method="post" action="todos" style="display:inline;">
                    <input type="hidden" name="action" value="toggle" />
                    <input type="hidden" name="id" value="<%= todo.getId() %>" />
                    <input type="submit" value="Toggle" />
                </form>
            </td>
        </tr>
        <% } %>
    </table>

</body>
</html>
