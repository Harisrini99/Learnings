<%@page import="java.util.Random"%>
<%@page import="java.sql.Statement"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="error.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QUIZ | Play Quiz</title>
<meta name="viewport"
	content="user-scalable=no, width=device-width, initial-scale=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes" />

<style>
#Question
{
  font-size:16px;
  font-weight: bold;
   
}
#Answer {
	color: green;
}

#scoreContainer {
	visibility: hidden;
}

.options {
	border: 0px white;
	box-shadow: 0 0 5px black;
	border-radius: 5px;
	padding: 5px;
	margin: 10px;
	max-width: 200px;
	padding: 10px;
}
</style>

</head>

<body>

	<%
		String subject = request.getParameter("subject");
		ArrayList<Object> d = null;
		d = new ArrayList();
		Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/quiz", "root", "");
		String query = " insert into 11th_book (Question,A,B,C,D,Answer)" + " values (?, ?, ?, ?, ?,?)";
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM " + subject;
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			JSONObject o = new JSONObject();
			o.put("Question", rs.getObject("Question"));
			o.put("A", rs.getObject("A"));
			o.put("B", rs.getObject("B"));
			o.put("C", rs.getObject("C"));
			o.put("D", rs.getObject("D"));
			o.put("Answer", rs.getObject("Answer"));
			d.add(o);
		}
	%>

	<%
		Random rand = new Random();
		ArrayList<Object> data = new ArrayList();
		for (int i = 0; i < 10; i++) {
			data.add(d.get(rand.nextInt(d.size())));
		}
	%>

	<div id="container">
		<pre id="Question"></pre>
		<div class="options">
			<input type="radio" id="A" name="option" onclick="check()" /> <label
				for="A"></label>
		</div>

		<div class="options">
			<input type="radio" id="B" name="option" onclick="check()" /> <label
				for="B"></label>
		</div>

		<div class="options">
			<input type="radio" id="C" name="option" onclick="check()" /> <label
				for="C"></label>
		</div>

		<div class="options">
			<input type="radio" id="D" name="option" onclick="check()" /> <label
				for="D"></label>
		</div>

		<p id="Answer"></p>
		<button onclick="next()">Next Question</button>
	</div>

	<div id="scoreContainer">
		<h1>Quiz Finished</h1>
		<p id="score"></p>
	</div>

</body>


<script>
let count= -1;
let score = 0;
count++;
let dataFromDB = <%=data%>;
document.getElementById("Question").innerHTML = dataFromDB[count].Question;
let radio = document.getElementsByTagName("label")
radio[0].innerHTML = dataFromDB[count].A;
radio[1].innerHTML = dataFromDB[count].B;
radio[2].innerHTML = dataFromDB[count].C;
radio[3].innerHTML = dataFromDB[count].D;


let next = () => {
	clearSelection();
	if(count < dataFromDB.length-1)
		count++;
	else
	{
		displayScore(score);	
	}
	document.getElementById("Question").innerHTML = dataFromDB[count].Question;
	let radio = document.getElementsByTagName("label")
	radio[0].innerHTML = dataFromDB[count].A;
	radio[1].innerHTML = dataFromDB[count].B;
	radio[2].innerHTML = dataFromDB[count].C;
	radio[3].innerHTML = dataFromDB[count].D;
};

let check = () =>{
	let l = document.getElementsByTagName("label");
	let div = document.getElementsByClassName("options");
	let radio = document.getElementsByTagName("input");
	for(let i=0;i<radio.length;i++)
	{
		document.getElementById("Answer").innerHTML = "Answer is 	"+dataFromDB[count].Answer;
		if(radio[i].checked)
		{
			if(dataFromDB[count].Answer == l[i].innerHTML)
			{
				div[i].style.backgroundColor ="#90ee90";
				score++;
			}
			else
				div[i].style.backgroundColor ="#ffcccb";
				
		}
		if(dataFromDB[count].Answer == l[i].innerHTML)
			div[i].style.backgroundColor ="#90ee90";
		
	}
};

let clearSelection = () =>{
	document.getElementById("Answer").innerHTML = "";
	let l = document.getElementsByTagName("label");
	let div = document.getElementsByClassName("options")
	let r = document.getElementsByTagName("input");
	for(let i=0;i<r.length;i++)
	{
		r[i].checked = false;
		div[i].style.backgroundColor ="transparent";
	}
	
};

let displayScore = score => {
	
	document.getElementById("container").style.display = "none";
	document.getElementById("scoreContainer").style.visibility = "visible";	
	document.getElementById("score").innerHTML = "Your Score is "+score+"/10"
	
};




</script>


</html>