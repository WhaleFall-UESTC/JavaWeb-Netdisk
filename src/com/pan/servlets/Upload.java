package com.pan.servlets;

import com.pan.utils.Settings;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
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
        String uname = request.getParameter("uname");
        Part part = request.getPart("upfile");
        String upfilename = part.getSubmittedFileName();

        String fileRoot = new Settings().fileRoot;
        String userRoot = fileRoot + "\\" + uname;

        Path rootPath = Paths.get(userRoot);
        Path filePath;

        int tmp = 1;
        int dot = upfilename.lastIndexOf(".");
        String base = upfilename.substring(0, dot);
        String ext = upfilename.substring(dot + 1);
        String newUpfile = upfilename;

        while (true) {
            filePath = Paths.get(userRoot + "\\" + newUpfile);
            if (Files.exists(filePath)) {
                newUpfile = base + " (" + tmp + ")." + ext;
                tmp += 1;
            } else {
                try {
                    part.write(userRoot + "\\" + newUpfile);
                    break;
                } catch (IOException e) {
                    if (!Files.exists(rootPath)) {
                        Files.createDirectories(rootPath);
                    }
                    e.printStackTrace();
                }
            }
        }

//        if (Files.exists(filePath)) {
//            int tmp = 1;
//            String[] up = upfilename.split("\\.(?![^\\.]++)");
//            String newUpfile;
//            while (true) {
//                newUpfile = up[0] + String.format(" (%d)", tmp) + up[1];
//                try {
//                    part.write(userRoot + "\\" + newUpfile);
//                    break;
//                } catch (Exception e_) {
//                    tmp += 1;
//                }
//            }
//        }

//        try {
//            part.write(userRoot + "\\" + upfilename);
//            System.out.println("Upload: " + uname + "\\" + upfilename);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Path root = Paths.get(userRoot);
//            Path file = Paths.get(userRoot, upfilename);
//
//            if (!Files.exists(root)) {
//                Files.createDirectories(root);
//            }
//
//            if (Files.exists(file)) {
//                int tmp = 1;
//                String[] up = upfilename.split("\\.(?![^\\.]++)");
//                String newUpfile;
//                while (true) {
//                    newUpfile = up[0] + String.format(" (%d)", tmp) + up[1];
//                    try {
//                        part.write(userRoot + "\\" + newUpfile);
//                        break;
//                    } catch (Exception e_) {
//                        tmp += 1;
//                    }
//                }
//            } else {
//                part.write(file.toString());
//            }
//        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
