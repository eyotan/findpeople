<%@ page language="java" contentType="text/html; charset=UTF-8"	
                         pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
                      "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; UTF-8">
      <script src="lib/jquery-3.2.1.min.js"
		type="text/javascript"></script>
	  <script src="lib/js.cookie.js" type="text/javascript"></script>
	  <script src="js/searchman.js" type="text/javascript"></script>
      <script src="js/userlogout.js" type="text/javascript"></script>
      <link type="text/css" rel="stylesheet" href="css/styleFindpeople.css" />
      <title>Поиск человека в БД</title>
   </head>
   <body>
   
	<div class="loggedInUser">
		<label for="loggedIn">Зарегистрирован как:</label>
		<label id="loggedIn"></label>
		<br>
		<input type="button" id="logOut" value="Выход" />
	</div>
	
	<h1>Поиск человека в БД</h1> 	
			
	<div class="searchFormFioBox">
	<form method="post" id="searchFormFio">  
		
		<label for="surName">Фамилия:</label>
		<input type="text" id="surName" />
	
		<label for="userName1">Имя:</label>
		<input type="text" id="userName1" />
	
		<label for="userName2">Отчество:</label>
		<input type="text" id="userName2" />
		
		<label for="cityName">Город:</label>
		<input type="text" id="cityName" />
	
		<label for="autoName">Автомобиль:</label>
		<input type="text" id="autoName" />
 		
	</form>
	</div>
	
	<div class="searchUserButton">
	<input type="button" id="searchUser" value="Поиск" />
	</div>
	
	<div class="resultSearchTable">
	<table cellspacing = "0" id = "resultSearchTable">
		<tr>
			<th scope = "col">Фамилия</th>
			<th scope = "col">Имя</th>
			<th scope = "col">Отчество</th>
			<th scope = "col">Город</th>
			<th scope = "col">Авто</th>
		</tr>
	</table>
   </div>
   </body>
</html>