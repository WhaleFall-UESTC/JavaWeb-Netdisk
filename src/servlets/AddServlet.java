package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;

public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        System.out.println("AddServlet-call-doPost");
        String fname = request.getParameter("fname");
        Integer price = Integer.parseInt(request.getParameter("price"));
        Integer fcount = Integer.parseInt(request.getParameter("fcount"));

        System.out.println("fname = " + fname);
        System.out.println("price = " + price);
        System.out.println("fcount = " + fcount);
    }
}
