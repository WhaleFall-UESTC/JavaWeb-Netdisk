package com.pan.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/delete")
public class Delete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        ServletContext context = getServletContext();
        String fileRoot = Paths.get(context.getRealPath("/pan"), "files").toString();

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");

        String uname = request.getParameter("uname");
        String userRoot = Paths.get(fileRoot, uname).toString();
        String filename = request.getParameter("deletefile");
        if (filename == null || "".equals(filename.trim())) {
            response.getWriter().write("Input the filename");
            return;
        }

        Path filePath = Paths.get(userRoot, filename);
        if (Files.exists(filePath) && Files.isRegularFile(filePath)) {
            try {
                Files.delete(filePath);
                System.out.println("Deleted the file: " + uname + "\\" + filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found");
        }

        response.sendRedirect("/pan/listfiles");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
