/**
 * 
 */
function checkUsername(inputtxt) {
	var userregexp = /^[a-zA-Z0-9]*$/;
	if(inputtxt.value.match(userregexp)) {
		console.log("Username ok");  
		return true
	} else
		return false;	
}

function checkEmail(inputtxt) {
	var email = /^[A-Z0-9_!#$%&'*+//=?`{|}~^.-]+@[A-Z0-9.-]+$/;
	if(inputtxt.value.match(email)) {
		console.log("Mail ok")
		return true;
	} else	
		return false;
}

function checkPasswordFormat(inputtxt) {
	var number = /\d/;
	var letter = /[A-Za-z]/;
	if(inputtxt.value.match(number) && inputtxt.value.match(number)) {
		console.log("Password ok");
		return true;
	} else
		return false;
}

function checkConfirmationPassword(inputtxt1, inputtxt2) {
	if(inputtxt1 === inputtxt2) {
		console.log("Password uguali");
		return true;
	} else
		return false;
}

function checkCF(inputtxt) {
	var cf = /^[a-zA-Z0-9]*$/;
	if(inputtxt.value.match(cf) && inputtxt.value.length == 16) {
		console.log("Cf ok");  
		return true
	} else
		return false;	
}

function checkName(inputtxt) {
	var name = /^[A-Za-z- ]+$/;
	if(inputtxt.value.match(name)) {
		console.log("Nome ok");  
		return true;
	} else
		return false;	
}

function checkSurname(inputtxt) {
	var surname = /^[A-Za-z- ]+$/;
	if(inputtxt.value.match(surname)) {
		console.log("Cognome ok");  
		return true;
	} else
		return false;	
}

function checkLuogoNascita(inputtxt) {
	var luogo = /^[A-Za-z- ]+$/;
	if(inputtxt.value.match(luogo)) {
		console.log("Luogo ok");  
		return true;
	} else
		return false;	
}

function checkDataNascita(data) {
	var oggi = new Date();
	if(data.value.valueOf() < oggi.valueOf()) {
		console.log("Data ok");
		return true;
	} else
		return false;
}

function checkComune(inputtxt) {
	var comune = /^[A-Za-z- ]+$/;
	if(inputtxt.value.match(comune)) {
		console.log("Comune ok");  
		return true;
	} else
		return false;	
}

function checkIndirizzo(inputtxt) {
	var indirizzo = /^[a-zA-Z0-9- ]*$/;
	if(inputtxt.value.match(indirizzo)) {
		console.log("Indirizzo ok");  
		return true;
	} else
		return false;	
}

function checkCap(inputtxt) {
	var cap = /^[0-9]\+$/;
	if(inputtxt.value.match(cap) && inputtxt.value.length === 5) {
		console.log("Indirizzo ok");  
		return true;
	} else
		return false;	
}

function checkCellulare(inputtxt) {
	var cap = /^[0-9]\+$/;
	if(inputtxt.value.match(cap) && inputtxt.value.length === 5) {
		console.log("Indirizzo ok");  
		return true;
	} else
		return false;	
}

function validateFirstForm(obj) {	
	var valid = true;	
	
	var name = document.getElementsByName("username")[0];
	if(!checkUsername(username)) {
		valid = false;
		name.classList.add("error");
	} else {
		name.classList.remove("error");
	}
	
	var email = document.getElementsByName("email")[0];
	if(!checkEmail(email)) {
		valid = false;
		email.classList.add("error");
	} else {
		email.classList.remove("error");
	}
	
	var password = document.getElementsByName("password")[0];
	var confirmpassword = document.getElementsByName("confirmpassword")[0];
	
	if(!checkPasswordFormat(password)) {
		valid = false;
		email.classList.add("error");
	} else {
		email.classList.remove("error");
	}
	
	if(!checkConfirmationPassword(password.value, confirmpassword.value)) {
		valid = false;
		password.classList.add("error");
		confirmpassword.classList.add("error");
	} else {
		password.classList.remove("error");
		confirmpassword.classList.remove("error");
	}
	console.log(valid);
		
	if(valid) obj.submit();
}

function validateSecondForm(obj) {	
	var valid = true;	
	
	var name = document.getElementsByName("username")[0];
	if(!checkUsername(username)) {
		valid = false;
		name.classList.add("error");
	} else {
		name.classList.remove("error");
	}
	
	var email = document.getElementsByName("email")[0];
	if(!checkEmail(email)) {
		valid = false;
		email.classList.add("error");
	} else {
		email.classList.remove("error");
	}
	
	var password = document.getElementsByName("password")[0];
	var confirmpassword = document.getElementsByName("confirmpassword")[0];
	
	if(!checkPasswordFormat(password)) {
		valid = false;
		email.classList.add("error");
	} else {
		email.classList.remove("error");
	}
	
	if(!checkConfirmationPassword(password.value, confirmpassword.value)) {
		valid = false;
		password.classList.add("error");
		confirmpassword.classList.add("error");
	} else {
		password.classList.remove("error");
		confirmpassword.classList.remove("error");
	}
	console.log(valid);
		
	if(valid) obj.submit();
}