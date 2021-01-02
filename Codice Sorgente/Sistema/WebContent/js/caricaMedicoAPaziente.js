/**
 * 
 */

$(document).ready(function() {
	$.post('MedicoControl', { "type" : "medico"},
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
	var medico = json.medico;
	if (medico != null) {
			$("#schedamed").empty().append("Dati" + medico.cose); //TODO: dati del medico
	}
};