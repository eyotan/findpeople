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
      <title>Поиск человека в БД</title>
   </head>
   <body>
                                
	<label>Поиск человека в БД</label>
	<label for="loggedIn">Зарегистрирован как:</label>
	<label id="loggedIn"></label>
	
	<br>
	<form method="post" id="searchFormFio">  
		
		<label for="surName">Фамилия:</label>
		<input type="text" id="surName" />
	
	<br>
		<label for="userName1">Имя:</label>
		<input type="text" id="userName1" />
	
	<br>
		<label for="userName2">Отчество:</label>
		<input type="text" id="userName2" />
		
	<br>
		<label for="cityName">Город:</label>
		<input type="text" id="cityName" />
		
	<br>
		<label for="autoName">Автомобиль:</label>
		<input type="text" id="autoName" />
	</form>
	
		<input type="button" id="searchUser" value="Поиск" />
		<input type="button" id="logOut" value="Выход" />
	<br>
	<br>

	<strong>Результат поиска</strong>:
	
	<br>
	<div id="resultSearch"></div>
	<table cellspacing = "0" id = "resultSearchTable">
		<tr>
			<th scope = "col">Имя</th>
			<th scope = "col">Фамилия</th>
			<th scope = "col">Отчество</th>
			<th scope = "col">Город</th>
			<th scope = "col">Авто</th>
		</tr>
	</table>
	                        
   
   </body>
</html>