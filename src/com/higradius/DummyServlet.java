package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * Servlet implementation class dummyServlet
 */
@WebServlet("/dummyServlet")
public class DummyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     * 
     */
	//private JdbcConn db;
	Connection con=null;
    public DummyServlet() throws SQLException {
        super();
        con=new JdbcConn().connection();
        System.out.println(con);
          
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		 response.setContentType("text/html");
		 response.setCharacterEncoding("UTF-8");
		 int page=Integer.parseInt(request.getParameter("page"));
		 //out.print(resp.getName());
		 try {
			out.print(getData(con,page));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 out.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 PrintWriter out = response.getWriter();
		 response.setContentType("text/html");
		 response.setCharacterEncoding("UTF-8");
		 //out.print(resp.getName());
		 String s=addData(con,request);
		 RequestDispatcher rd=request.getRequestDispatcher("/index.html");
		rd.include(request, response);
	}

	private static List<String> getData(Connection con,int page) throws SQLException{
		List <InvoiceData> lid=new ArrayList<>();
		 Gson gson=new Gson();
	        Statement stmt = null;
			try {
				stmt = con.createStatement();
				int skip=10*(page-1);
	        String q="select key_0,cust_number,name_customer,clear_date,due_in_date,total_open_amount,invoice_id,notes from mytable ORDER BY key_0 DESC limit "+skip+","+10;
	        ResultSet rs = null;
				rs = stmt.executeQuery(q);
				while(rs.next())
				{
					InvoiceData id=new InvoiceData();
					id.setKey(rs.getInt(1));
					id.setCust_number(rs.getString(2));
					id.setName_customer(rs.getString(3));
					id.setClear_date(rs.getDate(4));
					id.setDue_in_date(rs.getDate(5));
					
					id.setTotal_open_amount(rs.getFloat(6));
					//System.out.println(rs.getInt(7));
					id.setInvoice_id(rs.getInt(7));
					
					
					id.setNote(rs.getString(8));
					lid.add(id);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<String> li=new ArrayList<>();
			for(InvoiceData i:lid) {
				 String g=gson.toJson(i);
				 li.add(g);
			 }
//			stmt.close();
//			con.close();
			return li;
	}
	
	private static String addData(Connection con,HttpServletRequest request) {
		System.out.println("inside add data");
		try {
			Statement st=con.createStatement();
			Random r=new Random();
			int key=(int) Math.floor(Math.random()*(20000-10001+1)+10001);
			System.out.println(key);
			String name=request.getParameter("cusname");
			String cnum=request.getParameter("cusno");
			float id=Float.parseFloat(request.getParameter("invoiceid"));
			float amt=Float.parseFloat(request.getParameter("amount"));
			//String due_date=new SimpleDateFormat("yyyy-MM-dd").parse();
			java.sql.Date d1=java.sql.Date.valueOf(request.getParameter("duedate"));
			System.out.println(d1);
			String note=request.getParameter("notes");
			String q="insert into mytable (key_0,cust_number,name_customer,due_in_date,total_open_amount,invoice_id,notes) values(?,?,?,?,?,?,?);";
			PreparedStatement p=con.prepareStatement(q);
			p.setInt(1, key);
			p.setString(2, cnum);
			p.setString(3, name);
			p.setDate(4, d1 );
			p.setFloat(5, amt);
			p.setFloat(6, id);
			p.setString(7, note);
			System.out.println(p.executeUpdate());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "{msg:'done'}";
	}

}
