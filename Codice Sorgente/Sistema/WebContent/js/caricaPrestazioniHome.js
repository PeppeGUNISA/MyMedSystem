/**
 * 
 */

$(document).ready(function() {
	$.post('../PrestazioniControl', { "tipo" : "laboratorio"},
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
	$("#campiprestazioni").empty()
		
	$.each(json, function(i, prestazioneObject) {
		var prestazione;
		prestazione = "<tr><td scope='row' data-label='Prestazione'>" + prestazioneObject.descrizione + "</td><td data-label='Codice'>" + prestazioneObject.id + "</td><td data-label='Costo'>" + prestazioneObject.prezzo + "</td><td data-label='trash'><a href='../DeleteControl?prestazione=" + prestazioneObject.id + "'><i class='las la-trash-alt la-2x'></i></a></td></tr>";
		$("#campiprestazioni").append(prestazione);
	});
	$("#campiprestazioni").append("</tbody>");
};