<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="MyMedSystem">
	<meta name="author" content="">
	<meta name="generator" content="">
	<meta name="robots" content="noindex">
	<link href="./css/mymedsystem.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
	<title>MyMedSystem | Home</title>
</head>

<body>
	<header>
		<h1>MyMed System</h1>
	</header>
	<div class="corpo">
		<div class="riga">
			<div class="hidden welcome contenitore">
				<h2>Benvenuto!</h2>
				<img src="./imgs/logo_notitle.png">
			</div>
				<div class="contenitore login">
				<form action="LoginControl" id="login" method="post">
					<input type="text" placeholder="Username" name="username" required>
					<input type="password" placeholder="Password" name="password" required>
					<a href="#">Password dimenticata?</a>
					<button type="submit" class="loginbtn">Login</button>
					<p>Sei nuovo su MyMedSystem?</p>
					<button type="button" class="conferma">Carica</button>
				</form>
			</div>
		</div>
	</div>
		 <%@include file="footer.html" %>
</body>

</html>
