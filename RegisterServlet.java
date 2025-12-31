package in.ak.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import in.ak.database.DbConnection;

@WebServlet("/regForm")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String myname = req.getParameter("name1");
        String myemail = req.getParameter("email1");
        String mypass = req.getParameter("pass1");
        String mycity = req.getParameter("city1");
        String mygender = req.getParameter("gender1");

        try {
            Connection conn = DbConnection.getConnection();
            String insert_sql_query = "INSERT INTO register (name, email, passwd, city, gender) VALUES(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(insert_sql_query);
            ps.setString(1, myname);
            ps.setString(2, myemail);
            ps.setString(3, mypass);
            ps.setString(4, mycity);
            ps.setString(5, mygender);

            int count = ps.executeUpdate();

            HttpSession session = req.getSession();
            if (count > 0) {
                session.setAttribute("success_msg", "Registered Successfully! Please login.");
                resp.sendRedirect("login.jsp");
            } else {
                session.setAttribute("error_msg", "Error occurred while registration. Please try again!");
                resp.sendRedirect("register.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            HttpSession session = req.getSession();
            session.setAttribute("error_msg", "Exception occurred! Please try again.");
            resp.sendRedirect("register.jsp");
        }
    }
}
