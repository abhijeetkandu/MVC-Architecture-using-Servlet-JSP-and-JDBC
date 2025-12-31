package in.ak.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.ak.database.DbConnection;
import in.ak.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logForm")
public class Login extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		PrintWriter out = resp.getWriter();
		//resp.setContentType("text/html");
		String myemail = req.getParameter("email1");
		String mypass = req.getParameter("pass1");
		
		try {
			Connection conn = DbConnection.getConnection();
			String select_sql_query = "SELECT * FROM register WHERE email=? AND passwd = ?";
			PreparedStatement ps = conn.prepareStatement(select_sql_query);
			ps.setString(1, myemail);
			ps.setString(2, mypass);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setCity(rs.getString("city"));
				user.setGender(rs.getString("gender"));
				
				HttpSession session = req.getSession();
				session.setAttribute("user_key",user);
				
//				RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
//				rd.forward(req, resp);
				resp.sendRedirect("profile.jsp");
				
			}
			else {
				out.print("<h3 style='color:green'> Email id and Password didn't matched! </h3>");
				//RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				//rd.forward(req, resp);
				resp.sendRedirect("login.jsp");
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
