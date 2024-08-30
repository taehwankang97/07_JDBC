package edu.kh.jdbc.controller;

import java.io.IOException;
import java.sql.Connection;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;
import edu.kh.jdbc.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.tagext.TryCatchFinally;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet{

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			try {
				String userPW = req.getParameter("userPw");
				String userName = req.getParameter("userName");

				int userNo 
				 	= Integer.parseInt(req.getParameter("userNo"));
				
				// 파라미터 세팅
				User user = new User();
				user.setUserPw(userPW);
				user.setUserName(userName);
				user.setUserNo(userNo);

				
				UserService service = new UserServiceImpl();
				
				int result = service.updateUser(user);
				
				String message =null;
				
				if(result > 0) message = "수정 성공";
				else           message = "수정 실패";
				
				req.getSession().setAttribute("message", message);
				
				// 사용자 상세 정보 조회 
				resp.sendRedirect("/selectUser?userNo=" + userNo);
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
}
