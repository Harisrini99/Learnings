<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QUIZ | Index Page</title>
</head>
<body>
	<center>
	<form action="startQuiz.jsp" method="post">
		<h1>Welcome To Quiz</h1>
		<p>Select The Subject To Start Quiz</p>
		<select name="subject">
			<option>Geography</option>
			<option>History</option>
		</select> <br></br> <input type="submit" value="Start Quiz" />
		</form>
	</center>
</body>
</html>