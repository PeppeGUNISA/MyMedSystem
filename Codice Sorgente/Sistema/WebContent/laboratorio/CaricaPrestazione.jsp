<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.entity.Laboratorio" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="MyMedSystem">
<meta name="author" content="">
<meta name="generator" content="">
<meta name="robots" content="noindex">
<link href="../css/mymedsystem.css" rel="stylesheet"
	type="text/css">
<title>MyMedSystem | Inserimento prestazione</title>
</head>
<body>
	<% Laboratorio paziente = (Laboratorio) session.getAttribute("utente"); %>
	<header>
		<h1>MyMed System</h1>
		<img alt="" src="../imgs/iconmonstr-user-circle-thin.svg"
			onclick="openNav()">
		<div id="sidebar" class="sidebar">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			<a href="#">Profilo Laboratorio</a> <a href="../LogoutControl">Logout</a>
		</div>
	</header>
	<div class="corpo">
		<div class="primariga riga">
			<ul class="breadcrumb">
				<li><a href="#">Home</a></li>
				<li><a href="#">Inserimento Prestazione</a></li>
			</ul>
		</div>
		<div class="rigacentrata">
			<div class="contenitore inserimento">
				<form action="../PrestazioneControl" id="nuovaprestazione">
					<div>
						<label for="prestazione">Prestazione:</label>
						<select name="prestazione" id="prestazione" required>
							<option selected="selected" value="vairus">Vairus</option>
							<option value="analisidelsangue">Analisi del sangue</option>
							<option value="geriatrica">Visita Geriatrica</option>
						</select>
					</div>
					<div class="campiprestazione">
						<input type="text" placeholder="Codice" name="codice">
						<input type="text" placeholder="Prezzo" name="prezzo">
					</div>
					<div>
						<button type="submit" class=conferma>Conferma</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script src="../js/jquery.js"></script>
	<script src="../js/laboratorio.js"></script>
	<script src="../js/caricaTuttePrestazioni.js"></script>
</body>
</html>