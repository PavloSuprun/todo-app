<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Todo" %>

<!DOCTYPE html>
<html>
<head>
    <title>Todo List</title>
</head>
<body>
    <h1>Todo List</h1>

    <form method="post" action="todos">
        <input type="text" name="task" required />
        <input type="submit" value="Add" />
    </form>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Task</th>
            <th>Completed</th>
        </tr>
        <%
            List<Todo> todos = (List<Todo>) request.getAttribute("todos");
            for (Todo todo : todos) {
        %>
        <tr>
            <td><%= todo.getId() %></td>
            <td><%= todo.getTask() %></td>
            <td><%= todo.isDone() ? "Yes" : "No" %></td>
        </tr>
        <% } %>
    </table>

</body>
</html>
