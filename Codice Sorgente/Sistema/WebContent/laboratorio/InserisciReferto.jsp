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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<title>MyMedSystem | Inserimento referto</title>
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
				<li><a href="./homelaboratorio.jsp">Home</a></li>
				<li><a href="#">Inserimento Prestazione</a></li>
			</ul>
		</div>
		<div class="rigacentrata">
			<div class="contenitore inserimento">
				<form action="../CaricamentoControl" id="nuovoReferto" enctype="multipart/form-data" method="post">
					<div class="campiprestazione">
						<label for="codice">Codice Fiscale del paziente:</label>
						<input type="text" placeholder="Codice Fiscale" name="codice" required>
						<label for="codiceReferto">Codice Identificativo Referto</label>
						<input type="text" placeholder="Codice Referto" name="codiceReferto" >
						<label for="prestazione">Tipo referto:</label>
						<select name="prestazione" id="prestazione" required>
							<option value="vairus">Vairus</option>
							<option value="analisidelsangue">Analisi del sangue</option>
							<option value="geriatrica">Visita Geriatrica</option>
						</select>
						<label for="note">Note:</label>
						<textarea name="note" placeholder="Facoltativo..."></textarea>
						<label for="file">Seleziona un file:</label>
						<div class="input-group justify-content-center">
							<input type="file" name="file" accept="application/pdf" class="form-control" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload" required >
						</div>
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
	<script src="../js/InserimentoReferto.js"></script>
</body>
</html>