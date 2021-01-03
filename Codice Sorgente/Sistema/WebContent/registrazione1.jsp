<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="MyMedSystem">
<meta name="author" content="">
<meta name="generator" content="">
<meta name="robots" content="noindex">
<link href="./css/mymedsystem.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
<title>Registrazione MyMedSystem</title>
</head>
<body>
	<div class="corpo">
		<div class="rigacentrata">
			<div class="hidden welcome contenitore">
				<h2>Registrazione</h2>
				<img src="./imgs/logo_notitle.png">
			</div>
			<div class="contenitore registrazione">
				<form action="FirstFormControl"
					onsubmit="event.preventDefault(); validateFirstForm(this)" method="post">
					<div class="row">
						<div>
							<label for="email">Codice Fiscale</label>
						</div>
						<div>
							<input type="text" id="cf" name="cf"
								placeholder="Il tuo Codice Fiscale..." required>
						</div>
						<div>
							<label for="username">Username</label>
						</div>
						<div>
							<input type="text" id="username" name="username"
								placeholder="Il tuo username..." required>
						</div>
						<div>
							<label for="password">Password (deve contenere tra 8 e
								128 caratteri)</label>
						</div>
						<div>
							<input type="password" id="password" name="password"
								placeholder="..." required>
						</div>
						<div>
							<label for="confirmpassword">Conferma Password</label>
						</div>
						<div>
							<input type="password" id="confirmpassword"
								name="confirmpassword" placeholder="..." required>
						</div>
					</div>
					<div class="row">
						<input type="submit" value="Prosegui">
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script src="./js/signup.js"></script>

</body>
</html>