package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.json.simple.JSONObject;

@ManagedBean(name="quizbean")
@SessionScoped
public class QuizBean {

	private String subject;
	private List<String> subjectList;
	public List<Object> questions;
	public int count;
	public String selected;
	public int score;
	
	

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public int getCount() {
		return count;
	}

	public QuizBean() {
		subjectList = new ArrayList<>();
		questions = new ArrayList<>();
		score=0;
		getTables();
	}

	public int getScore() {
		return score;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
		getquestionsFromDB();
	}

	private void getTables() {
		try {
			Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/quiz", "root", "");
			Statement stmt = con.createStatement();
			String sql = "Show Tables;";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				subjectList.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getquestionsFromDB()
	{
		try 
		{
			ArrayList<Object> q = new ArrayList<>();
			Connection con = null;
			Class.forName("com.mysql.jdbc.Driver"); 
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/quiz","root",""); 
			Statement stmt  = con.createStatement();
			String sql = "SELECT * FROM " + subject;
			ResultSet rs    = stmt.executeQuery(sql);
			while (rs.next()) 
			{
				JSONObject o = new JSONObject();
				o.put("Question",rs.getObject("Question"));
				o.put("A",rs.getObject("A"));
				o.put("B",rs.getObject("B"));
				o.put("C",rs.getObject("C"));
				o.put("D",rs.getObject("D"));
				o.put("Answer",rs.getObject("Answer"));
				q.add(o);
			}
			Random rand = new Random();
			for (int i = 0; i < 10; i++) {
				questions.add(q.get(rand.nextInt(q.size())));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<String> getSubjectList()
	{
		return subjectList;
	}
	
	public List<Object> getquestions() {
		return questions;
	}
	
	public String increment()
	{
		JSONObject object = (JSONObject)questions.get(count);
		if(getSelected().equals(object.get("Answer").toString()))
		{
			score++;
		}
		
		if(count+1 < 10)
		{
			count++;
			return "welcome";
		}
		else
		{
			return "score";
		}
	}
	
}
