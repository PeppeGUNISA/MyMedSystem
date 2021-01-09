$(document).ready(function() {
	$.post('../RefertiPazienteControl', { "tipo": "paziente" },
		function(resp) { // on sucess
			// We need 2 methods here due to the different ways of 
			// handling a JSON object.
			printReferti(resp);
		})
		.fail(function() { // on failure
			alert("Request failed.");
		});
})

function printReferti(json) {
	$("#campireferti").empty()
	if (!json.empty) {
		$.each(json, function(i, refertoObject) {
			var referto;
			referto = "<tr><td scope='row' data-label=''><a href='../RefertoControl?id=" + refertoObject.id + "'<i class='las la-download la-3x'></i></td><td data-label='Referto'>" + refertoObject.prestazione + "</td><td data-label='Codice'>" + refertoObject.id + "</td><td data-label='Paziente'>" + refertoObject.laboratorio + "</td><td data-label='Data'>" + refertoObject.dataInserimento + "</td></tr>";
			$("#campireferti").append(referto);
		});
	}
	$("#campireferti").append("</tbody>");
};