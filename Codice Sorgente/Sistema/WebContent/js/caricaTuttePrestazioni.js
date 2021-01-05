/**
 * 
 */

$(document).ready(function() {
	$.post('../PrestazioniControl', { "tipo" : "tutte"},
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
		prestazione = "<option value='"+ prestazioneObject.id + "'>" + prestazioneObject.descrizione + "</option>";
		$("#prestazione").append(prestazione);
	});
};