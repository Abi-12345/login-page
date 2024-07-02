package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.protocol.Resultset;

@WebServlet("/Loginpage")
public class Loginpage extends HttpServlet {
	Connection cn;
		PreparedStatement ps1;
		ResultSet	 rs;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter pw=res.getWriter();
		
		
		String user =req.getParameter("username");
		
		String pds =req.getParameter("password");
		String str1 ="SELECT *FROM login where username=? and password=?";
		try {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root","abi*123");
			ps1=cn.prepareStatement(str1);
			ps1.setString(1,user);
			ps1.setString(2,pds);
			rs=ps1.executeQuery();
		
		RequestDispatcher rq=req.getRequestDispatcher("General.html");
		rq.include(req, res);
		
		if(user.equals("Abi") && pds.equals("123")) {
			HttpSession hsp=req.getSession();
			hsp.setAttribute("un", user);
			pw=res.getWriter();
			pw.print("login successfully!......");
			RequestDispatcher rds=req.getRequestDispatcher("General");
			rds.forward(req, res);
			
		}
		else {
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.forward(req, res);
			
		}}
		catch(SQLException e){
			e.printStackTrace();
		}
			
		
		
	
		
	}
	}
	


