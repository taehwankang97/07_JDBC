package edu.kh.todolist.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import edu.kh.todolist.dto.Todo;
import edu.kh.todolist.service.TodoListService;
import edu.kh.todolist.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TodoListService service = new TodoListServiceImpl();
            Map<String, Object> map = service.todoListFullView();

            List<Todo> todoList = (List<Todo>) map.get("todoList");
            int completeCount = (int) map.get("completeCount");

            req.setAttribute("todoList", todoList);
            req.setAttribute("completeCount", completeCount);

            String path = "/WEB-INF/views/main.jsp";
            req.getRequestDispatcher(path).forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
