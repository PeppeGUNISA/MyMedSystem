$(document).ready(function() {
	$.post('../PrenotazioneControl', { "tipo": "tutte" },
		function(resp) { // on sucess
			// We need 2 methods here due to the different ways of 
			// handling a JSON object.
			printPrestazioni(resp);
		})
		.fail(function() { // on failure
			alert("Request failed.");
		});
})

function printPrestazioni(json) {
	$("#prestazione").empty()

	$.each(json, function(i, prestazioneObject) {
		var prestazione;
		prestazione = "<option value='" + prestazioneObject.id + "'>" + prestazioneObject.id + " - " + prestazioneObject.descrizione + "</option>";
		$("#prestazione").append(prestazione);
	});
};

function caricaProvince(selected) {
	$.post('../ProvinceControl', { "prestazione": selected.value },
		function(resp) { // on sucess
			// We need 2 methods here due to the different ways of 
			// handling a JSON object.
			printProvince(resp);
		})
		.fail(function() { // on failure
			alert("Request failed.");
		});
}

function printProvince(json) {
	$("#provincia").empty()

	$.each(json, function(i, provinciaObject) {
		var provincia;
		provincia = "<option value='" + provinciaObject + "'>" + provinciaObject + "</option>";
		$("#provincia").append(provincia);
	});
};

function caricaLaboratori(selected) {
	$.post('../DistrettoControl', { "prestazione": $("#prestazione").val(), "provincia": "Caserta" },
		function(resp) { // on sucess
			// We need 2 methods here due to the different ways of 
			// handling a JSON object.
			printLaboratori(resp);
		})
		.fail(function() { // on failure
			alert("Request failed.");
		});
}

function printLaboratori(json) {
	$("#distretto").empty()

	$.each(json, function(i, laboratorioObject) {
		var laboratorio;
		laboratorio = "<option value='" + laboratorioObject.username + "'>" + laboratorioObject.citta + " - " + laboratorioObject.denominazione + "</option>";
		$("#distretto").append(laboratorio);
	});
};

function caricaDate(selected) {
	$.post('../DataControl', { "username": $("#distretto").val() },
		function(resp) { // on sucess
			// We need 2 methods here due to the different ways of 
			// handling a JSON object.
			editDateAndOrari(resp);
		})
		.fail(function() { // on failure
			alert("Request failed.");
		});
}

function editDateAndOrari(json) {
	var giorni = "";
	$("#datepicker").datepicker("destroy");

	$.each(json, function(i, giornoObject) {
		giorni = giorni + " day != " + giornoObject;
	});
	$("#datepicker").datepicker({
		beforeShowDay: function(date) {
			var day = date.getDay();
			return [(json.includes(day)), ''];
		},
		minDate: new Date(),
		onSelect: function() {
			$.post('../OrarioControl', { "username": $("#distretto").val() },
				function(resp) { // on sucess
					// We need 2 methods here due to the different ways of 
					// handling a JSON object.
					printOrari(resp);
				})
				.fail(function() { // on failure
					alert("Request failed.");
				});
		}
	}
	);
	$("#datepicker").datepicker("refresh");
};

function printOrari(json) {
	$("#radiofascia").empty()

	$.each(json, function(i, orarioObject) {
		var orario;
		const locale = 'en'; // or whatever you want...
		const hours = [];

		orarioApertura = moment(json.apertura, "H").format("H");
		orarioChiusura = moment(json.chiusura, "H").format("H");
		console.log(orarioChiusura);
		console.log(orarioApertura);

		for (let hour = orarioApertura; hour < moment(json.chiusura, "H").format("H"); hour++) {
			// hours.push(moment({ hour }).format('h:mm A'));
			hours.push(
				moment({
					hour,
					minute: 30
				}).format('h:mm A')
			);
		}
		console.log(hours);
		for (var i = 0; i < orarioObject.chiusura - orarioObject.apertura; i++) {
			orario = "<input type='radio' name='orario' id='" + (orarioObject.apertura + i) + "' value='otto'>"
			$("#radiofascia").append(orario);
		}
	});
}
