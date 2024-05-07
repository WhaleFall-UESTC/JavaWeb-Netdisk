package com.pan.servlets;

import com.pan.utils.Settings;
import com.pan.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/listfiles")
public class List extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String fileRoot = new Settings().fileRoot;

        String uname = request.getParameter("uname");
        String userRoot = fileRoot + uname;

        File file = new File(userRoot);
        if (!file.exists()) {
            return;
        }
        File[] files = file.listFiles();

        request.setAttribute("files", files);
        super.processTemplate("files", request, response);
    }
}
