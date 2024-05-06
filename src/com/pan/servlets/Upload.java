package com.pan.servlets;

import com.pan.utils.Settings;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

@WebServlet("/upload")
@MultipartConfig
public class Upload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        // String fileRoot = request.getServletContext().getRealPath("/");
        String fileRoot = new Settings().fileRoot;

        String uname = request.getParameter("uname");
        Part part = request.getPart("upfile");
        String upfilename = part.getSubmittedFileName();

        try {
            part.write(fileRoot + "\\" + uname + "\\" + upfilename);
            System.out.println("Upload: " + uname + "\\" + upfilename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
