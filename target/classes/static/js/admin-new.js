$(function() {
	$.ajaxSetup({
		async: false
	})
	initPage()
})

const initPage = function() {
	$('form').submit(function(e) {
		if (!isItErrors()) {
			saveRoles()
			savePassword()
		} else {
			e.preventDefault()
		}
	})
	initEvents()
	$('#btn-test').click(function() {
		mailExists()
		usernameExists()
	})
}

const saveRoles = function() {

	$('.check-role').each(function() {
		var b = $(this).prop('checked')
		if (b === false) {
			$('input:hidden', $(this).parent()).remove()
		}
	})
}

const savePassword = function() {

	var pass = $('#password').val()
	$('#contrasena').val(pass)
}

const isItErrors = function() {

	var b = false

	let checkOwner = $('#check-role-owner').prop('checked')
	let checkAdmin = $('#check-role-admin').prop('checked')
	let checkCeo = $('#check-role-ceo').prop('checked')
	let checkAdminTr = $('#check-role-admintrainee').prop('checked')
	if (!checkAdmin && !checkOwner && !checkCeo && !checkAdminTr) {
		b = true
		fieldError('#roles-div', 'Debe seleccionar al menos un role administrativo')
	}

	let pass = $('#password').val()
	let pass2 = $('#password-repeat').val()

	if ($('#password').val().length < 4) {
		b = true
		fieldError('#password', 'La contrasena debe tener no menos de 4 caracteres')
	} else if (pass != pass2) {
		b = true
		fieldError('#password-repeat', 'Las contrasenas no coinciden')
	}
	if (isValidEmail($('#mail').val())) {
		b = true
		fieldError('#mail', 'Ingrese un correo electronico valido')
	}
	else if (mailExists()) {
		b = true
		fieldError('#mail', 'Este mail o telefono ya esta siendo usado por favor inserte otro')
	}
	if ($('#username').val().length == 0)  {
		b = true
		fieldError('#username', 'Inserte un nombre de usuario')
	} else if (usernameExists()) {
		b = true
		fieldError('#username', 'Este nombre de usuario ya esta existe por favor inserte otro')
	}

	return b
}

const usernameExists = function() {

	var username = $('#username').val()
	$.getJSON("http://localhost:8080/api/user/check/name/" + username, function(exists) {
		return exists
	})
}

const mailExists = function() {

	var mail = $('#mail').val()
	$.getJSON('http://localhost:8080/api/user/check/mail/' + mail, function(exists) {
		return exists
	})

}

const isValidEmail = function(email) {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}

const initEvents = function() {

	$('input:checkbox').click(function() {
		$('#roles-div').removeClass('form-error')
		$('#roles-div + p').text('')
	})
	$('input').keypress(function() {
		$(this).removeClass('form-error')
		$(' + p', this).text('')
	})
}

const fieldError = function(fieldId, errorMessage) {

	$(fieldId).addClass('form-error')
	$(fieldId + ' + p').text(errorMessage)
}