package com.higradius;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@WebServlet("/update")
public class MainServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     * 
     */
	//private  db;
	private Connection con=null;
    public MainServerlet() throws SQLException {
        super();
        con=new JdbcConn().connection();
       
        // TODO Auto-generated constructor stub
        //  Edit Delete
    }
    

   




	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 BufferedReader br=req.getReader();
		 PrintWriter out = resp.getWriter();
		 resp.setContentType("text/html");
		 resp.setCharacterEncoding("UTF-8");
		 String ids=br.readLine();
		 System.out.println(ids);
		 StringBuffer sb=new StringBuffer(ids);
		 sb.deleteCharAt(0);
		 sb.deleteCharAt(sb.length()-1);
		 System.out.println(sb);
		 ids=sb.toString();
		 String id[]=ids.split(",");
		 String sql="delete from mytable where key_0=?";
		 try {
			 for(String s:id) {
				 StringBuffer sb1=new StringBuffer(s);
				 System.out.println(sb1.length());
				 sb1.deleteCharAt(0);
				 sb1.deleteCharAt(sb1.length()-1);
				 System.out.println(sb1.toString());
				 int key=Integer.parseInt(sb1.toString());
				 System.out.println(key);
				 PreparedStatement ps=con.prepareStatement(sql);
				 ps.setInt(1, key);
				 int r=ps.executeUpdate();
			 }
			 out.print("{'msg':'deleted successfully'}");
		 }catch(Exception e) {
			 System.out.println(e);
		 }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		updateData(con,req);
		//PrintWriter out = resp.getWriter();
		 resp.setContentType("text/html");
		 resp.setCharacterEncoding("UTF-8");
		 //out.print(resp.getName());
		 RequestDispatcher rd=req.getRequestDispatcher("/index.html");
			rd.include(req, resp);
	}
	
	private static String updateData(Connection con,HttpServletRequest req) {
		System.out.println("Edit-Invoice");
		System.out.println(req.getParameter("key"));
		int key=Integer.parseInt(req.getParameter("key"));
		float amt=Float.parseFloat(req.getParameter("amount"));
		String note=req.getParameter("notes");
		System.out.println(amt);
		String q="update mytable set total_open_amount=?,notes=? where key_0=?;";
		try {
			PreparedStatement ps=con.prepareStatement(q);
			ps.setFloat(1, amt);
			ps.setString(2,note);
			ps.setInt(3, key);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "{'msg':'updated'}";
	}
    
    
}
