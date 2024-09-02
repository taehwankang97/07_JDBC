package edu.kh.todolist.controller;

import java.io.IOException;

import edu.kh.todolist.dto.Todo;
import edu.kh.todolist.service.TodoListService;
import edu.kh.todolist.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo/detail")
public class DetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int index = Integer.parseInt(req.getParameter("index"));

            TodoListService service = new TodoListServiceImpl();
            Todo todo = service.todoDetailView(index);

            if (todo == null) {
                HttpSession session = req.getSession();
                session.setAttribute("message", "해당 index의 할 일이 존재하지 않습니다.");
                resp.sendRedirect("/");
                return;
            }

            req.setAttribute("todo", todo);
            String path = "/WEB-INF/views/detail.jsp";
            req.getRequestDispatcher(path).forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
