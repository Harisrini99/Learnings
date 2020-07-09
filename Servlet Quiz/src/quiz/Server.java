package quiz;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.OnClose;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.json.simple.JSONObject;

public class Server extends HttpServlet 
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		ArrayList<Object> d = null;
		try 
		{
			d = new ArrayList<>();
			Connection con = null;
			Class.forName("com.mysql.jdbc.Driver"); 
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/quiz","root",""); 
			String query = " insert into 11th_book (Question,A,B,C,D,Answer)"+ " values (?, ?, ?, ?, ?,?)";
			Statement stmt  = con.createStatement();
			String sql = "SELECT * FROM 11th_book";
			ResultSet rs    = stmt.executeQuery(sql);
			int i =1;
			while (rs.next()) 
			{
				JSONObject o = new JSONObject();
				o.put("Question",rs.getObject("Question"));
				o.put("A",rs.getObject("A"));
				o.put("B",rs.getObject("B"));
				o.put("C",rs.getObject("C"));
				o.put("D",rs.getObject("D"));
				o.put("Answer",rs.getObject("Answer"));
				d.add(o);
				i++;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		PrintWriter printWriter = res.getWriter();
		printWriter.print(d);
 	}

}
