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
	<link href="../css/mymedsystem.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
	<title>MyMedSystem | Home</title>
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
			</ul>
		</div>
		<div class="riga">
			<div class="contenitore">
				<div class="tab">
					<button class="tablinks" onclick="openTab(event, 'Prestazioni')">Prestazioni</button>
					<button class="tablinks" onclick="openTab(event, 'Referti')">Referti</button>
				</div>

				<!-- Tab content -->
				<div id="Prestazioni" class="tabcontent">
					<div>
						<a href="./CaricaPrestazione.jsp">
							<img alt="" src="../imgs/add-button-svgrepo-com.svg">
							<span>Nuova prestazione</span>
						</a>
					</div>
					<div>
						<h2>Elenco prestazioni</h2>
					</div>
					<div class="ricerca">
						<input type="text" placeholder="&#x1F50D; Ricerca" >
						<div class="radiogroup">
							<input type="radio" id="campo1">
							<label for="campo1">campo1</label>
							<input type="radio" id="campo2">
							<label for="campo2">campo1</label>
						</div>
					</div>
					<div class="tabella">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Prestazione</th>
									<th scope="col">Codice</th>
									<th scope="col">Costo</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody id="campiprestazioni">
								<tr>
									<td scope="row" data-label="">@</td>
									<td data-label="Prestazione">Emocromo</td>
									<td data-label="Codice">EMC</td>
									<td data-label="Costo">25.00</td>
								</tr>
							</tbody>
							<tfoot>
						</table>
					</div>
				</div>
				<div id="Referti" class="tabcontent">
					<div>
						<a href="./InserisciReferto.jsp">
							<img alt="" src="../imgs/add-button-svgrepo-com.svg">
							<span>Nuovo referto</span>
						</a>
					</div>
					<div>
						<h2>Elenco referti</h2>
					</div>
					<div class="ricerca">
						<input type="text" placeholder="&#x1F50D; Ricerca" >
						<div class="radiogroup">
							<input type="radio" id="campo1">
							<label for="campo1">campo1</label>
							<input type="radio" id="campo2">
							<label for="campo2">campo1</label>
						</div>
					</div>
					<div class="tabella">
						<table class="table">
							<thead>
								<tr>
									<th scope="col"></th>
									<th scope="col">Referto</th>
									<th scope="col">Codice</th>
									<th scope="col">Paziente</th>
									<th scope="col">Data</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody id="campireferti">
								<tr>
									<td scope="row" data-label="Referto">Visita all'occhio</td>
									<td data-label="Codice">AAACERCASI</td>
									<td data-label="Paziente">Peppe</td>
									<td data-label="Data">05/02/2021</td>
									<td data-label="">@</td>
									<td data-label="">@</td>
								</tr>
							</tbody>
							<tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="../js/jquery.js"></script>
	<script src="../js/laboratorio.js"></script>
	<script src="../js/caricaPrestazioniHome.js"></script>
</body>
</html>