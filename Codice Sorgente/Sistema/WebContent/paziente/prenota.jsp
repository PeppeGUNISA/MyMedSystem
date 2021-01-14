<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="MyMedSystem">
	<meta name="author" content="">
	<meta name="generator" content="">
	<meta name="robots" content="noindex">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link href="../css/mymedsystem.css" rel="stylesheet" type="text/css">
	<title>MyMedSystem | Prenota</title>
</head>
<body>
	<header>
		<h1>MyMed System</h1>
		<img alt="" src="../imgs/iconmonstr-user-circle-thin.svg" onclick="openNav()">
		<div id="sidebar" class="sidebar">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			<a href="#">Profilo Paziente</a>
			<a href="../LogoutControl">Logout</a>
		</div>
	</header>
	<div class="corpo">
		<div class="primariga riga">
			<ul class="breadcrumb">
				<li><a href="#">Home</a></li>
				<li><a href="./homepaziente.jsp">Nuova Prenotazione</a></li>
			</ul>
		</div>
		<div class="riga">
			<form action="#" id="nuovaprenotazione">
				<div class="contenitore">
					<p>
						<label for="prestazione">Seleziona tipologia prestazione:</label>
						<select name="prestazione" id="prestazione" onchange="caricaProvince(this);">
							<option selected="selected" value="vairus">Vairus</option>
							<option value="analisidelsangue">Analisi del sangue</option>
							<option value="geriatrica">Visita Geriatrica</option>
						</select>
					</p>
					<p>
						<label for="provincia">Seleziona provincia:</label>
						<select name="provincia" id="provincia" onclick="caricaLaboratori(this);">
							<option selected="selected" value="avellino">Avellino</option>
							<option value="benevento">Benevento</option>
							<option value="caserta">Caserta</option>
							<option value="napoli">Napoli</option>
							<option value="salerno">Salerno</option>
						</select>
					</p>
					<p>
						<label for="distretto">Seleziona distretto sanitario:</label>
						<select name="distretto" id="distretto" onclick="caricaDate(this)">
							<option selected="selected" value="avellino">Avellino</option>
							<option value="benevento">Benevento</option>
							<option value="caserta">Caserta</option>
							<option value="napoli">Napoli</option>
							<option value="salerno">Salerno</option>
						</select>
					</p>
				</div>
				<div class="contenitore">
					<div id="calendario">
						<div id="datepicker"></div>
					</div>
				</div>
				<div class="contenitore fascia">
					<label>Fascia oraria:</label><br><br>
					<div id="radiofascia">
						<input type="radio" name="orario" id="otto" value="otto">
						<label for="otto">8:00 - 9:00</label><br>
						<input type="radio" name="orario" id="nove" value="nove">
						<label for="nove">9:00 - 10:00</label><br>
						<input type="radio" name="orario" id="dieci" value="dieci">
						<label for="dieci">10:00 - 11:00</label><br>
						<input type="radio" name="orario" id="undici" value="undici">
						<label for="undici">11:00 - 12:00</label><br>
					</div>
				</div>
			</form>
		</div>
		<div class="riga">
			<button class="conferma">Conferma</button>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="../js/paziente.js"></script>
	<script src="../js/prenotazioniPaziente.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment-with-locales.min.js"></script>

</body>
</html>