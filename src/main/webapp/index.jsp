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
      <script src="js/userauth.js" type="text/javascript"></script>
      <link type="text/css" rel="stylesheet" href="css/styleindex.css" />
      <link rel="icon" type="image/png" href="favicon.png">
      <title>Авторизация</title>
   </head>
   
   <body>
   <h1>Авторизация</h1>
	
	<div class="loginFormBox">    
    	<form method="post" id="loginForm">                            
		
		<label for="userLogin">Логин:</label>
		<input type="text" id="userLogin" />
	
		<label for="userPassword">Пароль:</label>
		<input type="password" id="userPassword" />
			
	</form>
	</div>
	
	<div class="loginButton">
		<input type="button" id="enterUser" value="Вход" />
		
		<label for="userCheckbox">Оставаться в системе:</label>
		<input type="checkbox" id="userCheckbox" />
			
	</div>	
	        
   </body>
</html>