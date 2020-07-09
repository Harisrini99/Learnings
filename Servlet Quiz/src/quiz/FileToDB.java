package quiz;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.Iterator;

public class FileToDB {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\WANTED\\Desktop/11th_book.json"));
 			JSONObject jsonObject = (JSONObject) obj;
			Connection con = null;
 			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/quiz","root",""); 
				
 			for(int i=0;i<jsonObject.size();i++)
 			{
 				JSONObject object =(JSONObject) jsonObject.get(Integer.toString(i+1));
 				 String query = " insert into 11th_book (Question,A,B,C,D,Answer)"+ " values (?, ?, ?, ?, ?,?)";
 				 System.out.print(query);
 				
					try
	 				{  
						PreparedStatement preparedStmt = con.prepareStatement(query);
			 		      preparedStmt.setString (1, object.get("Question").toString());
			 		      preparedStmt.setString (2, object.get("A").toString());
			 		      preparedStmt.setString (3, object.get("B").toString());
			 		      preparedStmt.setString (4, object.get("C").toString());
			 		      preparedStmt.setString (5, object.get("D").toString());
			 		      preparedStmt.setString (6, object.get("Answer").toString());
						
	 					 
			 		     preparedStmt.execute();
	 					}
	 				catch(Exception e)
	 					{ 
	 					System.out.println(e);
	 					}  
	 				}
 			}
			catch (Exception e)
			{
			//e.printStackTrace();
			}
		}
	}
