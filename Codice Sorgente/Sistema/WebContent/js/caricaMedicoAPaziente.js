/**
 * 
 */

$(document).ready(function() {
	$.post('../MedicoControl', { "type": "medico" },
		function(resp) { // on sucess
			// We need 2 methods here due to the different ways of 
			// handling a JSON object.
			printMedico(resp);
		})
		.fail(function() { // on failure
			alert("Request failed.");
		});
})

function printMedico(json) {
	var medico = json;

	if (medico != null) {
		$("#schedamed").empty().append("<legend>Scheda medico</legend>" +
		"<div>Medico:</div>" +
		"<div class='medico'>Dott. " + medico.denominazione + "</div>" +
		"<div>Indirizzo:</div>" + 
		"<div class='indirizzo'>" + medico.indirizzo + "</div>" + 
		"<div>Città:</div>" +
		"<div class=citta>" + medico.citta + "</div>" +
		"<div>Telefono:</div>" + 
		"<div class='telefono'>" + medico.telefono + "</div>"); //TODO: dati del medico
	}
};