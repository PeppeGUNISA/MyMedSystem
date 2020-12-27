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
				<form action="SecondFormControl"
					onsubmit="event.preventDefault(); validateSecondForm(this)" method="post">

					<div>
						<label for="nome">Nome</label>
					</div>
					<div>
						<input type="text" id="nome" name="nome" required>
					</div>
					<div>
						<label for="cognome">Cognome</label>
					</div>
					<div>
						<input type="text" id="cognome" name="cognome" required>
					</div>
					<div>
						<label for="nascita">Data di nascita</label>
					</div>
					<div>
						<input type="date" id="nascita" name="nascita" required>
					</div>
					<div>
						<label for="luogo">Luogo di nascita</label>
					</div>
					<div>
						<input type="text" id="luogo" name="luogo" required>
					</div>
					<div>
						<label for="cf">Codice Fiscale</label>
					</div>
					<div>
						<input type="text" id="cf" name="cf" required>
					</div>
					<div>
						<label for="sesso">Sesso</label>
					</div>
					<div>
						<input type="radio" name="sesso" id="maschio" value="maschio">
						<label for="maschio">Maschio</label>
						
						<span class="spaziovuoto">&nbsp</span>
						
						<input type="radio" name="sesso" id="femmina" value="femmina">
						<label for="femmina">Femmina</label>
					</div>
					<div>
						<label for="telefono">Telefono</label>
					</div>
					<div>
						<input type="text" id="telefono" name="telefono" required>
					</div>
					<div>
						<label for="provincia">Provincia di residenza</label>
					</div>
					<div>
						<select id="provincia" name="provincia">
							<option value="ag">Agrigento</option>
							<option value="al">Alessandria</option>
							<option value="an">Ancona</option>
							<option value="ao">Aosta</option>
							<option value="ar">Arezzo</option>
							<option value="ap">Ascoli Piceno</option>
							<option value="at">Asti</option>
							<option value="av">Avellino</option>
							<option value="ba">Bari</option>
							<option value="bt">Barletta-Andria-Trani</option>
							<option value="bl">Belluno</option>
							<option value="bn">Benevento</option>
							<option value="bg">Bergamo</option>
							<option value="bi">Biella</option>
							<option value="bo">Bologna</option>
							<option value="bz">Bolzano</option>
							<option value="bs">Brescia</option>
							<option value="br">Brindisi</option>
							<option value="ca">Cagliari</option>
							<option value="cl">Caltanissetta</option>
							<option value="cb">Campobasso</option>
							<option value="ci">Carbonia-iglesias</option>
							<option value="ce">Caserta</option>
							<option value="ct">Catania</option>
							<option value="cz">Catanzaro</option>
							<option value="ch">Chieti</option>
							<option value="co">Como</option>
							<option value="cs">Cosenza</option>
							<option value="cr">Cremona</option>
							<option value="kr">Crotone</option>
							<option value="cn">Cuneo</option>
							<option value="en">Enna</option>
							<option value="fm">Fermo</option>
							<option value="fe">Ferrara</option>
							<option value="fi">Firenze</option>
							<option value="fg">Foggia</option>
							<option value="fc">Forl&igrave;-Cesena</option>
							<option value="fr">Frosinone</option>
							<option value="ge">Genova</option>
							<option value="go">Gorizia</option>
							<option value="gr">Grosseto</option>
							<option value="im">Imperia</option>
							<option value="is">Isernia</option>
							<option value="sp">La spezia</option>
							<option value="aq">L'aquila</option>
							<option value="lt">Latina</option>
							<option value="le">Lecce</option>
							<option value="lc">Lecco</option>
							<option value="li">Livorno</option>
							<option value="lo">Lodi</option>
							<option value="lu">Lucca</option>
							<option value="mc">Macerata</option>
							<option value="mn">Mantova</option>
							<option value="ms">Massa-Carrara</option>
							<option value="mt">Matera</option>
							<option value="vs">Medio Campidano</option>
							<option value="me">Messina</option>
							<option value="mi">Milano</option>
							<option value="mo">Modena</option>
							<option value="mb">Monza e della Brianza</option>
							<option value="na">Napoli</option>
							<option value="no">Novara</option>
							<option value="nu">Nuoro</option>
							<option value="og">Ogliastra</option>
							<option value="ot">Olbia-Tempio</option>
							<option value="or">Oristano</option>
							<option value="pd">Padova</option>
							<option value="pa">Palermo</option>
							<option value="pr">Parma</option>
							<option value="pv">Pavia</option>
							<option value="pg">Perugia</option>
							<option value="pu">Pesaro e Urbino</option>
							<option value="pe">Pescara</option>
							<option value="pc">Piacenza</option>
							<option value="pi">Pisa</option>
							<option value="pt">Pistoia</option>
							<option value="pn">Pordenone</option>
							<option value="pz">Potenza</option>
							<option value="po">Prato</option>
							<option value="rg">Ragusa</option>
							<option value="ra">Ravenna</option>
							<option value="rc">Reggio di Calabria</option>
							<option value="re">Reggio nell'Emilia</option>
							<option value="ri">Rieti</option>
							<option value="rn">Rimini</option>
							<option value="rm">Roma</option>
							<option value="ro">Rovigo</option>
							<option value="sa">Salerno</option>
							<option value="ss">Sassari</option>
							<option value="sv">Savona</option>
							<option value="si">Siena</option>
							<option value="sr">Siracusa</option>
							<option value="so">Sondrio</option>
							<option value="ta">Taranto</option>
							<option value="te">Teramo</option>
							<option value="tr">Terni</option>
							<option value="to">Torino</option>
							<option value="tp">Trapani</option>
							<option value="tn">Trento</option>
							<option value="tv">Treviso</option>
							<option value="ts">Trieste</option>
							<option value="ud">Udine</option>
							<option value="va">Varese</option>
							<option value="ve">Venezia</option>
							<option value="vb">Verbano-Cusio-Ossola</option>
							<option value="vc">Vercelli</option>
							<option value="vr">Verona</option>
							<option value="vv">Vibo valentia</option>
							<option value="vi">Vicenza</option>
							<option value="vt">Viterbo</option>
						</select>
					</div>
					<div>
						<label for="comune">Comune di residenza</label>
					</div>
					<div>
						<input type="text" id="comune" name="comune" required>
					</div>
					<div>
						<label for="cap">Codice Postale</label>
					</div>
					<div>
						<input type="text" id="cap" name="cap" required>
					</div>
					<div>
						<label for="indirizzo">Indirizzo</label>
					</div>
					<div>
						<input type="text" id="indirizzo" name="indirizzo" required>
					</div>
					<div>
						<label for="cellulare">Cellulare</label>
					</div>
					<div>
						<input type="text" id="cellulare" name="cellulare" placeholder="Facoltativo">
					</div>
					<div><input type="submit" value="Registrati"></div>
				</form>
			</div>
		</div>
	</div>

	<script src="./js/signup.js"></script>
</body>
</html>