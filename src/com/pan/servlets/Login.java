package com.pan.servlets;

import com.pan.bean.User;
import com.pan.dao.UserDAO;
import com.pan.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
// 当你在Servlet类上使用@WebServlet注解时，你不需要在web.xml文件中为该Servlet定义<servlet>和<servlet-mapping>元素
// 注解本身就会告诉Servlet容器如何将URL路径映射到对应的Servlet。
public class Login extends ViewBaseServlet {
    @Override
    // 声明意图重写父类中的方法，并不是必须的
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.login(email, password);
        if(user != null){
            HttpSession session = request.getSession();
            session.setAttribute("user", user.getName());
            // response.sendRedirect("/listfiles");
            // 告诉客户端的浏览器重定向到一个新的URL，即服务器上的/listfiles路径
        } else{
            request.setAttribute("errorMsg", "email or password is wrong!");
            super.processTemplate("index",request,response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}